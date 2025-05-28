package com.jobrapido.exception;

import com.jobrapido.model.OutputStatus;

public class GenericException extends RuntimeException {

    public GenericException(String message) {
        super(message);
    }

    public OutputStatus getCode() {
        return OutputStatus.GENERIC_ERROR;
    }
}
