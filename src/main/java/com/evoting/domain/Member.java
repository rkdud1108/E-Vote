package com.evoting.domain;

import com.evoting.domain.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL )
    private List<Vote> votes =new ArrayList<>();

    private Long voteCount;

    @Builder
    public Member(Long id, String name, String pwd, Role role
            , Long voteCount) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.role = role;
        this.voteCount = voteCount;
    }
}
