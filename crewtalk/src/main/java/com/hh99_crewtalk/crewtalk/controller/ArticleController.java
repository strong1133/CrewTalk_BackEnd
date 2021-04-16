package com.hh99_crewtalk.crewtalk.controller;

import com.hh99_crewtalk.crewtalk.domain.Article;
import com.hh99_crewtalk.crewtalk.dto.ArticleRequestDto;
import com.hh99_crewtalk.crewtalk.dto.ArticleResponseDto;
import com.hh99_crewtalk.crewtalk.dto.ArticleUpdateRequestDto;
import com.hh99_crewtalk.crewtalk.dto.MessageResponseDto;
import com.hh99_crewtalk.crewtalk.exception.NotAuthenticatedClientException;
import com.hh99_crewtalk.crewtalk.security.SecurityUtil;
import com.hh99_crewtalk.crewtalk.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
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
    @GetMapping(value = "/api/article", produces = "application/json")
    public ResponseEntity<String> getAllArticle() {
        List<ArticleResponseDto> articleResponseDtoList = articleService.getAllArticle();

        return new ResponseEntity<>(new JSONArray(articleResponseDtoList).toString(), HttpStatus.OK);
    }

    // 특정 게시물 조회
    @GetMapping(value = "/api/article/{id}", produces = "application/json")
    public ResponseEntity<String> getArticleDetail(@PathVariable Long id) {
        try {
            Article article = articleService.getArticleById(id);
            ArticleResponseDto articleResponseDto = new ArticleResponseDto(article);

            return new ResponseEntity<>(new JSONObject(articleResponseDto).toString(), HttpStatus.OK);
        } catch (Exception e) {
            MessageResponseDto messageResponseDto = new MessageResponseDto(e.getMessage());

            return new ResponseEntity<>(new JSONObject(messageResponseDto).toString(), HttpStatus.BAD_REQUEST);
        }
    }

    //게시물 작성
    @PostMapping("/api/article")
    public Article createArticle(@RequestBody ArticleRequestDto articleRequestDto) {
        String currentRequestUsername = SecurityUtil.getCurrentRequestUsername().orElseThrow(() -> new NotAuthenticatedClientException());
        return articleService.createArticle(articleRequestDto, currentRequestUsername);
    }

    //게시물 수정
    @PutMapping(value = "/api/article/{id}", produces = "application/json")
    public ResponseEntity<String> updateArticle(@PathVariable Long id, @RequestBody ArticleUpdateRequestDto articleUpdateRequestDto) {
        try {
            String currentRequestUsername = SecurityUtil.getCurrentRequestUsername().orElseThrow(() -> new NotAuthenticatedClientException());
            articleService.updateArticle(id, articleUpdateRequestDto, currentRequestUsername);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);

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
