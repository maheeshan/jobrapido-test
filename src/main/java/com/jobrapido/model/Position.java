package com.jobrapido.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
    private int x;
    private int y;
    private Direction direction;

    public Position copy() {
        return new Position(this.x, this.y, this.direction);
    }

    public void advance() {
        switch (direction) {
            case EST -> this.x++;
            case WEST -> this.x--;
            case NORTH -> this.y++;
            case SOUTH -> this.y--;
        }
    }
}
