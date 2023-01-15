package com.evoting.controller;

import com.evoting.controller.dto.ResponseVoteInterface;
import com.evoting.controller.dto.VoteDto;
import com.evoting.controller.dto.VoteResponseDto;
import com.evoting.controller.dto.response.BaseResponse;
import com.evoting.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    //투표 하기
    @PostMapping("/vote")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public BaseResponse write(@RequestBody VoteDto voteDto, HttpServletRequest request){
        voteService.voteToAgenda(voteDto);
        return new BaseResponse();
    }

    //안건 id에 따라 전체 투표결과 가져오기(유저)
    @GetMapping("/vote/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public List<ResponseVoteInterface> findAgendaIdResult(@PathVariable Long id){
        List<ResponseVoteInterface> result= voteService.findAgendaIdResult(id);
        return result;
    }

    //안건 id에 따라 전체 투표결과 가져오기(관리자)
    @GetMapping("/vote/{id}/admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<VoteResponseDto> findAdminAgendaIdResult(@PathVariable Long id){
        List<VoteResponseDto> result= voteService.findAdminAgendaIdResult(id);
        return result;
    }
}
