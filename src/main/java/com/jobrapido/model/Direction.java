package com.jobrapido.model;

public enum Direction {
    NORTH, SOUTH, EAST, WEST;

    public static Direction fromString(String s) {
        return switch (s.toUpperCase()) {
            case "NORTH" -> NORTH;
            case "SOUTH" -> SOUTH;
            case "EAST" -> EAST;
            case "WEST" -> WEST;
            default -> throw new IllegalArgumentException("Invalid direction: " + s);
        };
    }
}
