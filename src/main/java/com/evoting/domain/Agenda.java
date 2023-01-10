package com.evoting.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="agenda_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;
    private String title;
    private String contents;

    @NotNull(message = "status 값은 필수 값입니다.")
    @Enumerated(EnumType.STRING)
    private AgendaStatus agendaStatus;

    @Builder
    public Agenda(Long id, Member writer, String title, String contents){
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }
}
