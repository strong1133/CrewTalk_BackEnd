package com.hh99_crewtalk.crewtalk.controller;

import com.hh99_crewtalk.crewtalk.domain.Comment;
import com.hh99_crewtalk.crewtalk.dto.CommentRequestDto;
import com.hh99_crewtalk.crewtalk.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;

    @ResponseBody
    @GetMapping("/api/comment_list")
    public List<Comment> getCommentListByArticleId(@RequestParam Long article_id) {
        List<Comment> commentList = commentService.getCommentListByArticle(article_id);
        return commentList;
    }

    @ResponseBody
    @PostMapping("/api/comment")
    public Comment createComment(@RequestBody CommentRequestDto requestDto) {
        Comment comment = commentService.createComment(requestDto);
        return comment;
    }
}
