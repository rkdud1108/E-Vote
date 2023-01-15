package com.evoting.service;

import com.evoting.controller.dto.MemberDto;
import com.evoting.domain.Authority;
import com.evoting.domain.Member;
import com.evoting.jwt.SecurityUtil;
import com.evoting.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //전체 회원 조회
    public List<MemberDto> findAll(){
        return memberRepository.findAll().stream().map(MemberDto::new).collect(Collectors.toList());
    }

    //회원 1명 조회(id)
    public Member findOne(String memberName){
        return memberRepository.findByName(memberName);
    }

    //Spring Security
    @Transactional
    public Member signup(MemberDto memberDto){
        //username 중복확인
        if(memberRepository.findOneWithAuthoritiesByName(memberDto.getName()).orElse(null)!= null){
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        //권한생성
        Authority authority= Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .name(memberDto.getName())
                .pwd(passwordEncoder.encode(memberDto.getPwd()))
                .authorities(Collections.singleton(authority))
                .voteCount(memberDto.getVoteCount())
                .build();

        return memberRepository.save(member);
    }

    //username으로 user정보 조회
    @Transactional(readOnly = true)
    public Optional<Member> getUserWithAuthorities(String username){
        return memberRepository.findOneWithAuthoritiesByName(username);
    }

    //현재 SecurityContext에 저장된 username의 정보를 가져옴
    @Transactional(readOnly = true)
    public Optional<Member> getMyUserWithAuthorities(){
        return SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByName);
    }

}
