package com.cpcl.order.shipping;

import com.cpcl.common.Address;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Shipping {

    @Column(name = "recipient_name")
    private String name;

    @Column(name = "recipient_phone")
    private String phone;

    @Embedded
    private Address address;

    public static Shipping of(String name, String phone, Address address) {
        return new Shipping(name, phone, address);
    }

}
