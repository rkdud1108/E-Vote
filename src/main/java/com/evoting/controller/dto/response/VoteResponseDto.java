package com.evoting.controller.dto.response;

import com.evoting.domain.Vote;
import com.evoting.domain.enums.VoteType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteResponseDto {
    private Long id;
    private VoteType voteType;
    private Integer cnt;
    private String memberName;

    public VoteResponseDto(Vote vote) {
        id = vote.getId();
        voteType = vote.getVoteType();
        cnt = vote.getCount();
        memberName = vote.getMember().getName();
    }
}

