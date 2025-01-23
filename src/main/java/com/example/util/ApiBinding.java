package com.example.util;

import com.example.model.Weather;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiBinding {

    private static final String WEATHER_API_KEY = "a72b6153f55c4f8f83b175148242310";
    private static final String WEATHER_API_URL = "https://api.worldweatheronline.com/premium/v1/weather.ashx";

    public interface WeatherDataListener {
        void onDataReceived(String jsonResponse, Exception exception);
    }

    public static void getWeatherData(String location, WeatherDataListener listener) {
        String apiUrl = buildWeatherApiUrl(location);
        new Thread(() -> {
            try {
                String jsonResponse = getApiResponse(apiUrl);
                listener.onDataReceived(jsonResponse, null);
            } catch (Exception e) {
                listener.onDataReceived(null, e);
            }
        }).start();
    }


    //step1- app creates URL for that i/p data
    private static String buildWeatherApiUrl(String location) {
        return WEATHER_API_URL + "?key=" + WEATHER_API_KEY + "&q=" + location + "&num_of_days=5&fx24=no&format=json";
    }

    private static String getApiResponse(String apiUrl) throws Exception {

        StringBuilder response = new StringBuilder();
        //Construt URl
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }

        return response.toString();
    }

    public static List<Weather> parseWeatherData(String jsonResponse) {
        List<Weather> weatherDays = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootJson = mapper.readTree(jsonResponse);  //The rootJson object now represents the entire JSON structure.
            JsonNode dataNode = rootJson.path("data"); 
            JsonNode weatherNodes = dataNode.path("weather");  //weatherNodes is a list of all the weather objects from the JSON response.

            for (JsonNode weatherNode : weatherNodes) {
                Weather day = new Weather(weatherNode);   
                weatherDays.add(day);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return weatherDays;
    }
}