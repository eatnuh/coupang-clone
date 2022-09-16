package com.cpcl.order.validation;

import com.cpcl.order.dto.OrderPayment;
import com.cpcl.order.exception.OrderErrorCode;
import com.cpcl.order.exception.OrderProductNotFoundException;
import com.cpcl.product.Product;
import com.cpcl.product.repository.ProductRepository;
import com.cpcl.product.type.ProductStatus;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class OrderPaymentFormValidator implements ConstraintValidator<OrderPaymentForm, OrderPayment> {

    private final ProductRepository productRepository;

    @Override
    public boolean isValid(OrderPayment value, ConstraintValidatorContext context) {
        Product product = productRepository.findById(value.getProductId()).orElseThrow(
                () -> new OrderProductNotFoundException(OrderErrorCode.ORDER_PRODUCT_NOT_FOUND)
        );

        int invalidCount = 0;

        if (product.getProductStatus() != ProductStatus.SALE) {
            addConstraintViolation(context, "판매중인 상품이 아닙니다.", "productStatus");
            invalidCount++;
        }

        if (!product.getName().equals(value.getProductName())) {
            addConstraintViolation(context, "상품명이 다릅니다.", "productName");
            invalidCount++;
        }

        if (!product.getPrice().equals(value.getPrice())) {
            addConstraintViolation(context, "상품가격이 다릅니다.", "price");
            invalidCount++;
        }

        if (!value.getTotalPrice().equals(value.getPrice() * value.getQuantity())) {
            addConstraintViolation(
                    context,
                    "총 상품가격과 (상품가격 * 수량)이 같지 않습니다.",
                    "totalPrice");
            invalidCount++;
        }

        if (!value.getFinalPrice().equals(
                value.getTotalPrice()
                        + value.getShippingFee()
                        - value.getDiscountPrice())
        ) {
            addConstraintViolation(context,
                    "최종결제금액과 (총상품가격 + 배송비 - 할인금액)이 같지 않습니다.",
                    "finalPrice");
            invalidCount++;
        }
        return invalidCount == 0;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String errorMessage,
                                        String firstNode) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage)
                .addPropertyNode(firstNode)
                .addConstraintViolation();
    }

}
