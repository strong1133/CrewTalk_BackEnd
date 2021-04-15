package com.hh99_crewtalk.crewtalk.dto;

import com.hh99_crewtalk.crewtalk.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponseDto {
    private Long id;

    private String title;

    private String contents;

    private String author;

    private String stack;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.author = article.getMember().getUsername();
        this.stack = article.getMember().getStack();
        this.createdAt = article.getCreatedAt();
        this.modifiedAt = article.getModifiedAt();
    }
}
