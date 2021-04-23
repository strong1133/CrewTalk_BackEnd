package com.hh99_crewtalk.crewtalk_backend.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
// 프론트에 댓글 작성 요청시 값을 받아줄 dto
public class CommentRequestDto {

    private String comments;
}
