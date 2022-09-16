package com.cpcl.member.repository;

import com.cpcl.RepositoryTest;
import com.cpcl.member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MemberRepository 테스트")
class MemberRepositoryTest extends RepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private Member givenMember;

    @BeforeEach
    void setUp() {
        givenMember = Member.builder()
                .email("abc@abc.com")
                .password("1234")
                .name("john")
                .phone("010-1234-1234").build();
    }

    @DisplayName("Member 저장")
    @Test
    void save() {
        //given
        //when
        Member savedMember = memberRepository.save(givenMember);

        //then
        assertThat(savedMember.getId()).isNotNull();
    }

    @DisplayName("Member id 조회")
    @Test
    void findById() {
        //given
        Member savedMember = memberRepository.save(givenMember);
        //when
        Optional<Member> findMember = memberRepository.findById(savedMember.getId());
        //then
        assertThat(findMember).containsSame(savedMember);
    }

    @DisplayName("Member email 조회")
    @Test
    void findByEmail() {
        //given
        Member savedMember = memberRepository.save(givenMember);
        //when
        Optional<Member> findMember = memberRepository.findByEmail(savedMember.getEmail());
        //then
        assertThat(findMember).containsSame(savedMember);
    }

    @DisplayName("Member email 존재")
    @Test
    void existsByEmail() {
        //given
        Member savedMember = memberRepository.save(givenMember);
        //when
        Boolean exist = memberRepository.existsByEmail(savedMember.getEmail());
        //then
        assertThat(exist).isTrue();
    }
}