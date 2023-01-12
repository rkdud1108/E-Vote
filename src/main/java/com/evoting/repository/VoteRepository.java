package com.evoting.repository;

import com.evoting.controller.dto.VoteDto;
import com.evoting.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VoteRepository extends JpaRepository<Vote, Long> {

    //투표결과 리스트형식으로 보여주기
    @Query("select v from Vote v where v.agenda.id = :id")
    List<Vote> findAgendaIdResult(Long id);

    //Vote findByMember_Name(String name);
}
