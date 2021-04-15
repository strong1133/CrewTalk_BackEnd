package com.hh99_crewtalk.crewtalk.exception;

public class InvalidUsernameException extends RuntimeException{
    public InvalidUsernameException() {
        super("존재하지 않는 사용자 ID입니다.");
    }
}
