package com.cpcl.security.dao;

import com.cpcl.member.Member;
import com.cpcl.member.repository.MemberRepository;
import com.cpcl.security.exception.AuthErrorCode;
import com.cpcl.security.exception.LoginMemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new LoginMemberNotFoundException(AuthErrorCode.LOGIN_MEMBER_NOT_FOUND)
        );
        return new MemberDetails(member);
    }
}
