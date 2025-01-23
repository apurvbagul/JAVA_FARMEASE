package com.example.controller;

import com.example.authentication.FirebaseAuthentication;
import com.example.model.Admin;
import com.example.services.AdminService;
import com.example.views.AdminView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminController extends Application {
    private Stage primaryStage;
    private AdminView adminView;
    private String viewType;
    private AdminService adminService;
    private LoginSignupController loginSignupController;

    //CONSTRUCTOR
    public AdminController(String viewType) {
        this.viewType = viewType;
        this.adminService = new AdminService();
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
        this.primaryStage.setTitle("FarmEase Admin signUp");
        this.primaryStage.show();
    }

    //SHOWING THE ADMIN LOGIN VIEW
    private void showLoginView() {
        adminView = new AdminView(viewType, this);
        Group loginView = adminView.getAdminLoginPage();
        Scene scene = new Scene(loginView, 1800, 1000);
        this.primaryStage.setScene(scene);
    }

    //SHOWING THE ADMIN SIGNUP VIEW
    private void showSignUpView() {
        adminView = new AdminView(viewType, this);
        Group signUpView = adminView.getAdminSignUpPage();
        Scene scene = new Scene(signUpView, 1800, 1000);
        this.primaryStage.setScene(scene);
    }

    //SHOWING THE ADMIN HOMEPAGE VIEW
    private void showHomePage() {
        adminView = new AdminView(viewType, this);
        Group homePageView = adminView.getAdminHomePage();
        Scene scene = new Scene(homePageView, 1800, 1000);
        this.primaryStage.setScene(scene);
    }

    //HANDLES THE SIGNUP FOR THE ADMIN
    public boolean handleSignUp(String name, String email, String password) {
        Admin admin = new Admin();
        admin.setAdminName(name);
        admin.setAdminEmailId(email);
        admin.setAdminPassword(password);
        try {
            String idToken = FirebaseAuthentication.signUp(email, password);
            if (idToken != null) {
                boolean isSignUpSuccessful = adminService.createAdmin(admin);
                if (isSignUpSuccessful) {
                    // System.out.println("homepage");
                    showHomePage();
                    return false;
                } else {
                    System.err.println("error while signup see the farmercontrollerPage");
                }
            } else {
                System.err.println("error while signup see the farmercontrollerPage");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println("Error during Firebase authentication or farmer creation");

        }
        return true;
    }

    //HANDLES LOGIN FOR ADMIN
    public boolean handleLogin(String email, String password) throws Exception {
        Admin admin = new Admin();
        admin.setAdminEmailId(email);
        admin.setAdminPassword(password);
        String idToken = FirebaseAuthentication.signIn(email, password);
        String loggedUserUid = FirebaseAuthentication.getUserUid(); // corrected variable name
        if (idToken != null) {
            Admin loginAdmin = adminService.getAdminById(loggedUserUid);
            if (loginAdmin != null) {
                showHomePage(); // Show the home page if farmer retrieval is successful
                return true; // Indicate that login was successful
            } else {
                throw new Exception("Error while retrieving admin from the database");
            }
        } else {
            throw new Exception("Firebase authentication failed");
        }
    }

    //HANDLES THE ADMIN LOGOUT
    public void handleLogout() throws Exception {
        loginSignupController.handleLogout();
        adminService.logout();
        adminService = null;
        adminView = null;
        loginSignupController.start(this.primaryStage);
    }

    //NAVIGATING TO LOGIN PAGE FROM SIGNUP PAGE
    public void navigateToLogin() {
        showLoginView();
    }

    //NAVIGATE TO SIGNUP PAGE FROM LOGIN PAGE
    public void navigateToSignup() {
        showSignUpView();
    }

    //NAVIGATING BACK
    public void navigateBack() {
        loginSignupController.navigateBack(this.primaryStage);
    }
}
