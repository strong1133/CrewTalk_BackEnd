package com.hh99_crewtalk.crewtalk.service;

import com.hh99_crewtalk.crewtalk.domain.Article;
import com.hh99_crewtalk.crewtalk.domain.Comment;
import com.hh99_crewtalk.crewtalk.domain.Member;
import com.hh99_crewtalk.crewtalk.dto.CommentRequestDto;
import com.hh99_crewtalk.crewtalk.exception.InvalidArticleIdException;
import com.hh99_crewtalk.crewtalk.exception.InvalidUsernameException;
import com.hh99_crewtalk.crewtalk.repository.ArticleRepository;
import com.hh99_crewtalk.crewtalk.repository.CommentRepository;
import com.hh99_crewtalk.crewtalk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public List<Comment> getCommentListByArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new InvalidArticleIdException());

        List<Comment> commentList = commentRepository.findAllByArticleOrderByCreatedAtDesc(article);
        return commentList;
    }

    public Comment createComment(CommentRequestDto requestDto) {
        Article article = articleRepository.findById(requestDto.getArticleId()).orElseThrow(() -> new InvalidArticleIdException());
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(() -> new InvalidUsernameException());

        Comment comment = new Comment(article, member, requestDto.getContents());
        commentRepository.save(comment);

        return comment;
    }
}
