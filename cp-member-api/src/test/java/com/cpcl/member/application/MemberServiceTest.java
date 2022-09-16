package com.cpcl.member.application;

import com.cpcl.member.Member;
import com.cpcl.member.dto.MemberSignup;
import com.cpcl.member.exception.EmailDuplicateException;
import com.cpcl.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

@DisplayName("MemberService 테스트")
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;

    @DisplayName("Member 회원가입 - 성공")
    @Test
    void signup_success() {
        //given
        MemberSignup.Request givenRequest = MemberSignup.Request.builder()
                .email("abc@abc.com")
                .password("abcdefg")
                .name("john")
                .phone("010-1234-1234")
                .build();
        Member resultMember = Member.builder()
                .id(1L)
                .build();

        given(memberRepository.existsByEmail(givenRequest.getEmail())).willReturn(false);
        given(passwordEncoder.encode(anyString())).willReturn("encodedPassword");
        given(memberRepository.save(any())).willReturn(resultMember);

        //when
        memberService.signup(givenRequest);
        //then

        then(memberRepository)
                .should(times(1))
                .existsByEmail(givenRequest.getEmail());
        then(passwordEncoder)
                .should(times(1))
                .encode(anyString());
        then(memberRepository)
                .should(times(1))
                .save(any());
    }

    @DisplayName("Member 회원가입 - 실패 이메일 중복")
    @Test
    void signup_failed_duplicatedEmail() {
        //given
        MemberSignup.Request givenRequest = MemberSignup.Request.builder()
                .email("abc@abc.com")
                .password("abcdefg")
                .name("john")
                .phone("010-1234-1234")
                .build();

        given(memberRepository.existsByEmail(givenRequest.getEmail())).willReturn(true);

        //when
        assertThatThrownBy(() -> memberService.signup(givenRequest))
                .isInstanceOf(EmailDuplicateException.class);

        //then
        then(memberRepository)
                .should(times(1))
                .existsByEmail(givenRequest.getEmail());
        then(passwordEncoder)
                .should(never())
                .encode(anyString());
        then(memberRepository)
                .should(never())
                .save(any());
    }

}