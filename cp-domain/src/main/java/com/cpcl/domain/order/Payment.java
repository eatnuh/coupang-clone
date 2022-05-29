package com.cpcl.domain.order;

import com.cpcl.domain.common.Address;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Payment {

    @Column(name = "payment_amount")
    private Integer amount;

    @Enumerated(value = EnumType.STRING)
    private PaymentType type;

    public static Payment of(Integer amount, PaymentType paymentType) {
        return new Payment(amount, paymentType);
    }

}
