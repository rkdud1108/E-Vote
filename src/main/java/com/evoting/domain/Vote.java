package com.evoting.domain;

import com.evoting.domain.enums.VoteType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vote_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

}
