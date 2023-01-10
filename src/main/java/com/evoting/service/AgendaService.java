package com.evoting.service;

import com.evoting.controller.dto.AgendaDto;
import com.evoting.domain.Agenda;
import com.evoting.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AgendaService {
    private final AgendaRepository agendaRepository;

    //안건 저장
    @Transactional
    public Long write(AgendaDto params){
        Agenda entity = agendaRepository.save(params.toEntity());
        return entity.getId();
    }

    //모든 안건 조회
    public List<AgendaDto> findAll(){
        return agendaRepository.findAll().stream().map(AgendaDto::new).collect(Collectors.toList());
    }

    //하나의 안건 조회
    public Optional<Agenda> findById(Long id){
        return agendaRepository.findById(id);
    }

    //현재 투표중인 안건인지
    public Optional<Agenda> checkAgendaStatus(Long id){
        return agendaRepository.findById(id);
    }
}
