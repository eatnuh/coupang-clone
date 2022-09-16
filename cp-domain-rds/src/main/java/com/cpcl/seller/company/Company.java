package com.cpcl.seller.company;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Column(name = "company_number")
    private String number;

    @Column(name = "company_name")
    private String name;

    @Column(name = "company_phone")
    private String phone;

    @Embedded
    private com.cpcl.common.Address Address;

    @Builder
    public Company(String number, String name, String phone, com.cpcl.common.Address address) {
        this.number = number;
        this.name = name;
        this.phone = phone;
        this.Address = address;
    }
}
