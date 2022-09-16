package com.cpcl.member.api;

import com.cpcl.member.application.MemberService;
import com.cpcl.member.Member;
import com.cpcl.member.dto.MemberSignup;
import com.cpcl.member.dto.MemberUpdate;
import com.cpcl.member.exception.EmailDuplicateException;
import com.cpcl.member.exception.MemberErrorCode;
import com.cpcl.security.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberSignup.Response signup(@RequestBody @Valid MemberSignup.Request request) {
        return memberService.signup(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MemberUpdate.Response update(@RequestBody @Valid MemberUpdate.Request request,
                                        @AuthMember Member member) {
        return memberService.update(request, member.getId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resign(@AuthMember Member member) {
        memberService.resign(member);
    }

}
