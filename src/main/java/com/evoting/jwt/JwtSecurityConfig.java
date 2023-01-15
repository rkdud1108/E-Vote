package com.evoting.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//필터를 Sescurity 로직에 적용
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenProvider tokenProvider;

    //TokenProvider 주입 받음
    public JwtSecurityConfig(TokenProvider tokenProvider){
        this.tokenProvider=tokenProvider;
    }

    //JwtFilter를 통해 Security 로직에 필터를 등록
    @Override
    public void configure(HttpSecurity http){
        JwtFilter customFilter = new JwtFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }


}
