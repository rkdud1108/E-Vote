package com.evoting.controller.dto;

import com.evoting.domain.Agenda;
import com.evoting.domain.enums.AgendaStatus;
import com.evoting.domain.enums.AgendaType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgendaDto {
    private Long id;
    private String title;
    private String contents;
    private AgendaStatus agendaStatus;
    private AgendaType agendaType;
    private Long maxCount;

    public AgendaDto(Agenda agenda) {
        id = agenda.getId();
        title = agenda.getTitle();
        contents = agenda.getContents();
        agendaStatus = agenda.getAgendaStatus();
        agendaType = agenda.getAgendaType();
        maxCount = agenda.getMaxCount();
    }

    //requset, response 나누면 request에 toEntity 옮겨주면 writer null 해결..?
    public Agenda toEntity(){
        return Agenda.builder()
                .id(id)
                .title(title)
                .contents(contents)
                .agendaStatus(agendaStatus)
                .agendaType(agendaType)
                .maxCount(maxCount)
                .build();
    }
}
