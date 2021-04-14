package com.hh99_crewtalk.crewtalk_backend.domain;


import com.hh99_crewtalk.crewtalk_backend.dto.ArticleRequestDto;
import com.hh99_crewtalk.crewtalk_backend.dto.ArticleUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String stack;

    public Article(ArticleRequestDto articleRequestDto){
        this.title = articleRequestDto.getTitle();
        this.contents = articleRequestDto.getContents();
        this.author = articleRequestDto.getAuthor();
        this.stack = articleRequestDto.getStack();
    }

    public void updateArticle(ArticleUpdateRequestDto articleUpdateRequestDto){
        this.title = articleUpdateRequestDto.getTitle();
        this.contents = articleUpdateRequestDto.getContents();
    }


}
