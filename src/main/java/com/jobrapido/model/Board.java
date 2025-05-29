package com.jobrapido.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@NoArgsConstructor
@Slf4j
public class Board {
    private int width;
    private int height;
    private List<Position> obstacles;

    public boolean isValidPosition(Position position) {
        return position.getX() <= this.width && position.getY() <= this.height;
    }

    public boolean isPositionHasObstacle(Position position) {
        return isValidPosition(position) && checkPositionIsObstacle(position);
    }

    private boolean checkPositionIsObstacle(Position position) {
        return obstacles.stream().anyMatch(o -> o.getX() == position.getX() && o.getY() == position.getY());
    }
}
