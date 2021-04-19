package com.hh99_crewtalk.crewtalk.domain;

import com.hh99_crewtalk.crewtalk.dto.CommentUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(nullable = false)
    private String contents;

    public Comment(Article article, Member member, String contents) {
        this.article = article;
        this.member = member;
        this.contents = contents;
    }

    public void update(CommentUpdateRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }
}
