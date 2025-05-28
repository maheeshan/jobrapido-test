package com.jobrapido.model;

import com.jobrapido.exception.InvalidStartPositionException;
import com.jobrapido.exception.OutOfTheBoardException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Data
@RequiredArgsConstructor
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
    public void move(int steps) {
        for (int i = 0; i < steps; i++) {
            var next = position.copy();
            next.advance();
            if (!board.isValidPosition(next)) {
                throw new OutOfTheBoardException();
            }

            if (board.isPositionHasObstacle(next)) {
                break;
            }
            this.position = next;
        }
    }

}
