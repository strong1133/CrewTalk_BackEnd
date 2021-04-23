package com.hh99_crewtalk.crewtalk_backend.dto;

import com.hh99_crewtalk.crewtalk_backend.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
// CommentRequestDto를 통해 프론트에서 입력 받은 값을 토큰 인증 정보와 맞물려
// DB에 담아줄 데이터들을 만들어 저장해줄 dto
public class CommentSaveDto {
    private Long articleId;
    private String comments;
    private String cmtUserId;
    private String cmtUserName;
    private String stack;
}
