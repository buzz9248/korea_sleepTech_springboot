package com.example.korea_sleepTech_springboot.common.utils;

// Java의 LocalDateTime
//      : LocalDateTime을 문자열로 출력하면 YYYY-MM-DDTHH:mm:ss
// MySQL의 DATETIME
//      : YYYY-MM-DD HH:mm:ss
// >> 두 언어 사이의 형식의 차이를 일치시키기 위함

import com.example.korea_sleepTech_springboot.entity.Post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// === 포맷팅(formatting) === //
// : 사람이 읽기 편하게 함 + 시스템 간 통신에 일관된 형식 보장

/*
* 날짜/시간 포맷 유틸 클래스
* : LocalDateTime - 문자열 간 변환
* > 시스템 간 통신 및 로그 출력을 위한 통일된 포맷 제공
* */

public class DateUtils {
    // == 포맷 정의 == //
    // : constants 패키지에서 정의 가능
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /*
    * LocalDateTime >>>>> 문자열
    * @Param dateTime 변환할 날짜/시간 객체
    * @return 포맷 형식의 문자열
    * */
    public static String format(LocalDateTime dateTime) {
        return (dateTime != null) ? dateTime.format(FORMATTER) : null;
    }


    /*
     * 문자열 >>>>> LocalDateTime
     * @Param 포맷 형식의 문자열
     * @return dateTime 날짜/시간 객체
     * */
    public static LocalDateTime parse(String dateString) {
        return (dateString != null && !dateString.isEmpty()) ? LocalDateTime.parse(dateString, FORMATTER) : null;
    }

    /*
    * 현재 시간 문자열 반환
    * */
    public static String nowFormated() {
        return format(LocalDateTime.now());
    }


//    LocalDateTime now = LocalDateTime.now();
//    String formated = now.format(formatter);

    // formated 출력 시: 2025-05-29 10:28:30
}

class DateResponseDto {
    private String title;
    private String content;
    private String createdAt;

    public DateResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = DateUtils.format(post.getCreatedAt()); // 날짜 형식을 포맷터로 T 구분문자를 없애고 반환
    }
}
