package com.hh99_crewtalk.crewtalk.service;

import com.hh99_crewtalk.crewtalk.domain.Article;
import com.hh99_crewtalk.crewtalk.domain.Member;
import com.hh99_crewtalk.crewtalk.dto.ArticleRequestDto;
import com.hh99_crewtalk.crewtalk.dto.ArticleResponseDto;
import com.hh99_crewtalk.crewtalk.dto.ArticleUpdateRequestDto;
import com.hh99_crewtalk.crewtalk.exception.InvalidArticleIdException;
import com.hh99_crewtalk.crewtalk.exception.InvalidUsernameException;
import com.hh99_crewtalk.crewtalk.exception.NotAuthorizedException;
import com.hh99_crewtalk.crewtalk.repository.ArticleRepository;
import com.hh99_crewtalk.crewtalk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    private final MemberRepository memberRepository;

    //게시물 전체 조회 - 최신순
    @Transactional
    public List<ArticleResponseDto> getAllArticle() {
        List<Article> articleList = articleRepository.findAllByOrderByModifiedAt();

        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>(articleList.size());
        articleList.forEach(article -> {
            articleResponseDtoList.add(new ArticleResponseDto(article));
        });

        return articleResponseDtoList;
    }

    @Transactional
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new InvalidArticleIdException());
    }

    public List<ArticleResponseDto> getArticleListByUsername(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new InvalidUsernameException());

        List<Article> articleList = member.getArticleList();

        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>(articleList.size());
        articleList.forEach(article -> {
            articleResponseDtoList.add(new ArticleResponseDto(article));
        });

        return articleResponseDtoList;
    }


    //게시물 작성
    @Transactional
    public Article createArticle(ArticleRequestDto articleRequestDto, String username) {
        Member currentRequestMember = memberRepository.findByUsername(username).orElseThrow(() -> new InvalidUsernameException());

        Article article = new Article(articleRequestDto, currentRequestMember);
        return articleRepository.save(article);
    }

    //게시물 수정
    @Transactional
    public Long updateArticle(Long id, ArticleUpdateRequestDto articleUpdateRequestDto, String requestedUsername) {
        Member currentRequestMember = memberRepository.findByUsername(requestedUsername).orElseThrow(() -> new InvalidUsernameException());

        Article article = articleRepository.findById(id).orElseThrow(() -> new InvalidArticleIdException());

        if (article.getMember().getId() != currentRequestMember.getId()) {
            throw new NotAuthorizedException();
        }

        article.updateArticle(articleUpdateRequestDto);
        return article.getId();
    }

    //게시물 삭제
    @Transactional
    public Long deleteArticle(Long id, String username) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new InvalidArticleIdException());

        if (article.getMember().getUsername() != username) {
            throw new NotAuthorizedException();
        }

        articleRepository.deleteById(id);
        return id;
    }


}
