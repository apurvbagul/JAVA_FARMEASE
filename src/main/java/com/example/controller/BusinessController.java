package com.example.controller;

import com.example.authentication.FirebaseAuthentication;
import com.example.model.Businessman;
import com.example.services.BusinessmanService;
import com.example.views.BussinessView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BusinessController extends Application {
    private Stage primaryStage; 
    private BussinessView bussinessView;
    private String viewType; 
    private BusinessmanService businessmanService;
    private LoginSignupController loginSignupController;

    //CONSTRUCTOR
    public BusinessController(String viewType) {
        this.viewType = viewType;
        this.businessmanService = new BusinessmanService();
        this.loginSignupController = new LoginSignupController();
    }

    //START METHOD ENTRY POINT
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        switch (viewType) {
            case "home":
                showHomePage();
                break;
            case "signup":
                showSignUpView();
                break;
            case "login":
                showLoginView();
                break;
        }
        this.primaryStage.setTitle("FarmEase");
        this.primaryStage.show();
    }

    //SHOWING THE ADMIN LOGIN VIEW
    private void showLoginView() {
        bussinessView = new BussinessView(viewType, this);
        Group loginView = bussinessView.getBusinessmanLoginPage();
        Scene scene = new Scene(loginView, 1800, 1000);
        this.primaryStage.setScene(scene);
    }

    private void showSignUpView() {
        bussinessView = new BussinessView(viewType, this);
        Group signUpView = bussinessView.getBusinessmanSignUpPage();
        Scene scene = new Scene(signUpView, 1800, 1000);
        this.primaryStage.setScene(scene);
    }

    private void showHomePage() {
        bussinessView = new BussinessView(viewType, this);
        Group homePageView = bussinessView.getBusinessmanHomePage();
        Scene scene = new Scene(homePageView, 1800, 1000);
        this.primaryStage.setScene(scene);
    }

    public boolean handleSignUp(String name, String email, String password) {
        Businessman businessman = new Businessman();
        businessman.setManufacturerName(name);
        businessman.setManufacturerEmailId(email);
        businessman.setManufacturerPassword(password);
        try {
            String idToken = FirebaseAuthentication.signUp(email, password);
            if (idToken != null) {
                boolean isSignUpSuccessful = businessmanService.createBusinessman(businessman);
                if (isSignUpSuccessful) {
                    showHomePage();
                    return false;
                } else {
                    System.err.println("error while signup see the farmercontrollerPage");
                }
            } else {
                System.err.println("Firebase authentication failed");
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error during Firebase authentication or farmer creation");

        }
        return true;

    }

    public boolean handleLogin(String email, String password) throws Exception {
        Businessman businessman = new Businessman();
        businessman.setManufacturerEmailId(email);
        businessman.setManufacturerPassword(password);
    
        String idToken = FirebaseAuthentication.signIn(email, password);
        String loggedUserUid = FirebaseAuthentication.getUserUid(); // corrected variable name
        if (idToken != null) {
            Businessman loginBusinessman = businessmanService.getBusinessmanById(loggedUserUid);
            if (loginBusinessman != null) {
                showHomePage(); // Show the home page if farmer retrieval is successful
                return true; // Indicate that login was successful
            } else {
                throw new Exception("Error while retrieving businessman from the database");
            }
        } else {
            throw new Exception("Firebase authentication failed");
        }
    }
    
    public void handleLogout() throws Exception {
        loginSignupController.handleLogout();
        businessmanService.logout();
        businessmanService = null;
        bussinessView = null;
        loginSignupController.start(this.primaryStage);
    }

    public void navigateToLogin() {
        showLoginView();
    }

    public void navigateToSignup() {
        showSignUpView();
    }
    public void navigateBack() {
        loginSignupController.navigateBack(this.primaryStage);
    }

}
