package com.cpcl.security.filter;

import com.cpcl.security.dto.Login;
import com.cpcl.security.exception.AuthErrorCode;
import com.cpcl.security.exception.LoginRequestInvalidException;
import com.cpcl.security.exception.MethodNotSupportedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new MethodNotSupportedException(AuthErrorCode.METHOD_NOT_SUPPORTED);
        }
        Login.Request loginRequest;
        try {
            loginRequest = objectMapper.readValue(request.getInputStream(), Login.Request.class);
        } catch (IOException e) {
            throw new LoginRequestInvalidException(AuthErrorCode.LOGIN_REQUEST_INVALID);
        }

        String username = loginRequest.getEmail();
        username = (username != null) ? username.trim() : "";
        String password = loginRequest.getPassword();
        password = (password != null) ? password : "";

        UsernamePasswordAuthenticationToken authRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
