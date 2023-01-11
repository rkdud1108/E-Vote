package com.evoting.service;

import com.evoting.controller.dto.VoteDto;
import com.evoting.domain.Agenda;
import com.evoting.domain.Vote;
import com.evoting.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;

    //안건 id에 따라 투표하기
    @Transactional
    public Long voteToAgenda(VoteDto voteDto){
        //agendaId 셋팅
        Agenda agenda = new Agenda();
        agenda.setId(voteDto.getAgendaId());
        voteDto.setAgenda(agenda);

        Vote entity = voteRepository.save(voteDto.toEntity());
        return entity.getId();
    }

}
