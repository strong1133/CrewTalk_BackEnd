package com.hh99_crewtalk.crewtalk_backend.repository;

import com.hh99_crewtalk.crewtalk_backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignupRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
