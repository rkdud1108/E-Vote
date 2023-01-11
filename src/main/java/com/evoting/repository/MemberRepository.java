package com.evoting.repository;

import com.evoting.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findAllByName(String name);//중복된 이름찾기

    Member findByName(String name);
}
