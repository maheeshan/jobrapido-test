package com.jobrapido.model;

import com.jobrapido.exception.InvalidStartPositionException;
import com.jobrapido.exception.OutOfTheBoardException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KnightTest {

    @Mock
    Position currentPosition;

    @Mock
    Position nextPosition;

    @Mock
    Board board;

    @InjectMocks
    Knight knight;

    @Test
    void setStartPosition_validPosition() {

        when(board.isValidPosition(currentPosition)).thenReturn(true);

        knight.setStartPosition(currentPosition);

        assertEquals(currentPosition, knight.getPosition());
    }

    @Test
    void setStartPosition_invalidPosition_throwsException() {
        when(board.isValidPosition(currentPosition)).thenReturn(false);

        assertThrows(InvalidStartPositionException.class, () ->
                knight.setStartPosition(currentPosition));
    }

    @Test
    void rotate_setsNewDirection() {
        knight.setPosition(currentPosition);

        Direction direction = Direction.NORTH;
        knight.rotate(direction);

        verify(currentPosition).setDirection(direction);
    }

    @ParameterizedTest
    @MethodSource("directionAndStepsProvider")
    void move_withRealPosition_success(Direction direction, int steps) {
        //given
        Position start = new Position(3, 3, direction);
        when(board.isValidPosition(any(Position.class))).thenReturn(true);
        when(board.isPositionHasObstacle(any(Position.class))).thenReturn(false);

        knight.setStartPosition(start);

        //when
        knight.move(steps);

        //then
        Position finalPos = knight.getPosition();
        assertNotNull(finalPos);
        assertNotEquals(start, finalPos);
        assertEquals(direction, finalPos.getDirection());
    }

    private static Stream<Arguments> directionAndStepsProvider() {
        return Stream.of(
                Arguments.arguments(Direction.NORTH, 1),
                Arguments.arguments(Direction.EAST, 2),
                Arguments.arguments(Direction.SOUTH, 3),
                Arguments.arguments(Direction.WEST, 4)
        );
    }

    @Test
    void move_hitsObstacle_doesNotMove() {
        knight.setPosition(currentPosition);

        when(currentPosition.copy()).thenReturn(nextPosition);
        when(board.isPositionHasObstacle(nextPosition)).thenReturn(true);

        knight.move(1);

        assertEquals(currentPosition, knight.getPosition());
        verify(nextPosition).advance();
    }

    @Test
    void move_outOfBounds_throwsException() {
        knight.setPosition(currentPosition);

        when(currentPosition.copy()).thenReturn(nextPosition);
        when(board.isValidPosition(nextPosition)).thenReturn(false);

        assertThrows(OutOfTheBoardException.class, () -> knight.move(1));
        verify(nextPosition).advance();
    }
}