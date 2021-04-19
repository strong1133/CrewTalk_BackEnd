package com.hh99_crewtalk.crewtalk.controller;

import com.hh99_crewtalk.crewtalk.domain.Comment;
import com.hh99_crewtalk.crewtalk.dto.CommentRequestDto;
import com.hh99_crewtalk.crewtalk.dto.CommentUpdateRequestDto;
import com.hh99_crewtalk.crewtalk.dto.MessageResponseDto;
import com.hh99_crewtalk.crewtalk.exception.NotAuthenticatedClientException;
import com.hh99_crewtalk.crewtalk.security.SecurityUtil;
import com.hh99_crewtalk.crewtalk.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CommentController {

    private final CommentService commentService;

    @ResponseBody
    @GetMapping("/api/comment")
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

    @ResponseBody
    @PutMapping(value = "/api/comment/{id}", produces = "application/json")
    public ResponseEntity<String> updateComment(@PathVariable Long id, @RequestBody CommentUpdateRequestDto requestDto) {
        try {
            String currentRequestUsername = SecurityUtil.getCurrentRequestUsername().orElseThrow(() -> new NotAuthenticatedClientException());
            commentService.updateComment(id, requestDto, currentRequestUsername);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);

            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch (Exception e) {
            MessageResponseDto messageResponseDto = new MessageResponseDto(e.getMessage());

            return new ResponseEntity<>(new JSONObject(messageResponseDto).toString(), HttpStatus.FORBIDDEN);
        }

    }
}
