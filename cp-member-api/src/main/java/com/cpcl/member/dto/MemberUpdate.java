package com.cpcl.member.dto;

import com.cpcl.member.Member;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MemberUpdate {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Request {

        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,20}$",
                message = "비밀번호는 최소 8자리 최대 20자리에 숫자, 문자, 특수문자 각각 1개 이상 포함되어야합니다.")
        private String password;

        @Size(min = 3, max = 20, message = "이름의 길이는 최소 3자 부터 20자 까지입니다.")
        private String name;

        @Pattern(regexp = "^01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})$",
                message = "핸드폰 번호 형식이 아닙니다.")
        private String phone;

        @Builder
        public Request(String password, String name, String phone) {
            this.password = password;
            this.name = name;
            this.phone = phone;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class Response {
        private Long id;

        public static Response fromEntity(Member member) {
            return new Response(member.getId());
        }
    }

}
