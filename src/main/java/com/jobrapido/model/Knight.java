package com.jobrapido.model;

import com.jobrapido.exception.InvalidStartPositionException;
import com.jobrapido.exception.OutOfTheBoardException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Data
@RequiredArgsConstructor
@Slf4j
public class Knight {

    private final Board board;
    private Position position;

    @SneakyThrows
    public void setStartPosition(Position position) {
        if (!board.isValidPosition(position)) throw new InvalidStartPositionException();
        this.position = position;
    }

    public void rotate(Direction direction) {
        this.position.setDirection(direction);
    }

    @SneakyThrows
    public int move(int steps) {
        var executedSteps = 0;
        for (int i = 0; i < steps; i++) {
            var next = position.copy();
            next.advance();

            if (board.isPositionHasObstacle(next)) {
                log.info("Obstacle met at: {}", next);
                break;
            }

            if (!board.isValidPosition(next)) {
                throw new OutOfTheBoardException();
            }
            this.position = next;
            executedSteps++;
        }
        return executedSteps;
    }

}
