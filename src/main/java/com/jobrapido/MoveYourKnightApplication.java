package com.jobrapido;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobrapido.config.AppConfig;
import com.jobrapido.model.ApplicationOutput;
import com.jobrapido.model.Board;
import com.jobrapido.model.CommandList;
import com.jobrapido.model.Knight;
import com.jobrapido.service.KnightService;
import com.jobrapido.util.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class MoveYourKnightApplication {

    private final ObjectMapper mapper;
    private final HttpUtil httpUtil;
    private final AppConfig config;

    public ApplicationOutput run() throws Exception {
        log.info("Application started");
        String boardJson = httpUtil.fetchJson(config.getBoardApi());
        String commandsJson = httpUtil.fetchJson(config.getCommandsApi());
        log.info("Board and Commands received from API. \n board: {}, \n commands: {}", boardJson, commandsJson);

        Board board = mapper.readValue(boardJson, Board.class);
        CommandList commands = mapper.readValue(commandsJson, CommandList.class);
        Knight knight = new Knight(board);

        KnightService service = new KnightService(knight);
        return service.execute(commands);
    }
}
