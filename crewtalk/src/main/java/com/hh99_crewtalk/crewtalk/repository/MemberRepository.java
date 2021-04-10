package com.hh99_crewtalk.crewtalk.repository;


import com.hh99_crewtalk.crewtalk.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByStack(String stack);
}
