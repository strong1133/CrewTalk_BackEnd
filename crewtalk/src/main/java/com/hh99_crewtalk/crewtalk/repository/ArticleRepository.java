package com.hh99_crewtalk.crewtalk.repository;

import com.hh99_crewtalk.crewtalk.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByModifiedAt();
}
