package com.jobrapido.util;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class HttpUtil {

    private final HttpClient client;

    public String fetchJson(String url) throws IOException, InterruptedException {

        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Failed to fetch data from: " + url + "status code: " + response.statusCode());
        }

        return response.body();
    }
}
