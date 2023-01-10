package com.evoting.controller.dto;

import com.evoting.domain.Agenda;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgendaDto {
    private Long agendaId;
    private String title;
    private String contents;

    public AgendaDto(Agenda agenda) {
        agendaId = agenda.getId();
        title = agenda.getTitle();
        contents = agenda.getContents();
    }

    public Agenda toEntity(){
        return Agenda.builder()
                .title(title)
                .contents(contents)
                .build();
    }
}
