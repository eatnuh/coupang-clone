package com.cpcl.seller;

import com.cpcl.seller.company.Company;
import com.cpcl.seller.Seller;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("판매자 도메인 테스트")
class SellerTest {

    @Test
    @DisplayName("판매자 생성 테스트")
    void createSeller() {
        //given
        //when
        String email = "abc@abc.com";
        String password = "1234";
        String name = "mike";
        String phone = "010-1234-1234";
        Company company = Company.builder()
                .number("01-123-538-2")
                .name("A seller")
                .phone("010-5555-5555").build();

        Seller seller = Seller.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .company(company)
                .build();

        //then
        Assertions.assertThat(seller.getEmail()).isEqualTo(email);
        Assertions.assertThat(seller.getPassword()).isEqualTo(password);
        Assertions.assertThat(seller.getName()).isEqualTo(name);
        Assertions.assertThat(seller.getPhone()).isEqualTo(phone);
        Assertions.assertThat(seller.getCompany()).isEqualTo(company);
    }

}