package com.evoting.controller;

import com.evoting.controller.dto.AgendaDto;
import com.evoting.controller.dto.response.BaseResponse;
import com.evoting.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AgendaController {
    private final AgendaService agendaService;

    //관리자 안건 등록
    @PostMapping("/agenda")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AgendaDto> write(@RequestBody AgendaDto agendaDto) {
        Long agendaId = agendaService.write(agendaDto);
        agendaDto.setAgendaId(agendaId);
        return new ResponseEntity<>(agendaDto, HttpStatus.OK);
    }

    //모든 안건 조회
    @GetMapping("/agendas")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public List<AgendaDto> agendaList() {
        List<AgendaDto> result = agendaService.findAll();
        return result;
    }

    //단건 조회(id로 조회)
    @GetMapping("/agendas/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public Optional<AgendaDto> agendaOne(@PathVariable("id") Long id) {
        Optional<AgendaDto> result = agendaService.findById(id);
        return result;
    }

    //ADMIN
    //관리자 안건 삭제
    @DeleteMapping("/agendas/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public BaseResponse deleteAgenda(@PathVariable("id") Long id){
        agendaService.deleteAgenda(id);
        return new BaseResponse();
    }

    //ADMIN
    //관리자 안건 종료
    @PutMapping("/agendas/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public BaseResponse closeAgenda(@PathVariable("id") Long id){
        agendaService.updateCloseAgenda(id);
        return new BaseResponse();

    }

}
