package com.yeonwoo.self_introduce.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_INPUT(400, "잘못된 입력값입니다."),
    NOT_FOUND(404, "대상을 찾을 수 없습니다."),
    SERVER_ERROR(500, "서버에 오류가 발생했습니다."),
    DUPLICATE_EMAIL(409, "이미 가입된 이메일입니다."),
    ENCRYPTION_ERROR(500, "데이터 암호화 중 오류가 발생했습니다."),
    DECRYPTION_ERROR(500, "데이터 복호화 중 오류가 발생했습니다.");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
