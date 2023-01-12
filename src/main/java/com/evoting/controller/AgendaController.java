package com.evoting.controller;

import com.evoting.controller.dto.AgendaDto;
import com.evoting.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AgendaController {
    private final AgendaService agendaService;

    //ADMIN
    //관리자 안건 등록
    @PostMapping("/agenda")
    public ResponseEntity<AgendaDto> write(@RequestBody AgendaDto agendaDto) {
        agendaService.write(agendaDto);

        return new ResponseEntity(agendaDto, HttpStatus.OK);
    }

    //모든 안건 조회
    @GetMapping("/agenda")
    public List<AgendaDto> agendaList() {
        List<AgendaDto> result = agendaService.findAll();
        return result;
    }

    //단건 조회(id로 조회)
    @GetMapping("/agenda/{id}")
    public Optional<AgendaDto> agendaOne(@PathVariable("id") Long id) {
        Optional<AgendaDto> result = agendaService.findById(id);
        return result;
    }

    //ADMIN
    //관리자 안건 삭제
    @DeleteMapping("/agenda/{id}")
    public void deleteAgenda(@PathVariable("id") Long id){
        agendaService.deleteAgenda(id);
    }

}
