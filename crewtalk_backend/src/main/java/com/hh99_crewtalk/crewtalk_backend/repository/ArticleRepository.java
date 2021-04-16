package com.hh99_crewtalk.crewtalk_backend.repository;


import com.hh99_crewtalk.crewtalk_backend.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByModifiedAtDesc();
    List<Article> findTopByOrderByModifiedAtDesc();
}
