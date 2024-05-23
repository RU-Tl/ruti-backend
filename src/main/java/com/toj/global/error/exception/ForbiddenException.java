package com.toj.global.error.exception;

import com.toj.global.code.ErrorCode;

public class ForbiddenException extends CustomRunTimeException {

    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}
