package com.cpcl.member.security;

import com.cpcl.member.Member;
import com.cpcl.security.dao.MemberDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockCustomMemberSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomMember> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomMember customMember) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Member member = Member.builder()
                .id(customMember.id())
                .email(customMember.email())
                .password(customMember.password())
                .name(customMember.name())
                .phone(customMember.phone())
                .build();

        MemberDetails memberDetails = new MemberDetails(member);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                memberDetails,
                memberDetails.getPassword(),
                memberDetails.getAuthorities()
        );

        context.setAuthentication(auth);
        return context;
    }
}
