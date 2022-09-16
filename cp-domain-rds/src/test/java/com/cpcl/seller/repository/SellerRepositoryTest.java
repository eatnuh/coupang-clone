package com.cpcl.seller.repository;

import com.cpcl.RepositoryTest;
import com.cpcl.seller.Seller;
import com.cpcl.seller.company.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("SellerRepository 테스트")
class SellerRepositoryTest extends RepositoryTest {

    @Autowired
    private SellerRepository sellerRepository;

    private Seller givenSeller;

    @BeforeEach
    void setUp() {
        String email = "abc@abc.com";
        String password = "1234";
        String name = "mike";
        String phone = "010-1234-1234";
        Company company = Company.builder()
                .number("01-123-538-2")
                .name("A seller")
                .phone("010-5555-5555").build();

        givenSeller = Seller.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .company(company)
                .build();
    }

    @DisplayName("Seller 저장")
    @Test
    void save() {
        //given
        //when
        Seller savedSeller = sellerRepository.save(givenSeller);

        //then
        assertThat(savedSeller.getId()).isNotNull();
    }
}