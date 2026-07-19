package com.yeonwoo.self_introduce.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    // 포맷 상수 정의 (재사용, 성능 최적화)
    private static final DateTimeFormatter LOG_FORMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 한 번에 가져오기
    public static String getFormattedNow() {
        return LocalDateTime.now().format(LOG_FORMATER);
    }
}