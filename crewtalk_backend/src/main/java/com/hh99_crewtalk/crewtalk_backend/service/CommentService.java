package com.hh99_crewtalk.crewtalk_backend.service;

import com.hh99_crewtalk.crewtalk_backend.config.auth.PrincipalDetails;
import com.hh99_crewtalk.crewtalk_backend.domain.Comment;
import com.hh99_crewtalk.crewtalk_backend.domain.User;
import com.hh99_crewtalk.crewtalk_backend.dto.CommentRequestDto;
import com.hh99_crewtalk.crewtalk_backend.dto.CommentSaveDto;
import com.hh99_crewtalk.crewtalk_backend.repository.CommentRepository;
import com.hh99_crewtalk.crewtalk_backend.repository.UserRepository;
import com.hh99_crewtalk.crewtalk_backend.util.CommentSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    // 게시글 별 댓글 조회
    @Transactional
    public List<Comment> getComment(Long articleId){
         return commentRepository.findAll(CommentSpecs.withArticleId(articleId));
    }

    // 댓글 작성
    @Transactional
    public Comment createComment(Long articleId, Authentication authentication, CommentRequestDto commentRequestDto){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        // 그 중에서도 primary Key인 id값
        Long user_id = principalDetails.getUser().getId();
        User user = userRepository.findById(user_id).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디가 없습니다.")
        );
        String comments = commentRequestDto.getComments();
        CommentSaveDto commentSaveDto = new CommentSaveDto();
        commentSaveDto.setArticleId(articleId);
        commentSaveDto.setComments(comments);
        commentSaveDto.setCmtUserId(user.getUsername());
        commentSaveDto.setCmtUserName(user.getName());
        commentSaveDto.setStack(user.getStack());

        Comment comment = new Comment(commentSaveDto);
        return commentRepository.save(comment);

    }

    @Transactional
    public Comment updateComment(Long id, Authentication authentication, CommentRequestDto commentRequestDto){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        // 그 중에서도 primary Key인 id값
        Long user_id = principalDetails.getUser().getId();
        User user = userRepository.findById(user_id).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디가 없습니다.")
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디가 없습니다.")
        );

        String username = user.getUsername();
        String commentUsername = comment.getCmtUserId();
        if (!commentUsername.equals(username)){
            throw new IllegalArgumentException("본인이 작성한 댓글만 수정 가능합니다.");
        }
        comment.updateComment(commentRequestDto);
        return comment;
    }

    @Transactional
    public Long deleteComment(Long id, Authentication authentication){
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        // 그 중에서도 primary Key인 id값
        Long user_id = principalDetails.getUser().getId();
        User user = userRepository.findById(user_id).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디가 없습니다.")
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디가 없습니다.")
        );

        String username = user.getUsername();
        String commentUsername = comment.getCmtUserId();
        if (!commentUsername.equals(username)){
            throw new IllegalArgumentException("본인이 작성한 댓글만 삭제 가능합니다.");
        }
        commentRepository.deleteById(id);
        return comment.getId();
    }

}
