package com.jobrapido.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobrapido.Main;
import com.jobrapido.model.ApplicationOutput;
import com.jobrapido.model.Direction;
import com.jobrapido.model.OutputStatus;
import com.jobrapido.model.Position;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoveTheKnightIntegrationTest extends AbstractIntegrationTest {


    @Test
    @SneakyThrows
    void test_knight_move_success() {

        loadStubFile("src/test/resources/wiremock/mappings/get-success-commands.json");

        String actualJson = Main.runApp();

        var mapper = new ObjectMapper();
        var actualOutput = mapper.readValue(actualJson, ApplicationOutput.class);

        ApplicationOutput expectedOutput = new ApplicationOutput();
        expectedOutput.setStatus(OutputStatus.SUCCESS);
        expectedOutput.setPosition(new Position(7, 3, Direction.NORTH));

        Assertions.assertThat(actualOutput).usingRecursiveComparison().isEqualTo(expectedOutput);

    }

    @Test
    @SneakyThrows
    void test_knight_move_out_of_board() {

        loadStubFile("src/test/resources/wiremock/mappings/get-out-of-board-commands.json");

        String actualJson = Main.runApp();

        var mapper = new ObjectMapper();
        var actualOutput = mapper.readValue(actualJson, ApplicationOutput.class);

        ApplicationOutput expectedOutput = new ApplicationOutput();
        expectedOutput.setStatus(OutputStatus.OUT_OF_THE_BOARD);

        Assertions.assertThat(actualOutput).usingRecursiveComparison().isEqualTo(expectedOutput);

    }
}
