package com.evoting.controller;

import com.evoting.controller.dto.VoteDto;
import com.evoting.controller.dto.VoteResponseDto;
import com.evoting.domain.Member;
import com.evoting.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;
    //투표 하기
    @PostMapping("/vote")
    public Long write(@RequestBody VoteDto voteDto, HttpServletRequest request){
        //HttpSession session = request.getSession();
        //회원 정보 셋팅
        //Member mem = (Member)session.getAttribute("loginUser");
        //voteDto.setMemberId(mem.getId());

        return voteService.voteToAgenda(voteDto);
    }

    //안건 id에 따라 전체 투표결과 가져오기
    @GetMapping("/vote/{id}")
    public List<VoteResponseDto> findAgendaIdResult(@PathVariable Long id){
        List<VoteResponseDto> result= voteService.findAgendaIdResult(id);
        return result;
    }
}
