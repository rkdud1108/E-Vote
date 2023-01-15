package com.evoting.controller.dto;

import com.evoting.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {

    private Long memberId;
    private String pwd;
    private String name;
    private Integer voteCount;

    public MemberDto(Member member) {
        memberId = member.getId();
        pwd = member.getPwd();
        name = member.getName();
        voteCount = member.getVoteCount();
    }

    public Member toEntity(){
        return Member.builder()
                .pwd(pwd)
                .name(name)
                .voteCount(voteCount)
                .build();
    }
}
