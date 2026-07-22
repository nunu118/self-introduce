package com.yeonwoo.self_introduce.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;

// 501, 502, 503, 504 등과 같은 에러는 직접 만드는 일이 잘 없고, 로드 밸런서가 응답이 이상할 때 대신 던져줌
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 Bad Request : 비즈니스 로직 상의 잘못된 인자 전달 (ex. 유효성 검사 실패)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException e) {
        log.warn("Bad Request: {}", e.getMessage());

        String message = e.getMessage() != null ? e.getMessage() : "잘못된 요청입니다.";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, message));
    }
    // 404 Not Found : DB에서 원하는 데이터를 찾지 못함(NoSuchElementException) or 존재하지 않는 Static Resource/API end point 호출한 경우(NoResourceFoundException)
    @ExceptionHandler({NoSuchElementException.class, NoResourceFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(Exception e) {
        log.warn("Not Found: {}", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, "요청하신 리소스를 찾을 수 없습니다."));
    }
    // 500 Internal Server Error : 예상치 못한 기타 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        // 에러 메시지, 발생 위치까지 추적하여 원인 파악을 쉽게 하기 위함
        log.error("Internal Server Error", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(500, "서버 내부 오류가 발생했습니다. 잠시 후 다시 시도해 주세요."));
    }
}
