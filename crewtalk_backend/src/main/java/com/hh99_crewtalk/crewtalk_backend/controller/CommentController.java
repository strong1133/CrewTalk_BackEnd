package com.hh99_crewtalk.crewtalk_backend.controller;

import com.hh99_crewtalk.crewtalk_backend.domain.Comment;
import com.hh99_crewtalk.crewtalk_backend.dto.CommentRequestDto;
import com.hh99_crewtalk.crewtalk_backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 조회
    @GetMapping("/api/comment")
    public List<Comment> getComment(@RequestParam Long articleId){
        return commentService.getComment(articleId);
    }


    // 댓글 작성 + 인가
    @PostMapping("/api/comment")
    public Comment createComment(@RequestParam  Long articleId, Authentication authentication, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.createComment(articleId, authentication, commentRequestDto);
    }

    // 댓글 수정 + 인가
    @PutMapping("/api/comment/{id}")
    public Map<String, String> updateComment(@PathVariable Long id, Authentication authentication, @RequestBody CommentRequestDto commentRequestDto){
        Map<String, String> map = new HashMap<>(); //JSON형태의 성공 메세지
        map.put("Success","댓글 수정이 완료되었습니다.");
        commentService.updateComment(id, authentication, commentRequestDto);
        return map;
    }

    // 댓글 삭제 + 인가
    @DeleteMapping("/api/comment/{id}")
    public Map<String, String> deleteComment(@PathVariable Long id, Authentication authentication){
        Map<String, String> map = new HashMap<>(); //JSON형태의 성공 메세지
        map.put("Success","댓글 삭제가 완료되었습니다.");
        commentService.deleteComment(id, authentication);
        return map;
    }
}
