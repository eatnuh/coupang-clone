package com.cpcl.member.security;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomMemberSecurityContextFactory.class)
public @interface WithMockCustomMember {
    long id() default 1L;

    String email() default "abc@abc.com";

    String password() default "abcdefg123!";

    String name() default "john";

    String phone() default "010-1234-1234";


}
