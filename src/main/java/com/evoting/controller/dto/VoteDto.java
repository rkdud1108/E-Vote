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
    private Long agendaId;
    private Long memberId;

    public VoteDto(Vote vote) {
        id = vote.getId();
//        memberId = vote.getMember().getId();
        voteType = vote.getVoteType();
        count = vote.getCount();
    }

    public Vote toEntity(){
        return Vote.builder()
                .id(id)
                .voteType(voteType)
                .count(count)
                .build();
    }


}
