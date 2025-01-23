package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.authentication.FirebaseAuthentication;
import com.example.model.Farmer;
import com.example.model.FeedbackForm;
import com.example.model.Product;
import com.example.services.FarmerService;
import com.example.services.FeedbackService;
import com.example.services.ProductService;
import com.example.util.ImagePicker;
import com.example.views.AddProduct;
import com.example.views.FarmerView;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FarmerController extends Application {
    private Stage primaryStage; // farmer's Primary stage
    private FarmerView farmerView; // for the Farmers view
    private String viewType; // which view to show
    private FarmerService farmerService; // connecting to the database
    private LoginSignupController loginSignupController;
    private static ProductService productService = new ProductService();;
    private FeedbackService feedbackService;

    public FarmerController(String viewType) {
        this.viewType = viewType;
        this.farmerService = new FarmerService();
        this.feedbackService = new FeedbackService();
        this.loginSignupController = new LoginSignupController();
    }

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

    private void showLoginView() {
        farmerView = new FarmerView(viewType, this, primaryStage);
        Group loginView = farmerView.getFarmerLoginView();
        Scene scene = new Scene(loginView, 1800, 1000);
        this.primaryStage.setScene(scene);
    }

    private void showSignUpView() {
        farmerView = new FarmerView(viewType, this, primaryStage);
        Group signUpView = farmerView.getFarmerSignUpView();
        Scene scene = new Scene(signUpView, 1800, 1000);
        this.primaryStage.setScene(scene);
    }

    private void showHomePage() {
        farmerView = new FarmerView(viewType, this, primaryStage);
        Group homePageView = farmerView.getFarmerHomePage();
        Scene scene = new Scene(homePageView, 1800, 1000);
        this.primaryStage.setScene(scene);
    }

    public boolean handleSignUp(String name, String email, String password) {
        Farmer farmer = new Farmer();
        farmer.setFarmerName(name);
        farmer.setFarmerEmailId(email);
        farmer.setFarmerPassword(password);
        try {
            // Attempt to sign up with Firebase authentication
            String idToken = FirebaseAuthentication.signUp(email, password);

            if (idToken != null) {
                // Proceed with creating the farmer in the database
                boolean isSignUpSuccessful = farmerService.createFarmer(farmer);
                if (isSignUpSuccessful) {
                    showHomePage();
                    return false;
                    // Show the home page if farmer creation is successful
                } else {
                    System.err.println("Error while creating farmer in the database");

                }
            } else {
                System.err.println("Firebase authentication failed");
                return true;
            }
        } catch (IOException e) {
            System.err.println("Error during Firebase authentication or farmer creation");
        }
        return true;
    }

    public boolean handleLogin(String email, String password) throws Exception {
        Farmer farmer = new Farmer();
        farmer.setFarmerEmailId(email);
        farmer.setFarmerPassword(password);
        System.out.println(email);
        
        String idToken = FirebaseAuthentication.signIn(email, password);
        String loggedUserUid = FirebaseAuthentication.getUserUid(); // corrected variable name
        if (idToken != null) {
            Farmer loginFarmer = farmerService.getFarmerById(loggedUserUid);
            if (loginFarmer != null) {
                showHomePage(); // Show the home page if farmer retrieval is successful
                // imageViews = getAllProductImages();
                return true; // Indicate that login was successful
            } else {
                throw new Exception("Error while retrieving farmer from the database");
            }
        } else {
            throw new Exception("Firebase authentication failed");
        }
        
        // products_added_to_farmer = FarmerController.getAllProducts();
    }



    public void handleLogout() throws Exception {
        loginSignupController.handleLogout();
        farmerService.logout();
        farmerService = null;
        farmerView = null;
        loginSignupController.start(this.primaryStage);
        
    }


    public  Farmer getFarmer(String uid){
        // System.out.println(uid);
       Farmer farmer =  farmerService.getFarmerById(uid);
       return farmer;
    }

    public static void handleDltProduct(Product p,String id){

        // System.out.println(FirebaseAuthentication.getUserUid());
        // System.out.println(p.getProductId());
        if(id != null)
             productService.deleteProductForFarmer(FirebaseAuthentication.getUserUid(),id);
        else
            productService.deleteProductForFarmer(FirebaseAuthentication.getUserUid(),p.getProductId());

        try {
            ImagePicker.deleteImage(FirebaseAuthentication.getUserUid(), p.getProductTitle(),p.getProduct_img_Name() );
        } catch (IOException e) {
            System.out.println("error while deleting");
        }
    }
    

    
    public static String handleUploadProduct() {
        Product p = AddProduct.addedProduct();   
        String pid =  productService.createProductForFarmer(FirebaseAuthentication.getUserUid(), p);
        return pid;
    }



    public static  void handleUploadProfilePic(Farmer farmer){

        
        FarmerService.updateFarmer(farmer);
        
    }



    public   boolean handleFeedBackForm(FeedbackForm feedbackForm){
        boolean fdre = feedbackService.createFeedback(feedbackForm);
        System.out.println("Controller "+ fdre);
        if(fdre){
            return true;
        }else{
            return false;
        }
    }

    public static List<Product> getAllProducts() {
        return productService.getAllProductsForFarmer(FirebaseAuthentication.getUserUid());
    }

    public void navigateToSignup() {
        showSignUpView();
    }

    public void navigateToLogin() {
        showLoginView();
    }

    public void navigateBack() {
        loginSignupController.navigateBack(this.primaryStage);
    }
}
