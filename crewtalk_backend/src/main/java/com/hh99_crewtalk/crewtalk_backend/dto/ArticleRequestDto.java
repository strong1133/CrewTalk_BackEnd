package com.hh99_crewtalk.crewtalk_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequestDto {

    private String title;

    private String contents;

    private String authorId;

    private String authorName;

    private String stack;
}
