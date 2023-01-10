package com.evoting.controller;

import com.evoting.controller.dto.AgendaDto;
import com.evoting.domain.Agenda;
import com.evoting.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AgendaController {
    private final AgendaService agendaService;

    //안건 등록
    @PostMapping("/admin/agenda/write")
    public Long write(@RequestBody @Valid AgendaDto request) {
        return agendaService.write(request);
    }

    //모든 안건 조회
    @GetMapping("/api/agendalist")
    public List<AgendaDto> agendaList() {
        List<AgendaDto> result = agendaService.findAll();
        return result;
    }

    //단건 조회(id로 조회)
    @GetMapping("/api/agendaOne")
    public Optional<Agenda> agendaOne(@RequestBody @Valid AgendaDto request) {
        Optional<Agenda> result = agendaService.findById(request.getAgendaId());
        return result;
    }

    //현재투표중인지 조회(id로 조회)
//    @GetMapping("/api/agendaStatus")
//    public Optional<Agenda> agendaStatus(@RequestBody @Valid AgendaDto request) {
//
//    }

    //해당 안건에 찬,반,기권 의사 표시

}
