package com.evoting.repository;

import com.evoting.controller.dto.ResponseVoteInterface;
import com.evoting.controller.dto.VoteDto;
import com.evoting.controller.dto.VoteResponseDto;
import com.evoting.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VoteRepository extends JpaRepository<Vote, Long> {

    //투표결과 리스트형식으로 보여주기
    @Query("select v from Vote v where v.agenda.id = :id")
    List<Vote> findAdminAgendaIdResult(Long id);

    //투표결과 찬반 형식으로 보여주기
    @Query("select v.voteType as type, sum(v.count) as count from Vote v " +
            "where v.agenda.id = :id " +
            "group by v.voteType")
    List<ResponseVoteInterface> findAgendaIdResult(Long id);

    //Vote findByMember_Name(String name);

    //해당 안건,멤버가 투표한 합계 계산
    @Query("select coalesce(sum(v.count),0) as cnt from Vote v " +
            "where v.member.id = :memberId " +
            "and v.agenda.id = :agendaId")
    Integer findMemberCntAgendaIdResult(Long memberId, Long agendaId);
}
