package com.jobrapido.model;

public enum Direction {
    NORTH, SOUTH, EST, WEST;

    public static Direction fromString(String s) {
        return switch (s.toUpperCase()) {
            case "NORTH" -> NORTH;
            case "SOUTH" -> SOUTH;
            case "EST" -> EST;
            case "WEST" -> WEST;
            default -> throw new IllegalArgumentException("Invalid direction: " + s);
        };
    }
}
