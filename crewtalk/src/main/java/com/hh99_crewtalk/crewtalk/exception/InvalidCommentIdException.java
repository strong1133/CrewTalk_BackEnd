package com.hh99_crewtalk.crewtalk.exception;

public class InvalidCommentIdException extends RuntimeException {
    public InvalidCommentIdException() {
        super("존재하지 않는 댓글 ID입니다.");
    }
}
