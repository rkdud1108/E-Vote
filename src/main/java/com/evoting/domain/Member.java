package com.evoting.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private Integer voteCount;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Vote> votes;

    @ManyToMany
    @JoinTable(
            name="user_auth",
            joinColumns = {@JoinColumn(name="member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name="authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

}
