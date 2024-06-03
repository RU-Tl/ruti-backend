package com.toj.global.code;

import lombok.Getter;

import static com.toj.global.code.HttpStatusCode.*;

@Getter
public enum ErrorCode {

    TEST(INTERNAL_SERVER_ERROR, false, "T001", "business exception test"),

    /**
     * 403 Forbidden (권한 등의 이유로 허용하지 않는 요청)
     */
    FORBIDDEN_EXCEPTION(FORBIDDEN, false, "FB001", "허용하지 않는 요청입니다"),


    /**
     * 404 Not Found
     */
    NOT_FOUND_EXCEPTION(NOT_FOUND, false, "NF001", "존재하지 않는 엔티티 정보입니다."),
    NOT_EXISTS_DAILY(NOT_FOUND, false, "NF002", "존재하지 않는 일상입니다."),
    NOT_EXISTS_MEMBER(NOT_FOUND, false, "NF003", "존재하지 않는 회원입니다.");



    private final HttpStatusCode statusCode;
    private final boolean notification;
    private final String code;
    private final String message;

    ErrorCode(HttpStatusCode statusCode, boolean notification, String code, String message) {
        this.statusCode = statusCode;
        this.notification = notification;
        this.code = code;
        this.message = message;
    }
}
