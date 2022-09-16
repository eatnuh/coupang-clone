package com.cpcl.member.application;

import com.cpcl.member.Member;
import com.cpcl.member.exception.MemberNotFoundException;
import com.cpcl.member.repository.MemberRepository;
import com.cpcl.member.dto.MemberSignup;
import com.cpcl.member.dto.MemberUpdate;
import com.cpcl.member.exception.MemberErrorCode;
import com.cpcl.member.exception.EmailDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberSignup.Response signup(MemberSignup.Request request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new EmailDuplicateException(
                    MemberErrorCode.EMAIL_DUPLICATION,
                    String.format("signup : $s 이메일 중복", request.getEmail())
            );
        }

        request.encodePassword(
                passwordEncoder.encode(request.getPassword())
        );

        return MemberSignup.Response.fromEntity(
                memberRepository.save(request.toEntity())
        );
    }

    @Transactional
    public MemberUpdate.Response update(MemberUpdate.Request request, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberNotFoundException(
                        MemberErrorCode.MEMBER_NOT_FOUND,
                        String.format("update : memberId가 $d인 member가 없습니다.", memberId))
        );


        member.update(request.getPassword(), request.getName(), request.getPhone());

        return MemberUpdate.Response.fromEntity(member);
    }

    @Transactional
    public void resign(Member member) {
        memberRepository.delete(member);
    }
}
