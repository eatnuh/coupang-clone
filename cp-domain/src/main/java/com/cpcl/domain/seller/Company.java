package com.cpcl.domain.seller;

import com.cpcl.domain.common.Address;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
public class Company {

    @Column(name = "company_number")
    private String number;

    @Column(name = "company_name")
    private String name;

    @Column(name = "company_phone")
    private String phone;

    @Embedded
    private Address Address;

    @Builder
    public Company(String number, String name, String phone, com.cpcl.domain.common.Address address) {
        this.number = number;
        this.name = name;
        this.phone = phone;
        this.Address = address;
    }
}
