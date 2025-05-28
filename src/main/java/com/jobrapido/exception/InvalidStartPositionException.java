package com.jobrapido.exception;

import com.jobrapido.model.OutputStatus;

public class InvalidStartPositionException extends GenericException {
    public InvalidStartPositionException() {
        super("Invalid starting position");
    }

    @Override
    public OutputStatus getCode() {
        return OutputStatus.INVALID_START_POSITION;
    }
}
