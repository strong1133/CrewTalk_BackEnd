package com.hh99_crewtalk.crewtalk.exception;

public class InvalidArticleIdException extends RuntimeException {
    public InvalidArticleIdException() {
        super("존재하지 않는 ID의 게시물입니다.");
    }
}
