package com.jobrapido.exception;

import com.jobrapido.model.OutputStatus;

public class OutOfTheBoardException extends GenericException {
    public OutOfTheBoardException() {
        super("Knight moved out of the board !");
    }

    @Override
    public OutputStatus getCode() {
        return OutputStatus.OUT_OF_THE_BOARD;
    }
}
