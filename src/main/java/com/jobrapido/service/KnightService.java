package com.jobrapido.service;

import com.jobrapido.exception.GenericException;
import com.jobrapido.model.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class KnightService {

    private Knight knight;

    @SneakyThrows
    public ApplicationOutput execute(CommandList commandList) {
        log.info("Start executing commands...");
        for (String command : commandList.getCommands()) {
            if (command == null || command.trim().isEmpty()) continue;
            log.info("executing command: {}", command);

            var partsOfCommand = command.split(" ", 2);
            var commandType = CommandType.fromString(partsOfCommand[0]);

            switch (commandType) {
                case START -> handleStart(partsOfCommand[1]);
                case ROTATE -> handleRotate(partsOfCommand[1]);
                case MOVE -> handleMove(partsOfCommand[1]);
                default -> throw new GenericException("Unknown command: " + command);
            }
            log.info("Knight at position: {}", knight.getPosition());
        }
        var knightOutput = new ApplicationOutput();
        knightOutput.setPosition(knight.getPosition());
        knightOutput.setStatus(OutputStatus.SUCCESS);
        return knightOutput;
    }

    private void handleStart(String s) {
        var values = s.split(",", 3);
        knight.setStartPosition(new Position(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Direction.fromString(values[2])));
        log.info("Knight set to start position: {},{},{}", values[0], values[1], values[2]);
    }

    private void handleRotate(String s) {
        knight.rotate(Direction.fromString(s));
        log.info("Knight rotated to: {}", s);
    }

    private void handleMove(String s) {
        var executedSteps = knight.move(Integer.parseInt(s));
        log.info("Knight moved {} steps", executedSteps);
    }


}
