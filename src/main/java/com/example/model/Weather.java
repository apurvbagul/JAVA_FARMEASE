package com.example.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.JsonNode;

public class Weather {
    private LocalDate date;
    private String formattedDate;
    private String maxTemperature;
    private String minTemperature;
    private String windSpeed;
    private String weatherDescription;
    private String precipitation;
    private String humidity;
    private String cloudCover;
    private String weatherIconUrl;

    public Weather(JsonNode weatherNode) {
        this.date = LocalDate.parse(weatherNode.path("date").asText(), DateTimeFormatter.ISO_DATE);
        this.formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        this.maxTemperature = weatherNode.path("maxtempC").asText();
        this.minTemperature = weatherNode.path("mintempC").asText();
        this.windSpeed = weatherNode.path("hourly").get(0).path("windspeedKmph").asText();
        this.weatherDescription = weatherNode.path("hourly").get(0).path("weatherDesc").get(0).path("value").asText();
        this.precipitation = weatherNode.path("hourly").get(0).path("precipMM").asText();
        this.humidity = weatherNode.path("hourly").get(0).path("humidity").asText();
        this.cloudCover = weatherNode.path("hourly").get(0).path("cloudcover").asText();
        this.weatherIconUrl = weatherNode.path("hourly").get(0).path("weatherIconUrl").get(0).path("value").asText();
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getCloudCover() {
        return cloudCover;
    }

    public String getWeatherIconUrl() {
        return weatherIconUrl;
    }     
}
