package com.evoting.controller.dto;

import com.evoting.domain.Agenda;
import com.evoting.domain.enums.AgendaStatus;
import com.evoting.domain.enums.AgendaType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AgendaDto {
    private Long agendaId;
    private String title;
    private String contents;
    private AgendaStatus agendaStatus;
    private AgendaType agendaType;
    private Integer maxCount;
    private LocalDate startDate;
    private LocalDate endDate;

    public AgendaDto(Agenda agenda) {
        agendaId = agenda.getId();
        title = agenda.getTitle();
        contents = agenda.getContents();
        agendaStatus = agenda.getAgendaStatus();
        agendaType = agenda.getAgendaType();
        maxCount = agenda.getMaxCount();
        startDate = agenda.getStartDate();
        endDate = agenda.getEndDate();

    }

    public Agenda toEntity(){
        return Agenda.builder()
                .id(agendaId)
                .title(title)
                .contents(contents)
                .agendaStatus(agendaStatus)
                .agendaType(agendaType)
                .maxCount(maxCount)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
