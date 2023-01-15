package com.evoting.repository;

import com.evoting.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByName(String name);

    //name을 기준으로 member정보를 가져올 때 권한 정보도 같이 가져옴
    @EntityGraph(attributePaths = "authorities")
    Optional<Member> findOneWithAuthoritiesByName(String name);
}
