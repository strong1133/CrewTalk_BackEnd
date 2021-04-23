package com.hh99_crewtalk.crewtalk_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

// 프론트에서 게시글 작성시 입력값을 받아주는 dto
public class UserArticleRequestDto {
    private String title;
    private String contents;
}
