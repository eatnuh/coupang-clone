package com.cpcl.order.dto;

import com.cpcl.order.validation.OrderPaymentForm;
import com.cpcl.order.validation.group.ValidationSequence;
import com.cpcl.order.validation.group.ValidationSequence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Validated(ValidationSequence.class)
@OrderPaymentForm(groups = LogicCheck.class)
public class OrderPayment {

    @NotNull(groups = ExistCheck.class,
            message = "상품 ID가 없습니다.")
    private Long productId;

    @NotBlank(groups = ExistCheck.class,
            message = "상품 이름이 없습니다.")
    private String productName;

    @NotNull(groups = ExistCheck.class,
            message = "상품 가격이 없습니다.")
    @PositiveOrZero(groups = DataCheck.class,
            message = "상품 가격은 0 이상입니다.")
    private Integer price;

    @NotNull(groups = ExistCheck.class,
            message = "상품 수량이 없습니다.")
    @PositiveOrZero(groups = DataCheck.class,
            message = "상품 수량은 0 이상입니다.")
    private Integer quantity;

    @NotNull(groups =ExistCheck.class,
            message = "총 상품가격이 없습니다.")
    @PositiveOrZero(groups = DataCheck.class,
            message = "총 상품가격은 0 이상입니다.")
    private Integer totalPrice;

    @NotNull(groups = ExistCheck.class,
            message = "배송비가 없습니다.")
    @PositiveOrZero(groups = DataCheck.class,
            message = "배송비는 0 이상입니다.")
    private Integer shippingFee;

    @NotNull(groups = ExistCheck.class,
            message = "할인금액이 없습니다.")
    @PositiveOrZero(groups = DataCheck.class,
            message = "할인금액은 0 이상입니다.")
    private Integer discountPrice;

    @NotNull(groups = ExistCheck.class,
            message = "최종 결제금액이 없습니다.")
    @PositiveOrZero(groups = DataCheck.class,
            message = "최종 결제금액은 0 이상입니다.")
    private Integer finalPrice;

}
