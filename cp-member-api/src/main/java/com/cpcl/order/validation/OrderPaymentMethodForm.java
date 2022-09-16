package com.cpcl.order.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = OrderPaymentMethodFormValidator.class)
public @interface OrderPaymentMethodForm {
    String message() default "OrderPaymentMethod is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
