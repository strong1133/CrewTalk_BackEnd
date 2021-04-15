package com.hh99_crewtalk.crewtalk.repository;


import com.hh99_crewtalk.crewtalk.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);

    List<Member> findAllByStack(String stack);
}
