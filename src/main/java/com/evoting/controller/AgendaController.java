package com.evoting.controller;

import com.evoting.controller.dto.AgendaDto;
import com.evoting.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AgendaController {
    private final AgendaService agendaService;

    //관리자 안건 등록
    @PostMapping("/agenda")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<AgendaDto> write(@RequestBody AgendaDto agendaDto) {
        Long agendaId = agendaService.write(agendaDto);
        agendaDto.setAgendaId(agendaId);
        return ResponseEntity.ok().body(agendaDto);
    }

    //모든 안건 조회
    @GetMapping("/agendas")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<AgendaDto>> agendaList() {
        List<AgendaDto> result = agendaService.findAll();
        return ResponseEntity.ok().body(result);
    }

    //단건 조회(id로 조회)
    @GetMapping("/agendas/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<Optional<AgendaDto>> agendaOne(@PathVariable("id") Long id) {
        Optional<AgendaDto> result = agendaService.findById(id);
        return ResponseEntity.ok().body(result);
    }

    //ADMIN
    //관리자 안건 삭제
    @DeleteMapping("/agendas/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity deleteAgenda(@PathVariable("id") Long id){
        if(!agendaService.findById(id).isPresent()){
            throw  new RuntimeException("안건ID가 존재하지 않습니다.");
        }
        agendaService.deleteAgenda(id);
        return ResponseEntity.ok().body("SUCCESS");
    }

    //ADMIN
    //관리자 안건 종료
    @PutMapping("/agendas/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity closeAgenda(@PathVariable("id") Long id){
        if(!agendaService.findById(id).isPresent()){
            throw  new RuntimeException("안건ID가 존재하지 않습니다.");
        }
        agendaService.updateCloseAgenda(id);
        return ResponseEntity.ok().body("SUCCESS");
    }

}
