package com.evoting.util;


import com.evoting.controller.dto.AgendaDto;
import com.evoting.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class Scheduler extends SpringBootServletInitializer {

    private final AgendaRepository agendaRepository;

    //매일 0시에 스케줄러로 안건 상태 종료
    @Scheduled(cron = "0 0 0 * * *")
    public void run() {
        LocalDate now = LocalDate.now();

        List<AgendaDto> dtos = agendaRepository.findAll().stream().map(AgendaDto::new).collect(Collectors.toList());

        Iterator<AgendaDto> iter = dtos.iterator();

        while(iter.hasNext()){
            AgendaDto dto = iter.next();
            System.out.println(dto.getEndDate());
            if(now.isAfter(dto.getEndDate())){
                agendaRepository.updateCloseAgenda(dto.getAgendaId());
            }
        }
    }
}
