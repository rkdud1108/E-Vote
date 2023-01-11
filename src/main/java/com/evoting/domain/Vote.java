package com.evoting.domain;

import com.evoting.domain.enums.VoteType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vote_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    private Long count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @Builder
    public Vote(Long id, VoteType voteType, Long count, Agenda agenda) {
        this.id = id;
        this.voteType = voteType;
        this.count = count;
        this.agenda = agenda;
    }
}
