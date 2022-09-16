package com.cpcl.order.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = OrderPaymentFormValidator.class)
@Target({TYPE, FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderPaymentForm {

    String message() default "OrderPayment is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
