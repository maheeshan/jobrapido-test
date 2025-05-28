package com.jobrapido.model;

import java.util.Locale;

public enum CommandType {
    START, ROTATE, MOVE;

    public static CommandType fromString(String s) {
        return CommandType.valueOf(s.toUpperCase(Locale.ROOT));
    }
}
