package com.hh99_crewtalk.crewtalk_backend.util;


import com.hh99_crewtalk.crewtalk_backend.domain.Comment;
import org.springframework.data.jpa.domain.Specification;

public class CommentSpecs {

    public static Specification<Comment> withArticleId(Long articleId) {
        return ((root, query, builder) ->
                builder.equal(root.get("articleId"), articleId)
        );
    }
}
