package com.evoting.repository;

import com.evoting.domain.Agenda;
import com.evoting.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByName(String name);//중복된 이름찾기
    Member findByName(String name);

}
