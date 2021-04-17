package com.hh99_crewtalk.crewtalk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hh99_crewtalk.crewtalk.dto.ArticleRequestDto;
import com.hh99_crewtalk.crewtalk.dto.ArticleUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "article")
    @OrderBy(value = "createdAt DESC")
    private List<Comment> commentList;

    public Article(ArticleRequestDto articleRequestDto, Member member) {
        this.title = articleRequestDto.getTitle();
        this.contents = articleRequestDto.getContents();
        this.member = member;
    }

    public void updateArticle(ArticleUpdateRequestDto articleUpdateRequestDto) {
        this.title = articleUpdateRequestDto.getTitle();
        this.contents = articleUpdateRequestDto.getContents();
    }


}
