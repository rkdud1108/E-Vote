package com.evoting.domain;

import com.evoting.domain.enums.AgendaStatus;
import com.evoting.domain.enums.AgendaType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="agenda_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgendaStatus agendaStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgendaType agendaType;

    private Integer maxCount;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
    private List<Vote> votes;
}
