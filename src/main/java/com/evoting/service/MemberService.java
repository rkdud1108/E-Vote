package com.evoting.service;

import com.evoting.controller.dto.MemberDto;
import com.evoting.domain.Member;
import com.evoting.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    @Transactional
    public Long join(MemberDto params){
        validateDuplicateMember(params);//이름으로 중복확인
        Member entity = memberRepository.save(params.toEntity());
        return entity.getId();
    }

    private void validateDuplicateMember(MemberDto params){
        List<Member> findMembers=memberRepository.findAllByName(params.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //전체 회원 조회
    public List<MemberDto> findAll(){
        return memberRepository.findAll().stream().map(MemberDto::new).collect(Collectors.toList());
    }

    //회원 1명 조회(id)
    public Member findOne(String memberName){
        return memberRepository.findByName(memberName);
    }

    //로그인
    public boolean login(MemberDto MemberDto) {
        Member findMember = memberRepository.findByName(MemberDto.getName());

        if(findMember==null){
            return false;
        }
        if(!findMember.getPwd().equals(MemberDto.getPwd())){
            return false;
        }
        return true;
    }

    //회원 의결권 부여
    public void giveVote(int cnt, String name){
        memberRepository.giveVote(cnt,name);
    }
}
