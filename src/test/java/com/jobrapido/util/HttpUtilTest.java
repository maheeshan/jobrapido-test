package com.jobrapido.util;

import lombok.SneakyThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HttpUtilTest {

    @Mock
    HttpClient httpClient;

    @Mock
    HttpResponse<String> httpResponse;

    @InjectMocks
    HttpUtil httpUtil;

    final String testUri = "https://test/board.json";


    @Test
    @SneakyThrows
    void fetchJson_success() {

        //given
        var expectedBody = "test";

        //when
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(expectedBody);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);

        var result = httpUtil.fetchJson(testUri);

        //then
        assertEquals(expectedBody, result);
    }

    @Test
    @SneakyThrows
    void fetchJson_not_success() {

        //when
        when(httpResponse.statusCode()).thenReturn(500);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);

        //then
        assertThrows(IOException.class, () ->httpUtil.fetchJson(testUri));

    }
}