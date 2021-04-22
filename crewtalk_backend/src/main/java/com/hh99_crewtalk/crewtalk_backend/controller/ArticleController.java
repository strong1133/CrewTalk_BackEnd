package com.hh99_crewtalk.crewtalk_backend.controller;


import com.hh99_crewtalk.crewtalk_backend.domain.Article;
import com.hh99_crewtalk.crewtalk_backend.domain.User;
import com.hh99_crewtalk.crewtalk_backend.dto.ArticleUpdateRequestDto;
import com.hh99_crewtalk.crewtalk_backend.dto.UserArticleRequestDto;
import com.hh99_crewtalk.crewtalk_backend.repository.ArticleRepository;
import com.hh99_crewtalk.crewtalk_backend.repository.UserRepository;
import com.hh99_crewtalk.crewtalk_backend.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ArticleController {


    private final ArticleService articleService;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    //통신 테스트용
    @GetMapping("/api/hello")
    public String hello() {
        return "hello";
    }


    //게시물 + 페이징
    @GetMapping("/api/article")
    public List<Article> responseEntity(@RequestParam int page, Pageable pageable) {
        return articleService.findAllPageArticle(page);
    }


    //스택별 게시물 조회 + 페이징
    @GetMapping("/api/article/stack")
    public List<Article> findStackArticle(@RequestParam String stack, int page) {
        return articleService.findAllArticleByStack(page, stack);
    }

    //authorId별 게시물 조회 + 페이징
    @GetMapping("/api/article/authorId")
    public List<Article> findAuthorIdArticle(@RequestParam String authorId, int page) {
        return articleService.findAuthorIdArticle(page, authorId);
    }

    // 검색어가 포함된 이름을 갖는 게시물 조회 + 페이징
    @GetMapping("/api/article/search")
    public List<Article> findByArticleNameContaining(@RequestParam String authorName, int page){
        return articleService.findByArticleAuthorNameContaining(authorName, page);
    }

    //게시물 전체 조회 - 최신순
    @GetMapping("/api/article/all")
    public List<Article> findAllArticle() {
        return articleService.findAllArticle();
    }


    //게시물 - 가장 최신 게시물만 조회
    @GetMapping("/api/article/recent")
    public List<Article> findRecentArticle() {
        return articleService.findRecentArticle();
    }

    //내가 작성한 게시물 전체 보기 + 페이징
    @GetMapping("/api/article/cur_user")
    public List<Article> findAllAuthorId(Authentication authentication, int page) {
        return articleService.findAllAuthorId(authentication, page);
    }

    //특정 코드 조회
    @GetMapping("/api/article/{id}")
    public Optional<Article> findArticleById(@PathVariable Long id) {
        return articleService.findArticleById(id);
    }


    //게시물 작성
    @PostMapping("/api/article")
    public Article createArticle(Authentication authentication, @RequestBody UserArticleRequestDto userArticleRequestDto) {
        return articleService.createArticle(authentication, userArticleRequestDto);
    }


    //게시물 수정
    @PutMapping("/api/article/{id}")
    public Map<String, String> updateArticle(Authentication authentication, @PathVariable Long id, @RequestBody ArticleUpdateRequestDto articleUpdateRequestDto) {
        articleService.updateArticle(authentication, id, articleUpdateRequestDto);
        Map<String, String> map = new HashMap<>();
        map.put("Success", "수정이 완료 되었습니다!");
        return map;
    }


    //게시물 삭제
    @DeleteMapping("/api/article/{id}")
    public Map<String, String> deleteArticle(Authentication authentication, @PathVariable Long id) {
        articleService.deleteArticle(authentication, id);
        Map<String, String> map = new HashMap<>();
        map.put("Success", "삭제가 완료 되었습니다!");
        return map;
    }

}
