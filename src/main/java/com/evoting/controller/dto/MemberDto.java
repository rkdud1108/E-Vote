package com.evoting.controller.dto;

import com.evoting.domain.Agenda;
import com.evoting.domain.Member;
import com.evoting.domain.Role;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class MemberDto {

    private Long memberId;
    private String pwd;
    private String name;
    private Role role;

    public MemberDto(Member member) {
        memberId = member.getId();
        pwd = member.getPwd();
        name = member.getName();
        role = member.getRole();
    }

    public Member toEntity(){
        return Member.builder()
                .pwd(pwd)
                .name(name)
                .role(role)
                .build();
    }


}
