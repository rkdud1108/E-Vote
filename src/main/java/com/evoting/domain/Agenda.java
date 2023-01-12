package com.evoting.domain;

import com.evoting.domain.enums.AgendaStatus;
import com.evoting.domain.enums.AgendaType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="agenda_id")
    private Long id;

    private String title;
    private String contents;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgendaStatus agendaStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgendaType agendaType;

    @Column(nullable = false)
    private Long maxCount;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
    private List<Vote> votes;

    @Builder
    public Agenda(Long id, String title, String contents, AgendaStatus agendaStatus, AgendaType agendaType, Long maxCount){
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.agendaStatus = agendaStatus;
        this.agendaType = agendaType;
        this.maxCount = maxCount;
    }
}
