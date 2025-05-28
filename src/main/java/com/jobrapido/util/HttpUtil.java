package com.jobrapido.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtil {

    private static final HttpClient client = HttpClient.newHttpClient();

    public static String fetchJson(String url) throws IOException, InterruptedException {

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
