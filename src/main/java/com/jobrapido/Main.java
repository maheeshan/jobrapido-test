package com.jobrapido;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobrapido.exception.GenericException;
import com.jobrapido.model.*;
import com.jobrapido.service.KnightService;
import com.jobrapido.util.HttpUtil;

public class Main {
    public static void main(String[] args) {

        try {
            //init env var
            var boardApi = System.getenv("BOARD_API");
            var commandsApi = System.getenv("COMMANDS_API");

            if (boardApi == null || commandsApi == null) {
                System.err.println("Missing environment variables");
                System.exit(1);
            }

            //extract json
            var boardJson = HttpUtil.fetchJson(boardApi);
            var commandsJson = HttpUtil.fetchJson(commandsApi);

            var mapper = new ObjectMapper();
            var board = mapper.readValue(boardJson, Board.class);
            var commands = mapper.readValue(commandsJson, CommandList.class);
            var knight = new Knight(board);

            // Process with service
            var service = new KnightService(knight);
            var output = service.execute(commands);

            // Output final JSON
            System.out.println(mapper.writeValueAsString(output));
        } catch (GenericException genericException) {
            var applicationOutput = new ApplicationOutput();
            applicationOutput.setStatus(genericException.getCode());
            System.err.println(applicationOutput);
        } catch (Exception e) {
            var applicationOutput = new ApplicationOutput();
            applicationOutput.setStatus(OutputStatus.GENERIC_ERROR);
            System.err.println(applicationOutput);
            System.exit(1);
        }

        System.out.println("Lets Move your Knight!");
    }
}