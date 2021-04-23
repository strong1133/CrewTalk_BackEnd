package com.hh99_crewtalk.crewtalk_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// UserArticleRequestDto 를 통해 프론트에서 입력받은 값들과 토큰 인증 정보에 따른
// 유저 정보를 추려 db에 저장하기 위한 그릇 역활
public class ArticleRequestDto {

    private String title;

    private String contents;

    private String authorId;

    private String authorName;

    private String stack;
}
