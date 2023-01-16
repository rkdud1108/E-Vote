package com.evoting.repository;

import com.evoting.domain.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    @Transactional
    @Modifying
    @Query("update Agenda a set a.agendaStatus = 'END' where a.id = :id")
    void updateCloseAgenda(Long id);
}
