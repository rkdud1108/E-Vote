package com.evoting.repository;

import com.evoting.domain.Agenda;
import com.evoting.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

}
