package com.evoting.controller;

import com.evoting.controller.dto.response.ResponseVoteInterface;
import com.evoting.controller.dto.VoteDto;
import com.evoting.controller.dto.response.VoteResponseDto;
import com.evoting.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    //투표 하기
    @PostMapping("/vote")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity write(@RequestBody VoteDto voteDto){
        return ResponseEntity.ok().body(voteService.voteToAgenda(voteDto));
    }

    //안건 id에 따라 전체 투표결과 가져오기(유저)
    @GetMapping("/votes/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity findAgendaIdResult(@PathVariable Long id){
        List<ResponseVoteInterface> result= voteService.findAgendaIdResult(id);
        return ResponseEntity.ok().body(result);
    }

    //안건 id에 따라 전체 투표결과 가져오기(관리자)
    @GetMapping("/votes/admin/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity findAdminAgendaIdResult(@PathVariable Long id){
        List<VoteResponseDto> result= voteService.findAdminAgendaIdResult(id);
        return ResponseEntity.ok().body(result);
    }
}
