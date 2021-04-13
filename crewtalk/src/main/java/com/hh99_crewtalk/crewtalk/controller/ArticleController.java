package com.hh99_crewtalk.crewtalk.controller;

import com.hh99_crewtalk.crewtalk.domain.Article;
import com.hh99_crewtalk.crewtalk.dto.ArticleRequestDto;
import com.hh99_crewtalk.crewtalk.dto.ArticleUpdateRequestDto;
import com.hh99_crewtalk.crewtalk.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    //게시물 전체 조회 - 최신순
    @GetMapping("/api/article")
    public List<Article> findAllArticle() {
        return articleService.findAllArticle();
    }

    //게시물 전체 조회 - 최신순
    @GetMapping("/api/article/{id}")
    public Optional<Article> findArticleById(@PathVariable Long id) {
        return articleService.findArticleById(id);
    }

    //게시물 작성
    @PostMapping("/api/article")
    public Article createArticle(@RequestBody ArticleRequestDto articleRequestDto) {
        return articleService.createArticle(articleRequestDto);
    }

    //게시물 수정
    @PutMapping("/api/article/{id}")
    public Long updateArticle(@PathVariable Long id, @RequestBody ArticleUpdateRequestDto articleUpdateRequestDto) {
        return articleService.updateArticle(id, articleUpdateRequestDto);
    }

    //게시물 삭제
    @DeleteMapping("/api/article/{id}")
    public Long deleteArticle(@PathVariable Long id) {
        return articleService.deleteArticle(id);
    }

}
