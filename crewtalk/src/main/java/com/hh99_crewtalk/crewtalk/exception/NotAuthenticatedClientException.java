package com.hh99_crewtalk.crewtalk.exception;

public class NotAuthenticatedClientException extends RuntimeException {
    public NotAuthenticatedClientException() {
        super("인증되지 않은 클라이언트의 요청입니다.");
    }
}
