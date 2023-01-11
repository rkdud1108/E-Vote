package com.evoting.repository;

import com.evoting.controller.dto.AgendaDto;
import com.evoting.domain.Agenda;
import com.evoting.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}
