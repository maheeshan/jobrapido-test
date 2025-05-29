package com.jobrapido.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.Json;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public abstract class AbstractIntegrationTest {

    protected static WireMockServer wireMockServer;

    @BeforeAll
    static void setupWireMock() {
        wireMockServer = new WireMockServer(
                WireMockConfiguration.options()
                        .port(8081)
                        .usingFilesUnderClasspath("wiremock")
        );

        wireMockServer.start();
        log.info("Wiremock server started on {}", wireMockServer.port());
    }

    @BeforeEach
    void resetStubs() {
        wireMockServer.resetAll();
    }

    @AfterAll
    static void stopWireMock() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

    protected void loadStubFile(String mappingFilePath) {
        try {
            String json = Files.readString(Path.of(mappingFilePath));
            StubMapping mapping = Json.read(json, StubMapping.class);
            wireMockServer.addStubMapping(mapping);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load WireMock mapping: " + mappingFilePath, e);
        }
    }
}
