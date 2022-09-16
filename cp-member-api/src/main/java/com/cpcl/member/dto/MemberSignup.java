package com.cpcl.member.dto;

import com.cpcl.member.Member;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MemberSignup {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {

        @NotBlank(message = "이메일은 필수입니다.")
        @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$",
                message = "이메일 번호 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다..")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,20}$",
                message = "비밀번호는 최소 8자리 최대 20자리에 숫자, 문자, 특수문자 각각 1개 이상 포함되어야합니다.")
        private String password;

        @NotBlank(message = "이름은 필수입니다.")
        @Size(min = 3, max = 20, message = "이름의 길이는 최소 3자 부터 20자 까지입니다.")
        private String name;

        @NotBlank(message = "핸드폰은 필수입니다.")
        @Pattern(regexp = "^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})$",
                message = "핸드폰 번호 형식이 아닙니다.")
        private String phone;

        @Builder
        public Request(String email, String password, String name, String phone) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.phone = phone;
        }

        public void encodePassword(String encodedPassword) {
            this.password = encodedPassword;
        }

        public Member toEntity() {
            return Member.builder()
                    .email(this.getEmail())
                    .password(this.getPassword())
                    .name(this.getName())
                    .phone(this.getPhone())
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Response {
        private Long memberId;

        public static Response fromEntity(Member member) {
            return new Response(member.getId());
        }
    }

}
