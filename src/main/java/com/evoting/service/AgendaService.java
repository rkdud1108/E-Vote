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
    public Long write(AgendaDto agendaDto){

        //안건 타입이 FREE/ LIMIT인지 판별
        String nowType = agendaDto.getAgendaType().toString();

        //자유경쟁인 경우
        if(nowType.equals("FREE")){
            agendaDto.setMaxCount(null);//count 컬럼 사용하지 않는다.
        }
        //agendaRepository.findById(agendaDto.getAgendaId()).orElseThrow();

        Agenda entity = agendaRepository.save(agendaDto.toEntity());
        return entity.getId();
    }

    //모든 안건 조회
    public List<AgendaDto> findAll(){
        return agendaRepository.findAll().stream().map(AgendaDto::new).collect(Collectors.toList());
    }

    //하나의 안건 조회
    public Optional<AgendaDto> findById(Long id) {
        return agendaRepository.findById(id).map(AgendaDto::new);
    }

    //안건 삭제
    @Transactional
    public void deleteAgenda(Long agendaId){
        agendaRepository.deleteById(agendaId);
    }

    //안건 종료
    @Transactional
    public void updateCloseAgenda(Long agendaId) {
        agendaRepository.updateCloseAgenda(agendaId);
    }
}
