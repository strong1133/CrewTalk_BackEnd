package com.hh99_crewtalk.crewtalk.controller;

import com.hh99_crewtalk.crewtalk.domain.Article;
import com.hh99_crewtalk.crewtalk.dto.ArticleRequestDto;
import com.hh99_crewtalk.crewtalk.dto.ArticleUpdateRequestDto;
import com.hh99_crewtalk.crewtalk.dto.MessageResponseDto;
import com.hh99_crewtalk.crewtalk.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    //게시물 전체 조회 - 최신순
    @GetMapping("/api/article")
    public List<Article> findAllArticle() {
        return articleService.findAllArticle();
    }

    // 특정 게시물 조회
    @GetMapping(value = "/api/article/{id}", produces = "application/json")
    public ResponseEntity<String> findArticleById(@PathVariable Long id) {
        try {
            Article article = articleService.findArticleById(id);

            return new ResponseEntity<>(new JSONObject(article).toString(), HttpStatus.OK);
        } catch (Exception e) {
            MessageResponseDto messageResponseDto = new MessageResponseDto(e.getMessage());

            return new ResponseEntity<>(new JSONObject(messageResponseDto).toString(), HttpStatus.BAD_REQUEST);
        }
    }

    //게시물 작성
    @PostMapping("/api/article")
    public Article createArticle(@RequestBody ArticleRequestDto articleRequestDto) {
        return articleService.createArticle(articleRequestDto);
    }

    //게시물 수정
    @PutMapping(value = "/api/article/{id}", produces = "application/json")
    public ResponseEntity<String> updateArticle(@PathVariable Long id, @RequestBody ArticleUpdateRequestDto articleUpdateRequestDto) {
        try {
            Article article = articleService.findArticleById(id);
            article.updateArticle(articleUpdateRequestDto);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", article.getId());

            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            MessageResponseDto messageResponseDto = new MessageResponseDto(e.getMessage());

            return new ResponseEntity<>(new JSONObject(messageResponseDto).toString(), HttpStatus.FORBIDDEN);
        }
    }

    //게시물 삭제
    @DeleteMapping("/api/article/{id}")
    public Long deleteArticle(@PathVariable Long id) {
        return articleService.deleteArticle(id);
    }

}
