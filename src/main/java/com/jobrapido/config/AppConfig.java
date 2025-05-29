package com.jobrapido.config;

import com.jobrapido.exception.GenericException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AppConfig {
    private final String boardApi;
    private final String commandsApi;

    public AppConfig() {
        this.boardApi = System.getenv("BOARD_API");
        this.commandsApi = System.getenv("COMMANDS_API");

        if (boardApi == null || commandsApi == null) {
            throw new GenericException("Missing required environment variables");
        }
    }
}
