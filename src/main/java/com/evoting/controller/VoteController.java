package com.evoting.controller;

import com.evoting.controller.dto.VoteDto;
import com.evoting.domain.Agenda;
import com.evoting.domain.Member;
import com.evoting.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    //투표 하기
    @PostMapping("/vote")
    public Long write(@RequestBody VoteDto voteDto, HttpServletRequest request){
        HttpSession session = request.getSession();
        //회원 정보 셋팅
        Member mem = (Member)session.getAttribute("loginUser");
        voteDto.setVoter(mem);

        return voteService.voteToAgenda(voteDto);
    }
}
