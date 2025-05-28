package com.jobrapido.model;

import lombok.Data;

import java.util.List;

@Data
public class Board {
    private int width;
    private int height;
    private List<Position> obstacles;

    public boolean isValidPosition(Position position) {
        return position.getX() <= this.width && position.getY() <= this.height;
    }

    public boolean isPositionHasObstacle(Position position) {
        return isValidPosition(position) && obstacles.contains(position);
    }
}
