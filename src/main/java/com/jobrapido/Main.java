package com.jobrapido;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobrapido.config.AppConfig;
import com.jobrapido.exception.GenericException;
import com.jobrapido.model.ApplicationOutput;
import com.jobrapido.model.OutputStatus;
import com.jobrapido.util.HttpUtil;

import java.net.http.HttpClient;

public class Main {
    public static void main(String[] args) {

        var mapper = new ObjectMapper();
        var httpUtil = new HttpUtil(HttpClient.newHttpClient());
        var appConfig = new AppConfig();
        var app = new MoveYourKnightApplication(mapper, httpUtil, appConfig);

        try {
            var output = app.run();
            System.out.println(mapper.writeValueAsString(output));
        } catch (GenericException ge) {
            var errorOutput = new ApplicationOutput();
            errorOutput.setStatus(ge.getCode());
            System.out.println(toJson(mapper, errorOutput));
        } catch (Exception e) {
            var errorOutput = new ApplicationOutput();
            errorOutput.setStatus(OutputStatus.GENERIC_ERROR);
            System.out.println(toJson(mapper, errorOutput));
        }
    }

    private static String toJson(ObjectMapper mapper, Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            return "{\"status\":\"" + OutputStatus.GENERIC_ERROR + "\"}";
        }
    }

}