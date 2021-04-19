package com.hh99_crewtalk.crewtalk_backend.domain;

import com.hh99_crewtalk.crewtalk_backend.dto.CommentRequestDto;
import com.hh99_crewtalk.crewtalk_backend.dto.CommentSaveDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long articleId;

    @Column
    private String comments;

    @Column
    private String cmtUserId;

    @Column
    private String cmtUsername;

    @Column
    private String stack;

    public Comment(CommentSaveDto commentSaveDto) {
        this.articleId = commentSaveDto.getArticleId();
        this.comments = commentSaveDto.getComments();
        this.cmtUserId = commentSaveDto.getCmtUserId();
        this.cmtUsername = commentSaveDto.getCmtUserName();
        this.stack = commentSaveDto.getStack();
    }

    public void updateComment(CommentRequestDto commentRequestDto){
        this.comments = commentRequestDto.getComments();
    }
}
