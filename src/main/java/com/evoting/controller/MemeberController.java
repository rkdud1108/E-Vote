package com.evoting.controller;

import com.evoting.controller.dto.MemberDto;
import com.evoting.domain.Member;
import com.evoting.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemeberController {
    private final MemberService memberService;

    //화면 조회
    @GetMapping("/members/new")
    public String createForm(Model model, @RequestBody MemberDto request){
        model.addAttribute("memberForm", request);
        return "members/createMemberForm";
    }

    //정보를 등록
    @PostMapping("/members/new")
    public Long createMember(@RequestBody @Valid MemberDto request, BindingResult result){
        if(result.hasErrors()){
            throw new IllegalStateException("유효성 검사에 실패하였습니다.");
        }
        return memberService.join(request);
    }

    //회원 목록 조회
    @GetMapping(value = "/members")
    public List<MemberDto> memberList(Model model){
        List<MemberDto> result = memberService.findAll();
        return result;
    }

    //회원 로그인
    @PostMapping("/login")
    public String loginId(@RequestBody MemberDto memberDto, HttpServletRequest request) {
        HttpSession session = request.getSession();

        if(memberService.login(memberDto)){
            Member nowMem = memberService.findOne(memberDto.getName());
            session.setAttribute("loginUser", nowMem);
            return "login Success";
        }
        return "login Fail";
    }

    //회원 로그아웃
    @GetMapping("/logout")
    public String logoutId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "logout success";
    }
}
