package com.cpcl.domain.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Member 도메인 테스트")
class MemberTest {

    @Test
    @DisplayName("Member 생성 테스트")
    void createMember() {
        //given
        //when
        Member member = Member.builder()
                .email("abc@abc.com")
                .password("1234")
                .name("john")
                .phone("010-1234-1234").build();

        //then
        assertThat(member.getEmail()).isEqualTo("abc@abc.com");
        assertThat(member.getPassword()).isEqualTo("1234");
        assertThat(member.getName()).isEqualTo("john");
        assertThat(member.getPhone()).isEqualTo("010-1234-1234");

    }
}