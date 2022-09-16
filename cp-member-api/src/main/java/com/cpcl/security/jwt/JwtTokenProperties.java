package com.cpcl.security.jwt;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtTokenProperties {
    private String issuer = "Member-Api";
    private String secretKey = "bnidnosindonaoinsdfknalsdknf";
    private int expiryMillisecond = 1000 * 60 * 10;
}
