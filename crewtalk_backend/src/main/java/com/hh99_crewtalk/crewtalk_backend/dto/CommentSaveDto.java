package com.hh99_crewtalk.crewtalk_backend.dto;

import com.hh99_crewtalk.crewtalk_backend.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class CommentSaveDto {
    private Long articleId;
    private String comments;
    private String cmtUserId;
    private String cmtUserName;
    private String stack;
}
