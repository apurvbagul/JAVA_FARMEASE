package com.example.controller;

import com.example.views.Index;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginSignupController extends Application {
    private ComboBox<String> loginComboBox;
    private ComboBox<String> signComboBox;
    private Index indexView;
    private Stage primaryStage;
    FarmerController farmerController;
    AdminController adminController;
    BusinessController manufacturerController;

    // overide the start method
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        indexView = new Index();
        loginComboBox = indexView.getLoginComboBox();
        signComboBox = indexView.getSignupComboBox();
        // soham
        loginComboBox.setOnAction(event -> {
            String selectedLoginType = loginComboBox.getValue();
            handleLogin(selectedLoginType, "login");
        });

        signComboBox.setOnAction(event -> {
            String selectedSignUpType = signComboBox.getValue();
            handleSignUp(selectedSignUpType, "signup");

        });

        Image icon = new Image("file:farmease\\farmease\\src\\main\\resources\\farmForm\\logo.gif");
        this.primaryStage.getIcons().add(icon);

        Scene scene = new Scene(indexView.getContent(), 1800, 1000);
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle("FarmEase");
        this.primaryStage.show();
    }

    // handling LoginPages
    public void handleLogin(String loginType, String type) {
        switch (loginType) {
            case "Admin":
                launchAdminController(type);
                break;
            case "Farmer":
                System.out.println("demo");
                launchFarmerController(type);
                break;
            case "Business":
                launchBussinessController(type);
                break;
        }
    }

    public void handleSignUp(String signUpType, String type) {
        switch (signUpType) {
            case "Farmer":
                launchFarmerController(type);
                break;
            case "Admin":
                launchAdminController(type);
                break;
            case "Business":
                launchBussinessController(type);
                break;
        }
    }

    public void handleLogout() {
        indexView = null;
    }

    public void navigateBack(Stage prstage) {
        try {
            start(prstage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // launching the launchfarmerController from loginController
    private void launchFarmerController(String type) {
        farmerController = new FarmerController(type);
        try {
            System.out.println("farmerControler");
            farmerController.start(this.primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // launching the launchAdminController from loginController
    private void launchAdminController(String type) {
        adminController = new AdminController(type);
        try {
            adminController.start(this.primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // launching the launchManufacturerController from loginController
    private void launchBussinessController(String type) {
        manufacturerController = new BusinessController(type);
        try {
            manufacturerController.start(this.primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
