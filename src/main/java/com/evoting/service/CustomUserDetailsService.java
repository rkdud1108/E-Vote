package com.evoting.service;

import com.evoting.domain.Member;
import com.evoting.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username){
        return memberRepository.findOneWithAuthoritiesByName(username)
                .map(member -> createUser(username, member))
                .orElseThrow(()-> new UsernameNotFoundException(username+"-> 데이터베이스에서 찾을 수 없습니다."));
    }

    private User createUser(String username, Member member){
        List<GrantedAuthority> grantedAuthorities=member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new User(member.getName(),member.getPwd(),grantedAuthorities);
    }
}
