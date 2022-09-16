package com.cpcl.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    public static Address of(String zipcode, String address1, String address2) {
        return new Address(zipcode, address1, address2);
    }
}
