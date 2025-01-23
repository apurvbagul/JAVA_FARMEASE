package com.example.views;

import com.example.model.Weather;
import com.example.util.ApiBinding;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class WeatherView extends Application {
    private static ImageView progressIndicator;
    private static Label locationLabel;
    private static Label weatherDescriptionLabel;
    private static ImageView weatherIcon;
    private static Label temperatureLabel;
    private static Label windSpeedLabel;
    private static Label humidityLabel;
    private static Label cloudCoverLabel;
    private static StackPane root;
    private static VBox mainContainer;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        StackPane weatherView = showWeather();
        root.getChildren().add(weatherView);
        Scene scene = new Scene(root, 1800, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Weather App");
        primaryStage.show();
    }

    public static StackPane showWeather() {
        mainContainer = new VBox(20);
        mainContainer.setPrefSize(1800, 1200);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setStyle("-fx-background-color:linear-gradient(to bottom right, #575757, #000000); -fx-padding: 30px;");

        Label yourWeatherLabel = new Label("Your Weather");
        yourWeatherLabel.setFont(new Font("verdana", 25));
        yourWeatherLabel.setTextFill(Color.web("#9E9E9E"));

        TextField locationField = new TextField();
        locationField.setPromptText("Search for city ...");
        locationField.setStyle("-fx-focus-color: transparent;-fx-faint-focus-color: transparent; -fx-font-size: 25px; -fx-pref-width: 400px; -fx-opacity:0.7; -fx-background-radius:20;  -fx-padding: 10px;");
        locationField.setPrefHeight(30);

        Button searchButton = new Button("Search Weather");
        searchButton.setStyle(
                "-fx-background-color: #B0BEC5; -fx-text-fill: black; -fx-font-size: 25px; -fx-background-radius:20; -fx-padding: 10px 20px;");
        searchButton.setOnAction(e -> {
            String location = locationField.getText();
            if (location.isEmpty()) {
                showAlert("Location cannot be empty!", Alert.AlertType.ERROR);
                return;
            }

            fetchWeatherData(location, mainContainer);
        });

        HBox searchBox = new HBox(10, locationField, searchButton);
        searchBox.setAlignment(Pos.CENTER);
        VBox.setMargin(searchBox, new Insets(20, 0, 0, 0));

        locationLabel = new Label("Pune");
        locationLabel.setFont(new Font("Verdena", 40));
        locationLabel.setTextFill(Color.WHITE);

        weatherDescriptionLabel = new Label();
        weatherDescriptionLabel.setFont(new Font("verdana", 30));
        weatherDescriptionLabel.setTextFill(Color.WHITE);

        weatherIcon = new ImageView();
        weatherIcon.setFitHeight(100);
        weatherIcon.setFitWidth(100);

        temperatureLabel = new Label();
        temperatureLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        temperatureLabel.setTextFill(Color.WHITE);

        ImageView wind = new ImageView(new Image("file:farmease\\farmease\\src\\main\\resources\\icons\\wind.png"));
        wind.setFitHeight(70);
        wind.setFitWidth(70);

        Label windL = new Label("WIND SPEED");
        windL.setFont(new Font("verdana", 30));
        windL.setTextFill(Color.WHITE);

        windSpeedLabel = new Label();
        windSpeedLabel.setFont(new Font("verdana", 30));
        windSpeedLabel.setTextFill(Color.WHITE);

        VBox windBox = new VBox(20, wind, windL, windSpeedLabel);
        windBox.setAlignment(Pos.CENTER);
        windBox.setPrefWidth(250);

        ImageView humidity = new ImageView(new Image("file:farmease\\farmease\\src\\main\\resources\\icons\\humidity.png"));
        humidity.setFitHeight(70);
        humidity.setFitWidth(70);

        Label humidityL = new Label("HUMIDITY");
        humidityL.setFont(new Font("verdana", 30));
        humidityL.setTextFill(Color.WHITE);

        humidityLabel = new Label();
        humidityLabel.setFont(new Font("verdana", 30));
        humidityLabel.setTextFill(Color.WHITE);

        VBox humidityBox = new VBox(20, humidity, humidityL, humidityLabel);
        humidityBox.setAlignment(Pos.CENTER);
        humidityBox.setPrefWidth(250);

        ImageView cloud = new ImageView(new Image("file:farmease\\farmease\\src\\main\\resources\\icons\\cloudy.png"));
        cloud.setFitHeight(70);
        cloud.setFitWidth(70);

        Label cloudL = new Label("CLOUD COVER");
        cloudL.setFont(new Font("verdana", 30));
        cloudL.setTextFill(Color.WHITE);

        cloudCoverLabel = new Label();
        cloudCoverLabel.setFont(new Font("verdana", 30));
        cloudCoverLabel.setTextFill(Color.WHITE);

        VBox cloudBox = new VBox(20, cloud, cloudL, cloudCoverLabel);
        cloudBox.setPrefWidth(250);
        cloudBox.setAlignment(Pos.CENTER);

        HBox weatherInfoBox = new HBox(80, windBox, humidityBox, cloudBox);
        weatherInfoBox.setAlignment(Pos.CENTER);

        VBox weatherContainer = new VBox(30, locationLabel, weatherDescriptionLabel, weatherIcon, temperatureLabel, weatherInfoBox);
        weatherContainer.setAlignment(Pos.CENTER);

        mainContainer.getChildren().addAll(yourWeatherLabel, searchBox, weatherContainer);

        progressIndicator = new ImageView(new Image("file:farmease\\farmease\\src\\main\\resources\\icons\\loading.gif"));
        progressIndicator.setFitWidth(300);
        progressIndicator.setFitHeight(300);
        progressIndicator.setVisible(false);

        root = new StackPane(mainContainer, progressIndicator);
        root.setStyle("-fx-background-color:linear-gradient(to bottom right, #575757, #000000); -fx-padding: 30px;");

        root.setAlignment(progressIndicator, Pos.CENTER);

        // Fetch weather data for Pune initially
        fetchWeatherData("Pune", mainContainer);

        return root;
    }

    private static void fetchWeatherData(String location, VBox root) {
       
        showProgressIndicator(true);
        ApiBinding.getWeatherData(location, (jsonResponse, exception) -> {
            Platform.runLater(() -> {
                showProgressIndicator(false);
                
                if (exception != null) {
                    showAlert("Failed to fetch weather data. Please check your connection and try again.",
                            Alert.AlertType.ERROR);
                } else {
                    locationLabel.setText(location.substring(0, 1).toUpperCase() + location.substring(1) + " \uD83C\uDDEE\uD83C\uDDF3");
                    displayWeatherData(jsonResponse);
                }
            });
        });
    }

    private static void showProgressIndicator(boolean show) {
        mainContainer.setVisible(!show);
        progressIndicator.setVisible(show);
    }

    private static void displayWeatherData(String jsonResponse) {
        Weather weatherDay = ApiBinding.parseWeatherData(jsonResponse).get(0);

        if (weatherDay == null) {
            showAlert("No weather data found for the given location.", Alert.AlertType.INFORMATION);
            return;
        }

        weatherDescriptionLabel.setText(weatherDay.getWeatherDescription());

        Image weatherImage = new Image(weatherDay.getWeatherIconUrl());
        weatherIcon.setImage(weatherImage);

        temperatureLabel.setText(String.format("%.2f°C", Double.parseDouble(weatherDay.getMaxTemperature())));

        windSpeedLabel.setText(String.format("%.2f Km/s", Double.parseDouble(weatherDay.getWindSpeed())));
        humidityLabel.setText(weatherDay.getHumidity() + "%");
        cloudCoverLabel.setText(weatherDay.getCloudCover() + "%");
    }

    private static void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
