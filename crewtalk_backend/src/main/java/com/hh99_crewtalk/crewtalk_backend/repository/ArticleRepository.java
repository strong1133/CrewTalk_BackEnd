package com.hh99_crewtalk.crewtalk_backend.repository;


import com.hh99_crewtalk.crewtalk_backend.domain.Article;
import com.hh99_crewtalk.crewtalk_backend.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // 전체 최신순 정렬
    List<Article> findAllByOrderByModifiedAtDesc();
    // 가장 최신 하나만
    List<Article> findTopByOrderByModifiedAtDesc();
    // 전체 스택별 + 페이징
    Page<Article> findAllByStack(String stack, Pageable PageRequest);

    // 작성 아이디별 + 페이징
    Page<Article> findAllByAuthorId(String authorId, Pageable PageRequest);

    // 작성 이름이 포함된 검색 + 페이징
    Page<Article> findByAuthorNameContaining(String name, Pageable PageRequest);
}
