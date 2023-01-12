package com.evoting.controller.dto;

import com.evoting.domain.Vote;
import com.evoting.domain.enums.VoteType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteResponseDto {
    private Long id;
    private VoteType voteType;
    private Long count;
    private Long agendaId;
    private String memberName;

    public VoteResponseDto(Vote vote) {
        id = vote.getId();
        voteType = vote.getVoteType();
        count = vote.getCount();
        agendaId = vote.getAgenda().getId();
        memberName = vote.getMember().getName();
    }
}
