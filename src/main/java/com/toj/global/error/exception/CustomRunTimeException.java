package com.toj.global.error.exception;

import com.toj.global.code.ErrorCode;
import lombok.Getter;

@Getter
public abstract class CustomRunTimeException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomRunTimeException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public CustomRunTimeException(String message) {
        super(message);
        this.errorCode = ErrorCode.NOT_FOUND_EXCEPTION;
    }
}
