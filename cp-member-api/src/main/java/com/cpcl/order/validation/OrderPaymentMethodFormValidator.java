package com.cpcl.order.validation;

import com.cpcl.order.dto.OrderPaymentMethod;
import com.cpcl.order.payment.type.PaymentType;
import com.cpcl.order.validation.group.PaymentMethodGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RequiredArgsConstructor
public class OrderPaymentMethodFormValidator implements ConstraintValidator<OrderPaymentMethodForm, OrderPaymentMethod> {

    private final Validator validator;

    @Override
    public boolean isValid(OrderPaymentMethod value, ConstraintValidatorContext context) {
        if (value.getPaymentType() == PaymentType.PHONE) {
            final Set<ConstraintViolation<Object>> constraintViolations = validator.validate(value, PaymentMethodGroup.Phone.class);
            if (!CollectionUtils.isEmpty(constraintViolations)) {
                context.disableDefaultConstraintViolation();
                constraintViolations
                        .stream()
                        .forEach(constraintViolation -> {
                            context.buildConstraintViolationWithTemplate(constraintViolation.getMessageTemplate())
                                    .addPropertyNode(constraintViolation.getPropertyPath().toString())
                                    .addConstraintViolation();
                        });
                return false;
            }
        }
        if (value.getPaymentType() == PaymentType.CREDIT_CARD) {
            final Set<ConstraintViolation<Object>> constraintViolations = validator.validate(value, PaymentMethodGroup.CreditCard.class);
            if (!CollectionUtils.isEmpty(constraintViolations)) {
                context.disableDefaultConstraintViolation();
                constraintViolations
                        .stream()
                        .forEach(constraintViolation -> {
                            context.buildConstraintViolationWithTemplate(constraintViolation.getMessageTemplate())
                                    .addPropertyNode(constraintViolation.getPropertyPath().toString())
                                    .addConstraintViolation();
                        });
                return false;
            }
        }

        return true;
    }

}
