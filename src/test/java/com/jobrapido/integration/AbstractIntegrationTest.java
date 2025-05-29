package com.jobrapido.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class AbstractIntegrationTest {

    protected static WireMockServer wireMockServer;

    @BeforeAll
    static void setupWireMock() {
        wireMockServer = new WireMockServer(
                WireMockConfiguration.options()
                        .dynamicPort()
                        .usingFilesUnderClasspath("wiremock")
        );

        wireMockServer.start();
    }

    @AfterAll
    static void stopWireMock() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }
}
