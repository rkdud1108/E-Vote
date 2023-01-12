package com.evoting.controller.dto;

import com.evoting.domain.Member;
import com.evoting.domain.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MemberDto {

    private Long memberId;
    private String pwd;
    private String name;
    private Role role;
    private Long voteCount;

    public MemberDto(Member member) {
        memberId = member.getId();
        pwd = member.getPwd();
        name = member.getName();
        role = member.getRole();
        voteCount = member.getVoteCount();
    }

    public Member toEntity(){
        return Member.builder()
                .pwd(pwd)
                .name(name)
                .role(role)
                .voteCount(voteCount)
                .build();
    }
}
