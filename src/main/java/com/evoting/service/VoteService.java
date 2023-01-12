package com.evoting.service;

import com.evoting.controller.dto.AgendaDto;
import com.evoting.controller.dto.VoteDto;
import com.evoting.domain.Agenda;
import com.evoting.domain.Vote;
import com.evoting.repository.AgendaRepository;
import com.evoting.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;

    private final AgendaRepository agendaRepository;
    //안건 id에 따라 투표하기

    @Transactional
    public Long voteToAgenda(VoteDto voteDto){

        //Agenda agenda = agendaRepository.findById(voteDto.getAgendaId()).orElseThrow(IllegalArgumentException::new);
        Optional<Agenda> agenda = agendaRepository.findById(voteDto.getAgendaId());

        //회원의 안건수에 따라 제한걸기
        Vote member = voteRepository.findByMember_Id(voteDto.getMemberId());
        Long voteLimit = member.getMember().getVoteCount();
        Long voteReq = voteDto.getCount();

        //요청 들어온 값이 크다면
        if(voteLimit<voteReq){
            voteDto.setCount(voteLimit);
        }

        Vote entity = voteRepository.save(voteDto.toEntity());
        return entity.getId();
    }

    //안건 id에 따라 전체 투표결과 가져오기
    public List<VoteDto> findAgendaIdResult(Long id){
        List<VoteDto> result = voteRepository.findAgendaIdResult(id).stream().map(VoteDto::new).collect(Collectors.toList());

        return result;
    }

}
