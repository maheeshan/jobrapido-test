package com.jobrapido.integration;

import com.jobrapido.Main;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MoveTheKnightIntegrationTest extends AbstractIntegrationTest {


    @Test
    void testMain() {
        // Capture output
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(new String[]{});

        String result = out.toString();

        // Validate result
        System.out.println(result);
    }
}
