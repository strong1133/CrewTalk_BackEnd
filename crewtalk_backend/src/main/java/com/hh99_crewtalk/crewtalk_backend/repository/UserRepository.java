package com.hh99_crewtalk.crewtalk_backend.repository;


import com.hh99_crewtalk.crewtalk_backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAllByStack(String stack);
}
