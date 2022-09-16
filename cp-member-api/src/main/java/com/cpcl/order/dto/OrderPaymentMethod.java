package com.cpcl.order.dto;

import com.cpcl.order.payment.type.PaymentType;
import com.cpcl.order.validation.OrderPaymentMethodForm;
import com.cpcl.order.validation.group.PaymentMethodGroup.CreditCard;
import com.cpcl.order.validation.group.PaymentMethodGroup.Phone;
import com.cpcl.order.validation.group.ValidationSequence;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cpcl.order.validation.group.ValidationSequence.*;
import org.springframework.validation.annotation.Validated;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Validated({ValidationSequence.class})
@OrderPaymentMethodForm(groups = ExistCheck.class)
public class OrderPaymentMethod {

    @NotNull(groups = ExistCheck.class,
            message = "결제수단이 업습니다."
    )
    @Valid private PaymentType paymentType;


    @NotNull(groups = CreditCard.class,
            message = "신용카드 결제정보가 없습니다."
    )
    @Valid private CreditCardPayment creditCardPayment;

    @NotNull(groups = Phone.class,
            message = "핸드폰 결제정보가 없습니다."
    )
    @Valid private PhonePayment phonePayment;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Validated(ValidationSequence.class)
    public static class PhonePayment {

        @NotNull(groups = ExistCheck.class,
                message = "핸드폰 번호가 없습니다."
        )
        @Pattern(regexp = "^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})$",
                groups = DataCheck.class,
                message = "핸드폰 번호 형식이 아닙니다.")
        private String phoneNumber;

        @NotNull(groups = ExistCheck.class,
                message = "통신사 정보가 없습니다."
        )
        private String carrier;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Validated(ValidationSequence.class)
    public static class CreditCardPayment {

        @NotNull(groups = ExistCheck.class,
                message = "카드 브랜드가 없습니다."
        )
        private String brand;

        @NotNull(groups = ExistCheck.class,
                message = "카드번호가 없습니다."
        )
        private String cardNumber;

        @NotNull(groups = ExistCheck.class,
                message = "csv 번호가 업습니다."
        )

        @Size(min = 3, max = 3,
                groups = DataCheck.class,
                message = "csv는 3자리 입니다.")
        private String csv;
    }

}
