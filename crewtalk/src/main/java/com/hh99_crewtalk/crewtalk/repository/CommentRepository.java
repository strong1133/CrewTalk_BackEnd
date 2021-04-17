package com.hh99_crewtalk.crewtalk.repository;

import com.hh99_crewtalk.crewtalk.domain.Article;
import com.hh99_crewtalk.crewtalk.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticleOrderByCreatedAtDesc(Article article);
}
