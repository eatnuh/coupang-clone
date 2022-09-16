package com.cpcl.member.api;

import com.cpcl.member.application.MemberService;
import com.cpcl.member.dto.MemberSignup;
import com.cpcl.member.dto.MemberUpdate;
import com.cpcl.member.exception.EmailDuplicateException;
import com.cpcl.member.exception.MemberErrorCode;
import com.cpcl.member.security.WithMockCustomMember;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("MemberController 테스트")
@SpringBootTest
class MemberControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup(@Autowired WebApplicationContext applicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .alwaysDo(print())
                .build();
    }

    @DisplayName("Member 회원가입 - 성공")
    @Test
    void signup_success() throws Exception {
        //given
        MemberSignup.Request request = MemberSignup.Request.builder()
                .email("abc@abc.com")
                .password("abcde123$fg")
                .name("john")
                .phone("010-1234-1234")
                .build();

        given(memberService.signup(any()))
                .willReturn(
                        new MemberSignup.Response(1L)
                );
        //when
        //then
        mockMvc.perform(post("/api/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated());
    }

    @DisplayName("Member 회원가입 실패 - 이메일 중복")
    @Test
    void signup_failed_email_duplicated() throws Exception {
        //given
        MemberSignup.Request request = MemberSignup.Request.builder()
                .email("abc@abc.com")
                .password("abcde123$fg")
                .name("john")
                .phone("010-1234-1234")
                .build();

        given(memberService.signup(any()))
                .willThrow(new EmailDuplicateException(MemberErrorCode.EMAIL_DUPLICATION));

        //when
        //then
        mockMvc.perform(post("/api/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isInternalServerError());
    }

    @DisplayName("Member 회원가입 실패 - 유효하지 않은 요청")
    @Test
    void signup_failed_invalid_request() throws Exception {
        //given
        MemberSignup.Request invalidRequest = MemberSignup.Request.builder()
                .email("abc@abc.com")
                .password("12")
                .name("john")
                .phone("010-1234-1234")
                .build();

        //when
        //then
        mockMvc.perform(post("/api/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest))
                )
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Member 수정 성공")
    @WithMockCustomMember
    @Test
    void update_success() throws Exception {
        //given
        MemberUpdate.Request request = MemberUpdate.Request.builder()
                .password("abcdefgh123!")
                .name("mike")
                .phone("010-1234-1234")
                .build();
        given(memberService.signup(any()))
                .willReturn(
                        new MemberSignup.Response(1L)
                );
        //when
        //then
        mockMvc.perform(put("/api/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isAccepted());
    }

    @DisplayName("Member 수정 실패 - 인증되지 않은 Member")
    @WithAnonymousUser
    @Test
    void update_failed_unauthenticated() throws Exception {
        //given
        MemberUpdate.Request request = MemberUpdate.Request.builder()
                .password("abcdefgh123!")
                .name("mike")
                .phone("010-1234-1234")
                .build();
        //when
        //then
        mockMvc.perform(put("/api/member")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isUnauthorized());
    }

}