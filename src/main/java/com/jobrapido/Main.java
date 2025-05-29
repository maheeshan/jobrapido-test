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
        System.out.println(runApp());
    }

    public static String runApp() {
        var mapper = new ObjectMapper();
        var httpUtil = new HttpUtil(HttpClient.newHttpClient());
        var appConfig = new AppConfig();
        var app = new MoveYourKnightApplication(mapper, httpUtil, appConfig);

        try {
            var output = app.run();
            return mapper.writeValueAsString(output);
        } catch (GenericException ge) {
            var errorOutput = new ApplicationOutput();
            errorOutput.setStatus(ge.getCode());
            return toJson(mapper, errorOutput);
        } catch (Exception e) {
            var errorOutput = new ApplicationOutput();
            errorOutput.setStatus(OutputStatus.GENERIC_ERROR);
            return toJson(mapper, errorOutput);
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