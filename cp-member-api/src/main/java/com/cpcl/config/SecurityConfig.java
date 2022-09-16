package com.cpcl.config;

import com.cpcl.security.handler.LoginFailureHandler;
import com.cpcl.security.handler.LoginSuccessfulHandler;
import com.cpcl.security.handler.UnAuthorizationHandler;
import com.cpcl.security.filter.JwtTokenAuthenticationFilter;
import com.cpcl.security.filter.LoginFilter;
import com.cpcl.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final String LOGIN_URL = "/api/login";
    private final String MEMBER = "MEMBER";
    private final ObjectMapper objectMapper;
    private final com.cpcl.security.dao.MemberDetailsService MemberDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public LoginSuccessfulHandler loginSuccessfulHandler() {
        return new LoginSuccessfulHandler(objectMapper, jwtTokenProvider);
    }

    @Bean
    public LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler(objectMapper);
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public UnAuthorizationHandler unAuthorizationHandler() {
        return new UnAuthorizationHandler(objectMapper);
    }

    @Bean
    public JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter() {
        return new JwtTokenAuthenticationFilter(MemberDetailsService, jwtTokenProvider);
    }

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter(objectMapper);
        loginFilter.setFilterProcessesUrl(LOGIN_URL);
        loginFilter.setAuthenticationManager(authenticationManager());
        loginFilter.setAuthenticationSuccessHandler(loginSuccessfulHandler());
        loginFilter.setAuthenticationFailureHandler(loginFailureHandler());
        return loginFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unAuthorizationHandler())
                .and()

                .authorizeRequests(authorize ->
                        authorize
                                .antMatchers(HttpMethod.PUT, "/api/member")
                                .hasRole(MEMBER)
                                .antMatchers(HttpMethod.DELETE, "/api/member")
                                .hasRole(MEMBER)
                                .antMatchers(HttpMethod.GET, "/api/order")
                                .hasRole(MEMBER)
                                .antMatchers(HttpMethod.POST, "/api/order")
                                .hasRole(MEMBER)
                                .antMatchers(HttpMethod.GET, "/api/order/*")
                                .hasRole(MEMBER)
                                .antMatchers(HttpMethod.POST, "/api/order")
                                .hasRole(MEMBER)
                                .antMatchers(HttpMethod.POST, "/api/order/*/review")
                                .hasRole(MEMBER)
                                .anyRequest()
                                .permitAll()
                );

        http.addFilterBefore(jwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
