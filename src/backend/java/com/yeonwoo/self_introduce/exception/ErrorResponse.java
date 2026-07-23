package com.yeonwoo.self_introduce.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String message,
        LocalDateTime timestamp) {

    public ErrorResponse(int status, String message) {
        this(status, message, LocalDateTime.now());
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatus(), errorCode.getMessage());
    }
}
