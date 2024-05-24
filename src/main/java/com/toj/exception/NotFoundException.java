package com.toj.exception;

import com.toj.global.code.ErrorCode;
import com.toj.global.error.exception.CustomRunTimeException;

public class NotFoundException extends CustomRunTimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }

}
