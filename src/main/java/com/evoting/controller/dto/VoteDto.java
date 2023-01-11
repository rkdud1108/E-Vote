package com.evoting.controller.dto;

import com.evoting.domain.Agenda;
import com.evoting.domain.Member;
import com.evoting.domain.Vote;
import com.evoting.domain.enums.VoteType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteDto {
    private Long id;
    private VoteType voteType;
    private Long count;
    private Agenda agenda;
    private Long agendaId;
    private Member voter;

    public VoteDto(Vote vote) {
        id = vote.getId();
        voteType = vote.getVoteType();
        count = vote.getCount();
        agenda = vote.getAgenda();
    }

    public Vote toEntity(){
        return Vote.builder()
                .id(id)
                .voteType(voteType)
                .count(count)
                .agenda(agenda)
                .build();
    }


}
