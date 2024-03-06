package com.example.Foods.riotV2.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HttpConnect {
    public String connectUrl(String apiUrl) {
        String result = "";
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {

            throw new IllegalStateException();
        }
        return result;
    }

}


