package com.hh99_crewtalk.crewtalk.service;

import com.hh99_crewtalk.crewtalk.domain.Article;
import com.hh99_crewtalk.crewtalk.dto.ArticleRequestDto;
import com.hh99_crewtalk.crewtalk.dto.ArticleUpdateRequestDto;
import com.hh99_crewtalk.crewtalk.exception.InvalidArticleIdException;
import com.hh99_crewtalk.crewtalk.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    //게시물 전체 조회 - 최신순
    @Transactional
    public List<Article> findAllArticle() {
        return articleRepository.findAllByOrderByModifiedAt();
    }

    @Transactional
    public Article findArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new InvalidArticleIdException());
    }


    //게시물 작성
    @Transactional
    public Article createArticle(ArticleRequestDto articleRequestDto) {
        Article article = new Article(articleRequestDto);
        return articleRepository.save(article);
    }

    //게시물 수정
    @Transactional
    public Long updateArticle(Long id, ArticleUpdateRequestDto articleUpdateRequestDto) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 없습니다.")
        );
        article.updateArticle(articleUpdateRequestDto);
        return article.getId();
    }

    //게시물 삭제
    @Transactional
    public Long deleteArticle(Long id) {
        articleRepository.deleteById(id);
        return id;
    }


}
