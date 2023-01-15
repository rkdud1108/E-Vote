package com.evoting.controller;

import com.evoting.controller.dto.MemberDto;
import com.evoting.domain.Member;
import com.evoting.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    //회원 목록 조회
    @GetMapping()
    public List<MemberDto> memberList(){
        List<MemberDto> result = memberService.findAll();
        return result;
    }

    //spring security 적용
    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@Valid @RequestBody MemberDto memberDto){
        return ResponseEntity.ok(memberService.signup(memberDto));
    }

    @PostMapping("/admin/signup")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Member> signupAdmin(@Valid @RequestBody MemberDto userDto){
        return ResponseEntity.ok(memberService.signup(userDto));
    }

    //USER, ADMIN 둘 모두에게 권한 허용
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<Member> getMyUserInfo(){
        return ResponseEntity.ok(memberService.getMyUserWithAuthorities().get());
    }

    //ADMIN에게만 권한 허용
    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Member> getUserInfo(@PathVariable String username){
        return ResponseEntity.ok(memberService.getUserWithAuthorities(username).get());
    }

}
