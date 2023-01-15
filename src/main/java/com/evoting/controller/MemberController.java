package com.evoting.controller;

import com.evoting.controller.dto.MemberDto;
import com.evoting.domain.Member;
import com.evoting.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    //회원 목록 조회
    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<MemberDto>> memberList(){
        List<MemberDto> result = memberService.findAll();
        return ResponseEntity.ok().body(result);
    }

    //회원 이름으로 단건 조회
    @GetMapping("/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<MemberDto> memberName(@PathVariable String name){
        ModelMapper mapper = new ModelMapper();
        Member member = memberService.findOne(name);

        return ResponseEntity.ok().body(mapper.map(member, MemberDto.class));
    }

    //spring security 적용
    //유저 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@Valid @RequestBody MemberDto memberDto){
        return ResponseEntity.ok(memberService.signup(memberDto));
    }
    
    //관리자 회원가입
    @PostMapping("/admin/signup")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Member> signupAdmin(@Valid @RequestBody MemberDto userDto){
        return ResponseEntity.ok(memberService.signup(userDto));
    }

}
