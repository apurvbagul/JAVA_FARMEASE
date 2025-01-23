package com.example.views;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.example.authentication.FirebaseAuthentication;
import com.example.controller.FarmerController;
import com.example.model.Farmer;
import com.example.util.ImagePicker;
import com.google.api.Authentication;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ProfilePageView {

    private static ImagePicker imgp;
    private static File selectedFile;
    private static VBox profilePage;
    private static TextField textField;
    private static Image profileImage;
    public static ImageView profileImageView = new ImageView();

    public static TextField firstNameField = new TextField();
    public static TextField lastNameField = new TextField();
    public static TextField emailField = new TextField();
    public static TextField phoneField = new TextField();
    public static TextField stateField = new TextField();
    public static TextField cityField = new TextField();
    public static TextArea addressField = new TextArea();
    public static ComboBox<String> genderComboBox = new ComboBox<String>() {
        {
            getItems().addAll("Male", "Female", "Other");
        }
    };
    public static TextField pinCodeField = new TextField();

    public static VBox showProfilePageView(Stage stage) {
        imgp = new ImagePicker(null);

        Label firstNameLabel = new Label("First Name:");
        // firstNameField = new TextField();

        Label lastNameLabel = new Label("Last Name:");
        // lastNameField = new TextField();

        Label emailLabel = new Label("Email ID:");
        // emailField = new TextField();
        emailField.setEditable(false);

        Label phoneLabel = new Label("Phone:");
        // phoneField = new TextField();

        Label stateLabel = new Label("State:");
        // stateField = new TextField();

        Label cityLabel = new Label("City:");
        // cityField = new TextField();

        Label genderLabel = new Label("Gender:");
        // genderComboBox = new ComboBox<>();
        // genderComboBox.getItems().addAll("Male", "Female", "Other");

        Label addressLabel = new Label("Address:");
        // addressField = new TextArea();
        addressField.setPrefHeight(70);
        addressField.setPrefRowCount(2);
        addressField.setWrapText(true);

        Label pinCodeLabel = new Label("Pin Code:");
        // pinCodeField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setMaxSize(150, 70);

        // Set the preferred width for the input fields
        double fieldWidth = 250;
        firstNameField.setPrefWidth(fieldWidth);
        lastNameField.setPrefWidth(fieldWidth);
        emailField.setPrefWidth(fieldWidth);
        phoneField.setPrefWidth(fieldWidth);
        stateField.setPrefWidth(fieldWidth);
        cityField.setPrefWidth(fieldWidth);
        genderComboBox.setPrefWidth(fieldWidth);
        addressField.setPrefWidth(fieldWidth);
        pinCodeField.setPrefWidth(fieldWidth);

        // Create the grid layout for the form
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setPrefWidth(1800);
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);

        // Set constraints for two fields per row
        GridPane.setConstraints(firstNameLabel, 0, 0);
        GridPane.setConstraints(firstNameField, 1, 0);

        GridPane.setConstraints(lastNameLabel, 2, 0);
        GridPane.setConstraints(lastNameField, 3, 0);

        GridPane.setConstraints(emailLabel, 0, 1);
        GridPane.setConstraints(emailField, 1, 1);

        GridPane.setConstraints(phoneLabel, 2, 1);
        GridPane.setConstraints(phoneField, 3, 1);

        GridPane.setConstraints(stateLabel, 0, 2);
        GridPane.setConstraints(stateField, 1, 2);

        GridPane.setConstraints(cityLabel, 2, 2);
        GridPane.setConstraints(cityField, 3, 2);

        GridPane.setConstraints(genderLabel, 0, 3);
        GridPane.setConstraints(genderComboBox, 1, 3);

        GridPane.setConstraints(pinCodeLabel, 2, 3);
        GridPane.setConstraints(pinCodeField, 3, 3);

        GridPane.setConstraints(addressLabel, 0, 4);
        GridPane.setConstraints(addressField, 1, 4, 3, 1);

        // Add children to grid
        grid.getChildren().addAll(firstNameLabel, firstNameField, lastNameLabel, lastNameField, emailLabel, emailField,
                phoneLabel, phoneField, stateLabel, stateField, cityLabel, cityField, genderLabel, genderComboBox,
                addressLabel, addressField, pinCodeLabel, pinCodeField);

        grid.setStyle("-fx-background-color: White");

        // Styling
        String labelStyle = "-fx-font-size: 25px; -fx-text-fill: #333333;";
        String fieldStyle = "-fx-font-size: 20px; -fx-background-color: white; -fx-border-color: grey; -fx-border-radius: 5; -fx-padding: 10;";
        String buttonStyle = "-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 25; -fx-font-size: 20px; -fx-padding: 10 20;";

        firstNameLabel.setStyle(labelStyle);
        lastNameLabel.setStyle(labelStyle);
        emailLabel.setStyle(labelStyle);
        phoneLabel.setStyle(labelStyle);
        stateLabel.setStyle(labelStyle);
        cityLabel.setStyle(labelStyle);
        genderLabel.setStyle(labelStyle);
        addressLabel.setStyle(labelStyle);
        pinCodeLabel.setStyle(labelStyle);

        firstNameField.setStyle(fieldStyle);
        lastNameField.setStyle(fieldStyle);
        emailField.setStyle(fieldStyle);
        phoneField.setStyle(fieldStyle);
        stateField.setStyle(fieldStyle);
        cityField.setStyle(fieldStyle);
        genderComboBox.setStyle(fieldStyle);
        addressField.setStyle(fieldStyle);
        pinCodeField.setStyle(fieldStyle);

        submitButton.setStyle(buttonStyle);

        // Create an HBox for the submit button and center it
        HBox buttonBox = new HBox(submitButton);
        buttonBox.setAlignment(Pos.CENTER);

        GridPane.setConstraints(buttonBox, 0, 5, 4, 1);
        grid.getChildren().add(buttonBox);

        // Load an image for the profile picture
        profileImage = new Image("file:farmease\\farmease\\src\\main\\resources\\icons\\profilepic.jpg"); // Replace
                                                                                                          // with

        // your profile
        // image path
        profileImageView.setImage(profileImage);
        ;
        profileImageView.setFitWidth(200);
        profileImageView.setFitHeight(200);
        profileImageView.setClip(new Circle(100, 100, 100));
        // profileImageView.setStyle("-fx-stroke:black; -fx-stroke-width: 5;");

        Circle border = new Circle(100, 100, 100);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(5);

        // Load the plus image
        Image plusImage = new Image("file:farmease\\farmease\\src\\main\\resources\\icons\\plusimage.jpg"); // Replace
                                                                                                            // with
        // your plus image
        // path
        ImageView plusImageView = new ImageView(plusImage);
        plusImageView.setFitWidth(50); // Set width
        plusImageView.setFitHeight(50); // Set height

        // Create a StackPane to overlay plusImageView on profileImageView
        StackPane profileImageStack = new StackPane();
        Button addPic = new Button("", plusImageView);
        addPic.setPadding(new Insets(70, 0, 0, 60));
        addPic.setAlignment(Pos.BOTTOM_RIGHT);
        profileImageStack.getChildren().addAll(profileImageView, border, addPic);
        StackPane.setAlignment(addPic, Pos.BOTTOM_RIGHT); // Position plusImageView at bottom right
        profileImageStack.setPrefWidth(130);
        addPic.setStyle("-fx-background-color:transparent");

        HBox hb = new HBox(profileImageStack);

        textField = new TextField();

        hb.setAlignment(Pos.CENTER);
        Label lb = new Label("Profile");
        lb.setFont(new Font("IMPACT", 60));
        lb.setStyle(" -fx-background-color:transparent; -fx-border-radius: 5; -fx-padding: 30; ");

        // Create an vBox for the profile image and name
        VBox profileBox = new VBox(10, hb, lb);
        profileBox.setAlignment(Pos.CENTER);
        profileBox.setPrefWidth(1800);
        addPic.setOnMouseEntered(e -> {
            addPic.setStyle("-fx-background-color:transparent");
        });
        addPic.setOnMouseExited(e -> {
            addPic.setStyle("-fx-background-color:transparent");
        });

        addPic.setOnAction(e -> {
            System.out.println("image added sucessfully");
            selectedFile = imgp.getImage(FirebaseAuthentication.getUserUid());
            if (selectedFile != null) {
                textField.setText(selectedFile.getAbsolutePath());
                Image image = new Image("file:" + selectedFile.getAbsolutePath());
                profileImageView.setImage(image);
                profileImageView.setFitWidth(200);
                profileImageView.setFitHeight(200);
                profileImageView.setStyle("-fx-border-color: black; -fx-border-width: 2;");
            }
        });

        // Create a VBox to center the grid and button

        VBox vbox = new VBox(10, profileBox, grid);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-alignment: center; -fx-padding: 20; -fx-background-color:white; ");

        // Handle submit button click
        submitButton.setOnAction(e -> {
            // Validate required fields
            if (isFieldsFilled(firstNameField, lastNameField, phoneField, stateField, cityField,
                    pinCodeField)) {
                if (selectedFile != null) {
                    try {
                        imgp.uploadProfileImage(selectedFile, FirebaseAuthentication.getUserUid(),
                                (firstNameField.getText() + lastNameField.getText()));
                        String imgLink = ImagePicker.getImgPath();
                        Farmer farmer = updateFarmerInformation();
                        farmer.setProfilePageUrl(imgLink);
                        FarmerController.handleUploadProfilePic(farmer);
                        profileImageView.setImage(new Image(imgLink));
                        FarmerView.profileImageView.setImage(new Image(imgLink));
                        System.out.println("chnaged");
                    } catch (IOException exce) {
                        System.out.println("IoException in profilePageView");
                        FarmerController.handleUploadProfilePic(updateFarmerInformation());

                    }
                } else {
                    System.out.println("no file selected in image view");
                    FarmerController.handleUploadProfilePic(updateFarmerInformation());

                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Submission Status");
                alert.setHeaderText(null);
                alert.setContentText("Submitted Successfully");
                alert.showAndWait();
            } else {
                // Optionally, you can provide feedback that required fields are not filled
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all required fields.");
                alert.showAndWait();
            }

        });

        // Replace labels with asterisk labels
        replaceWithAsteriskLabels(grid,
                Arrays.asList(firstNameLabel, lastNameLabel, emailLabel, phoneLabel, stateLabel, cityLabel,
                        pinCodeLabel));

        // Add status label to VBox
        // vbox.getChildren().add(statusLabel);

        // Adding animations
        animateNode(firstNameLabel);
        animateNode(firstNameField);
        animateNode(lastNameLabel);
        animateNode(lastNameField);
        animateNode(emailLabel);
        animateNode(emailField);
        animateNode(phoneLabel);
        animateNode(phoneField);
        animateNode(stateLabel);
        animateNode(stateField);
        animateNode(cityLabel);
        animateNode(cityField);
        animateNode(genderLabel);
        animateNode(genderComboBox);
        animateNode(addressLabel);
        animateNode(addressField);
        animateNode(pinCodeLabel);
        animateNode(pinCodeField);
        animateNode(buttonBox);

        profilePage = new VBox(vbox);
        profilePage.setPrefWidth(1800);
        // profilePage.setPrefHeight(750);
        profilePage.setAlignment(Pos.CENTER);
        return profilePage;
    }

    public static Farmer updateFarmerInformation() {
        Farmer farmer = new Farmer();
        farmer.setFarmerId(FirebaseAuthentication.getUserUid());
        farmer.setFarmerName(firstNameField.getText() + " " + lastNameField.getText());
        farmer.setFarmerEmailId(emailField.getText());
        farmer.setFarmerPhoneNumber(Long.parseLong(phoneField.getText()));
        farmer.setFarmerState(stateField.getText());
        farmer.setFarmerCity(cityField.getText());
        farmer.setFarmerAddress(addressField.getText());
        farmer.setFarmerGender(genderComboBox.getValue());
        farmer.setFarmerPincode(Integer.parseInt(pinCodeField.getText()));
        return farmer;

    }

    private static void animateNode(javafx.scene.Node node) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), node);
        transition.setFromX(-1000);
        transition.setToX(0);
        transition.setCycleCount(1);
        transition.play();
    }

    private static void replaceWithAsteriskLabels(GridPane grid, List<Label> labelsToReplace) {
        for (Label originalLabel : labelsToReplace) {
            int columnIndex = GridPane.getColumnIndex(originalLabel);
            int rowIndex = GridPane.getRowIndex(originalLabel);

            Label star = new Label("*");
            star.setTextFill(Color.RED);
            // Create a new label with asterisk
            Label asteriskLabel = new Label(originalLabel.getText(), star);

            asteriskLabel.setStyle(originalLabel.getStyle()); // Copy original style
            asteriskLabel.setAlignment(originalLabel.getAlignment());

            // Replace original label with asterisk label
            grid.getChildren().remove(originalLabel);
            grid.add(asteriskLabel, columnIndex, rowIndex);
        }
    }

    // Method to check if all required fields are filled
    private static boolean isFieldsFilled(TextField... fields) {
        for (TextField field : fields) {
            if (field.getText().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static void reset() {

        firstNameField.clear();
        ;
        lastNameField.clear();
        emailField.clear();
        phoneField.clear();
        stateField.clear();
        cityField.clear();
        addressField.clear();
        genderComboBox.setValue("-select-");
        pinCodeField.clear();
    }
}