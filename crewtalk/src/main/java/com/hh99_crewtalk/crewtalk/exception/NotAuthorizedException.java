package com.hh99_crewtalk.crewtalk.exception;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException() {
        super("요청에 필요한 권한이 없습니다.");
    }
}
