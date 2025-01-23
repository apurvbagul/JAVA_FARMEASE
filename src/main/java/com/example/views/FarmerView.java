package com.example.views;

import java.util.List;

import com.example.authentication.FirebaseAuthentication;
import com.example.controller.FarmerController;
import com.example.model.Farmer;
import com.example.model.Product;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.application.Platform;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FarmerView {

    private Button selectedButton = null;
    private Group farmerLoginView;
    private Group farmerSignUpView;
    private Group farmerHomePageView;
    private FarmerController farmerController;
    private Stage stage;
    // login varibales
    private TextField email;
    private PasswordField pass;
    // signup variables
    private TextField name;
    private TextField email_signUp;
    private PasswordField pass_signUp;
    private ScrollPane buyproduct;
    public static List<Product> products;// = FarmerView.getProducts();
    private MediaPlayerLearningHub mediaPlayerLearningHub;
    private HBox mediaPlayerhub;
    // private static List<Product> products = FarmerController.getAllProducts();
    private ScrollPane feedBackView;
    private FeedbackFormView feedbackFormView;
    private MenuButton menuButton;
    private MenuItem logoutOption;
    private MenuItem profileOption;
    private static VBox profilePageView;
    public static ImageView profileImageView;
    // private MenuBar menuBar;

    public static List<Product> getProducts() {
        if (products == null) {
            //System.out.println("hello baba");
            products = FarmerController.getAllProducts();
        }
        return products;
    }

    // weather applicaton
    private ImageView homeView;
    private StackPane weatherView;
    private ScrollPane scrollpane;
    private HBox content;
    private HBox addProduct;
    public VBox cartBox;

    public FarmerView(String viewType, FarmerController farmerController, Stage stage) {
        this.farmerController = farmerController;
        this.stage = stage;
        this.stage.setResizable(false);
        farmerLoginPage();
        farmerSignUpPage();
        farmerHomePage();
        feedbackFormView = new FeedbackFormView(farmerController);
        this.mediaPlayerLearningHub = new MediaPlayerLearningHub();
    }

    private void farmerHomePage() {
        Image img = new Image("file:C:\\Users\\soham\\OneDrive\\Desktop\\Project\\farmease\\farmease\\src\\main\\resources\\farmForm\\logo2.gif");
        ImageView iv = new ImageView(img);
        iv.setFitHeight(65);
        iv.setFitWidth(65);

        Label title = new Label("FarmEase");
        title.setFont(new Font("IMPACT", 50));
        title.setTextFill(Color.GREEN);

        HBox navLeft = new HBox(30, iv, title);
        navLeft.setAlignment(Pos.CENTER);

        Button home = new Button("Home");
        // home.setPrefWidth(0);
        home.setStyle(
                "-fx-font-family:Verdana; -fx-background-color:transparent; -fx-font-size:30; -fx-font-weight:bold;");

        // ImageView cart = new ImageView(new Image(
        //         "file:C:\\Users\\soham\\OneDrive\\Desktop\\Copy\\farmease\\farmease\\src\\main\\resources\\icons\\cart.png"));

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        homeView = new ImageView(new Image("file:farmease\\farmease\\src\\main\\resources\\farmForm\\frontPage.jpg"));
        homeView.setFitWidth(1800);
        homeView.setFitHeight(1000);

        
        // content.getChildren().add(vb);

        home.setOnAction(e -> {
            content.getChildren().clear(); // Clear all existing children
            content.getChildren().add(homeView);
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ImageView cart = new ImageView(new Image(
            "file:C:farmease\\farmease\\src\\main\\resources\\cart.png"));


        Button cartBtn = new Button();
        cartBtn.setGraphic(cart);
        cartBtn.setStyle("-fx-background-color:transparent; ");
        cartBtn.setPrefSize(50, 50);

        cart.setFitWidth(50);
        cart.setFitHeight(50);

        cartBtn.setOnAction(e->{
            // content.getChildren().clear(); 
            // cartBox = Cart.cartBoxView();
            // content.getChildren().add(cartBox);

            System.out.println("Cart clicked");
            content.getChildren().clear(); // Clear all existing children
            cartBox = Cart.cartBoxView();
            if (cartBox != null) {
                content.getChildren().add(cartBox);
            } else {
                cartBox = Cart.cartBoxView();
                content.getChildren().add(cartBox);
            }
        });
        // Profile Photo

        Image profileImage = new Image(
                "file:C:\\Users\\soham\\OneDrive\\Desktop\\Project\\farmease\\farmease\\src\\main\\resources\\profilepic.jpg");
        profileImageView = new ImageView(profileImage);
        profileImageView.setFitHeight(50);
        profileImageView.setFitWidth(50);
        profileImageView.setClip(new Circle(25, 25, 25));
        // profileImageView.setStyle("-fx-stroke:black; -fx-stroke-width: 5;");
 
 
         Circle border = new Circle(25, 25, 25);
         border.setFill(Color.TRANSPARENT);
         border.setStroke(Color.BLACK);
         border.setStrokeWidth(2); // Circular shape


         StackPane pro = new StackPane();
         pro.getChildren().addAll(profileImageView,border);


        // Menu with options
        Image menuImage = new Image(
                "file:C:\\Users\\soham\\OneDrive\\Desktop\\Project\\farmease\\farmease\\src\\main\\resources\\icons\\menu.png");
        ImageView menuImageView = new ImageView(menuImage);
        menuImageView.setFitHeight(50);
        menuImageView.setFitWidth(50);

        menuButton = new MenuButton();
        menuButton.setGraphic(menuImageView);

        menuButton.setStyle("-fx-background-color:transparent; -fx-font-size:20px; -fx-padding:0px;");
        logoutOption = new MenuItem("Logout");
        profileOption = new MenuItem("Profile");

        logoutOption.setStyle("-fx-font-size:25px; -fx-padding:10px;");
        profileOption.setStyle("-fx-font-size:25px; -fx-padding:10px;");

        menuButton.getItems().addAll(profileOption, logoutOption);

        HBox navRight = new HBox(50, home, cartBtn, pro, menuButton);
        navRight.setPrefHeight(150);
        navRight.setAlignment(Pos.CENTER);

        HBox navbar = new HBox(900, navLeft, navRight);
        navbar.setPrefWidth(0);
        navbar.setPrefHeight(100);
        navbar.setAlignment(Pos.CENTER);
        navbar.setStyle(" -fx-background-color:transparent;   ");

        Button opt1 = new Button("Learning Hub");
        opt1.setPrefWidth(300);
        opt1.setStyle(
                "-fx-font-size:25px;-fx-background-color:transparent ; -fx-border-color:red; -fx-text-fill:black; -fx-font-weight:900; -fx-font-family:Verdana; -fx-border-width:8 0 0 0; -fx-padding:10;");

        Button opt2 = new Button("Buy Products");
        opt2.setPrefWidth(300);
        opt2.setStyle(
                "-fx-font-size:25px;-fx-background-color:transparent ;  -fx-border-color:green; -fx-text-fill:black; -fx-font-weight:900; -fx-font-family:Verdana; -fx-border-width:8 0 0 0; -fx-padding:10;");

        Button opt3 = new Button("Sell Products");
        opt3.setPrefWidth(300);
        opt3.setStyle(
                "-fx-font-size:25px; -fx-background-color:transparent ;  -fx-border-color:gold; -fx-text-fill:black; -fx-font-weight:900; -fx-font-family:Verdana; -fx-border-width:8 0 0 0; -fx-padding:10;");

        Button opt4 = new Button("Weather Info");
        opt4.setPrefWidth(300);
        opt4.setStyle(
                "-fx-font-size:25px; -fx-background-color:transparent ;  -fx-border-color:darkblue; -fx-text-fill:black; -fx-font-weight:900; -fx-font-family:Verdana; -fx-border-width:8 0 0 0; -fx-padding:10; ");

        Button opt5 = new Button("About Us");
        opt5.setPrefWidth(300);
        opt5.setStyle(
                "-fx-font-size:25px; -fx-background-color:transparent ; -fx-border-color:brown; -fx-text-fill:black; -fx-font-weight:700; -fx-font-family:Verdana; -fx-border-width:8 0 0 0; -fx-padding:10; ");

        // Event handlers for hover and click
        // setButtonEvents(home, "black");
        setButtonEvents(opt1, "red");
        setButtonEvents(opt2, "green");
        setButtonEvents(opt3, "gold");
        setButtonEvents(opt4, "darkblue");
        setButtonEvents(opt5, "brown");

        opt1.setOnAction(e -> {
            content.getChildren().clear(); // Clear all existing children
            mediaPlayerhub = mediaPlayerLearningHub.showMediaPlayer();
            // if (mediaPlayerhub != null) {
            //     content.getChildren().add(mediaPlayerhub);
            // } else {
                mediaPlayerhub = mediaPlayerLearningHub.showMediaPlayer();
                content.getChildren().add(mediaPlayerhub);
            //}
        });
        opt2.setOnAction(e -> {
            content.getChildren().clear(); // Clear all existing children
            // if (buyproduct != null) {
            //     content.getChildren().add(buyproduct);
            //     // List<Product> produu = farmerController.getAllProductsByFarmers();
            //     // System.out.println(produu.size());
            //     // // Get the new weather view
            // } else {
                buyproduct = ProductView.showBuyProducts();
                content.getChildren().add(buyproduct);
           // }
        });
        // Button opt3 = new Button("Add Product");
        opt3.setOnAction(e -> {
            content.getChildren().clear(); // Clear all existing children
            // if (addProduct != null) {
            //     content.getChildren().add(addProduct);
            // } else {
                addProduct = AddProduct.showAddProduct(stage);
                content.getChildren().add(addProduct);
            // }

        });

        opt4.setOnAction(e -> {
            content.getChildren().clear(); // Clear all existing children
            if (weatherView != null) {
                content.getChildren().add(weatherView);
                // Get the new weather view
            } else {
                weatherView = WeatherView.showWeather();
                content.getChildren().add(weatherView);
            }
            // AddProduct.getAllPrBox();
        });
        opt5.setOnAction(e -> {
             content.getChildren().clear(); // Clear all existing children
            // if (feedBackView != null) {
            //     content.getChildren().add(feedBackView); // Clear all existing children
            // } else {
                feedBackView = feedbackFormView.BothPage();
                content.getChildren().add(feedBackView);
            //}

        });

        profileOption.setOnAction(e -> {
            content.getChildren().clear(); // Clear all existing children
            // if (profilePageView != null) {
            //     content.getChildren().add(profilePageView); // Clear all existing children
            // } else {
                profilePageView = ProfilePageView.showProfilePageView(stage);
                content.getChildren().add(profilePageView);
            //}
        });

        HBox opt = new HBox(15, opt1, opt2, opt3, opt4, opt5);
        opt.setAlignment(Pos.CENTER);
        opt.setPrefWidth(1800);
        opt.setPrefHeight(100);
        opt.setStyle("-fx-padding:30 0 0 0; ");

        HBox mid = new HBox(opt);
        mid.setAlignment(Pos.CENTER);
        mid.setPrefWidth(1800);
        mid.setStyle("-fx-border-color:black; -fx-border-width:0 0 1 0");

        content = new HBox();
        content.getChildren().add(homeView);
        content.setPrefWidth(1800);
        content.setPrefHeight(800);

        scrollpane = new ScrollPane(content);
        scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollpane.setFocusTraversable(false);
        scrollpane.setPrefWidth(1800);
        scrollpane.setPrefHeight(800);

        Platform.runLater(() -> {
            scrollpane.lookupAll(".scroll-bar").forEach(node -> {
                if (node instanceof ScrollBar) {
                    ScrollBar scrollBar = (ScrollBar) node;
                    if (scrollBar.getOrientation() == Orientation.VERTICAL) {
                        scrollBar.setStyle("-fx-pref-width: 15; -fx-background-color: lightgreen;");
                    }
                }
            });
        });

        VBox pg = new VBox(navbar, mid, scrollpane);
        pg.setPrefSize(1800, 1000);

        pg.setPrefSize(1800, 1000);

        farmerHomePageView = new Group(pg);

        logoutOption.setOnAction(e -> {
            content.getChildren().clear();
            products = null;

            logoutSession();

        });
    }

    
    private void logoutSession() {
        try {
            farmerController.handleLogout();
            profileImageView.setImage(null);
            ProfilePageView.profileImageView.setImage(new Image("file:farmease\\farmease\\src\\main\\resources\\icons\\profilepic.jpg"));
            ProfilePageView.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /////////////////////////////////
    ///////////////////////////////////

    private void setButtonEvents(Button button, String borderColor) {
        button.setOnMouseEntered(e -> button
                .setStyle("-fx-font-size:25px; -fx-underline:true; -fx-background-color:transparent ; -fx-border-color:"
                        + borderColor + "; -fx-text-fill:" + borderColor
                        + "; -fx-font-weight:900; -fx-font-family:Verdana; -fx-border-width:8 0 0 0; -fx-padding:10;"));
        button.setOnMouseExited(e -> {
            if (button != selectedButton) {
                button.setStyle("-fx-font-size:25px; -fx-background-color:transparent ; -fx-border-color:" + borderColor
                        + "; -fx-text-fill:black; -fx-font-weight:900; -fx-font-family:Verdana; -fx-border-width:8 0 0 0; -fx-padding:10;");
            }
        });
        button.setOnMouseClicked(e -> {
            if (selectedButton != null) {
                String prevColor = selectedButton.getStyle().split(";")[2].split(":")[1];
                selectedButton.setStyle("-fx-font-size:25px; -fx-background-color:transparent ; -fx-border-color:"
                        + prevColor
                        + "; -fx-text-fill:black; -fx-font-weight:900; -fx-font-family:Verdana; -fx-border-width:8 0 0 0; -fx-padding:10;");
            }
            selectedButton = button;
            button.setStyle("-fx-font-size:25px; -fx-background-color:transparent ; -fx-border-color:" + borderColor
                    + "; -fx-text-fill:" + borderColor
                    + "; -fx-font-weight:900; -fx-font-family:Verdana; -fx-border-width:0 0 8 0; -fx-padding:10;");
        });
    }

    private void farmerLoginPage() {
        farmerLoginView = new Group();
        ImageView iv = new ImageView(new Image("farmForm/img3.jpg"));
        iv.setFitWidth(1620);
        iv.setFitHeight(400);

        HBox left = new HBox(iv);
        left.setStyle("-fx-padding: 0 0 0 0 ");
        left.setAlignment(Pos.BOTTOM_CENTER);

        // ------------------------------------------------------------------------------------------------------------------
        // Back button
        ImageView bimg = new ImageView(new Image("icons/back.png"));
        Button backButton = new Button();
        backButton.setGraphic(bimg);
        backButton.setOnAction(event -> {
            farmerController.navigateBack();
            System.out.println("Back button clicked");
        });
        backButton.setStyle(
                "-fx-font-size:30; -fx-background-color:transparent; -fx-text-fill:white; -fx-background-radius:50; ");
        backButton.setAlignment(Pos.TOP_LEFT);
        backButton.setPrefWidth(40);

        HBox back = new HBox(backButton);
        back.setStyle("-fx-padding: 10 0 0 10");

        // ---------------------------------------------------------------------------------------------

        Label lb1 = new Label("Welcome Back !!");
        lb1.setFont(new Font("IMPACT", 50));

        ImageView m = new ImageView(new Image("icons/mail.gif"));
        m.setFitHeight(30);
        m.setFitWidth(30);

        email = new TextField();
        email.setPromptText("Email");
        email.setFont(new Font(30));
        email.setStyle("-fx-background-color: transparent;");

        HBox mailBox = new HBox(10, m, email);
        mailBox.setStyle("-fx-background-color: transparent;  -fx-border-color: grey; -fx-border-width: 0 0 2 0;");
        mailBox.setAlignment(Pos.CENTER_LEFT);

        // ---------------------------------------------------------------------------------------------------------------------

        ImageView p = new ImageView(new Image("icons/lock.gif"));
        p.setFitHeight(30);
        p.setFitWidth(30);

        pass = new PasswordField();
        pass.setPromptText("Password");
        pass.setFont(new Font(30));
        pass.setStyle("-fx-background-color: transparent;");

        HBox passBox = new HBox(10, p, pass);
        passBox.setStyle("-fx-background-color: transparent;  -fx-border-color: grey; -fx-border-width: 0 0 2 0;");
        passBox.setAlignment(Pos.CENTER_LEFT);

        // ------------------------------------------------------------------------------------------------

        Button btn = new Button("Login");
        btn.setFont(new Font(30));
        btn.setPrefWidth(170);

        btn.setStyle(
                "-fx-background-color:black; -fx-border-color: black; -fx-border-width: 0; -fx-text-fill: white; -fx-border-radius: 100; -fx-background-radius: 100; ");

        btn.setOnMouseEntered(e -> {
            btn.setScaleX(1.1);
            btn.setScaleY(1.1);

        });

        btn.setOnMouseExited(e -> {
            btn.setScaleX(1);
            btn.setScaleY(1);

        });

        btn.setOnAction(e -> {

            if (email.getText().isEmpty() || pass.getText().isEmpty()) {
                showAlert("Error", null, "Enter required data to login");
            } else {

                System.out.println(email.getText());
                System.out.println(pass.getText());
                try {
                    boolean haveAcc = farmerController.handleLogin(email.getText(), pass.getText());
                    if (!haveAcc) {
                        showAlert("Error", null, "Don't have Account");
                        return;
                    }
                    // Farmer farmerImg =
                    // farmerController.getFarmer(FirebaseAuthentication.getUserUid());
                    // System.out.println(farmerImg.getProfilePageUrl());
                    updateProfileData();
                } catch (Exception excep) {
                    showAlert("Error", null, excep.getMessage().contains("Bad Request") ? "Invalid Password or Email"
                            : "NetworkError! check NetwFork Connection");
                }
            }

            // content= new HBox(homeView);
        });
        // --------------------------------------------------------

        Label lb4 = new Label("Don't have an account?");
        lb4.setFont(new Font(25));

        Button lg = new Button("Sign Up");
        lg.setFont(new Font(25));
        lg.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: orange; -fx-border-color: none; -fx-padding: 0 0 1 0;");

        lg.setOnMouseEntered(e -> {
            lg.setStyle(
                    "-fx-text-fill:red; -fx-background-color: transparent; -fx-border-color: none; -fx-padding: 0 0 1 0;");

        });

        lg.setOnMouseExited(e -> {

            lg.setStyle(
                    "-fx-text-fill:orange; -fx-background-color: transparent; -fx-border-color: none; -fx-padding: 0 0 1 0;");

        });

        lg.setOnAction(e -> {
            farmerController.navigateToSignup();
        });

        HBox lhb = new HBox(30, lb4, lg);
        lhb.setStyle("-fx-padding:0");
        lhb.setAlignment(Pos.CENTER);

        // -----------------------------------------------------------------------------------------------------------

        VBox right = new VBox(40, lb1, mailBox, passBox, btn, lhb);
        right.setAlignment(Pos.CENTER);
        HBox hb = new HBox(right);
        hb.setPrefWidth(600);
        hb.setAlignment(Pos.CENTER);

        VBox vb = new VBox(hb, left);
        vb.setPrefWidth(1600);
        vb.setPrefHeight(1000);
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-padding: 0 0 0 10");

        HBox hbMain = new HBox(vb);
        hbMain.setPrefSize(1800, 1000);
        hbMain.setStyle("-fx-padding: 80 0 0 60; ");
        hbMain.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(back, hbMain);
        vbox.setPrefWidth(1800);
        vbox.setPrefHeight(1000);
        vbox.setAlignment(Pos.TOP_LEFT);

        farmerLoginView = new Group(vbox);
    }

    private void updateProfileData() {

        Farmer farmerImg = farmerController.getFarmer(FirebaseAuthentication.getUserUid());
        if (farmerImg.getProfilePageUrl() != null) {
            // System.out.println(farmerImg.getProfilePageUrl());
            profileImageView.setImage(new Image(farmerImg.getProfilePageUrl()));

            ProfilePageView.profileImageView.setImage(new Image(farmerImg.getProfilePageUrl()));
            // ///////////////////////////////////////////
        }

        // ProfilePageView.profileImageView.setImage(new
        // Image(farmerImg.getFarmerPhoto()));

        ProfilePageView.firstNameField.setText(farmerImg.getFarmerName().split(" ")[0]);
        ProfilePageView.emailField.setText(farmerImg.getFarmerEmailId());

        String[] nameParts = farmerImg.getFarmerName().split(" ");
        if (nameParts.length > 1) {
            ProfilePageView.lastNameField.setText(nameParts[1]);
        }

        String farmerPhoneNumber = "" + farmerImg.getFarmerPhoneNumber();
        ProfilePageView.phoneField
                .setText(farmerPhoneNumber != null && !farmerPhoneNumber.trim().isEmpty() ? farmerPhoneNumber : "");

        String farmerState = farmerImg.getFarmerState();
        ProfilePageView.stateField.setText(farmerState != null && !farmerState.trim().isEmpty() ? farmerState : "");

        String farmerCity = farmerImg.getFarmerCity();
        ProfilePageView.cityField.setText(farmerCity != null && !farmerCity.trim().isEmpty() ? farmerCity : "");

        String farmerAddress = farmerImg.getFarmerAddress();
        ProfilePageView.addressField
                .setText(farmerAddress != null && !farmerAddress.trim().isEmpty() ? farmerAddress : "");

        String farmerGender = farmerImg.getFarmerGender();
        ProfilePageView.genderComboBox
                .setValue(farmerGender != null && !farmerGender.trim().isEmpty() ? farmerGender : null);

        String farmerPincode = "" + farmerImg.getFarmerPincode();
        ProfilePageView.pinCodeField.setText(!farmerPincode.trim().isEmpty() ? farmerPincode : "");
    }

    // getsignuppage
    private void farmerSignUpPage() {

        ImageView iv = new ImageView(new Image("farmForm/back5.jpg"));
        iv.setFitWidth(900);
        iv.setFitHeight(800);

        VBox left = new VBox(iv);
        left.setPrefWidth(700);
        left.setPrefHeight(700);
        left.setAlignment(Pos.CENTER);

        // ------------------------------------------------------------------------------------------

        // Back button
        ImageView bimg = new ImageView(new Image("icons/back.png"));
        Button backButton = new Button();
        backButton.setGraphic(bimg);
        backButton.setOnAction(event -> {
            farmerController.navigateBack();
            System.out.println("Back button clicked");
        });
        backButton.setStyle(
                "-fx-font-size:30; -fx-background-color:transparent; -fx-text-fill:white; -fx-background-radius:50; ");
        backButton.setAlignment(Pos.TOP_LEFT);
        backButton.setPrefWidth(40);

        HBox back = new HBox(backButton);
        back.setStyle("-fx-padding: 10 0 0 10");

        // ------------------------------------------------------------------------------------------

        Label heading = new Label("For Farmerss");
        heading.setFont(new Font("IMPACT", 40));
        heading.setAlignment(Pos.CENTER);

        Label lb1 = new Label("Save your account now");
        lb1.setFont(new Font("IMPACT", 50));

        Label lb2 = new Label("  The best fertilizer for a piece of\n land is the footprints of its owner.");
        lb2.setFont(new Font(20));
        lb2.setStyle("-fx-font-weight:500");

        VBox vb1 = new VBox(20, heading, lb1, lb2);
        vb1.setAlignment(Pos.CENTER);

        // -------------------------------------------------

        ImageView usr = new ImageView(new Image("icons/user.gif"));
        usr.setFitHeight(30);
        usr.setFitWidth(30);

        name = new TextField();
        name.setPromptText("Name or nickname");
        name.setFont(new Font(30));
        name.setStyle("-fx-background-color: transparent;");

        HBox userBox = new HBox(10, usr, name);
        userBox.setStyle("-fx-background-color: transparent;  -fx-border-color: grey; -fx-border-width: 0 0 2 0;");
        userBox.setAlignment(Pos.CENTER_LEFT);

        // ---------------------------------------------------

        ImageView m = new ImageView(new Image("icons/mail.gif"));
        m.setFitHeight(30);
        m.setFitWidth(30);

        email_signUp = new TextField();
        email_signUp.setPromptText("Email");
        email_signUp.setFont(new Font(30));
        email_signUp.setStyle("-fx-background-color: transparent;");

        HBox mailBox = new HBox(10, m, email_signUp);
        mailBox.setStyle("-fx-background-color: transparent;  -fx-border-color: grey; -fx-border-width: 0 0 2 0;");
        mailBox.setAlignment(Pos.CENTER_LEFT);

        // ----------------------------------------------------------------------------------------------

        ImageView p = new ImageView(new Image("icons/lock.gif"));
        p.setFitHeight(30);
        p.setFitWidth(30);

        pass_signUp = new PasswordField();
        pass_signUp.setPromptText("Password");
        pass_signUp.setFont(new Font(30));
        pass_signUp.setStyle("-fx-background-color: transparent;");

        HBox passBox = new HBox(10, p, pass_signUp);
        passBox.setStyle("-fx-background-color: transparent;  -fx-border-color: grey; -fx-border-width: 0 0 2 0;");
        passBox.setAlignment(Pos.CENTER_LEFT);

        // ---------------------------------------------------------------------------------------------

        ImageView rp = new ImageView(new Image("icons/lock2.gif"));
        rp.setFitHeight(35);
        rp.setFitWidth(35);

        PasswordField rePass = new PasswordField();
        rePass.setPromptText("Re-enter Password");
        rePass.setFont(new Font(30));
        rePass.setStyle("-fx-background-color: transparent;");

        HBox rePassBox = new HBox(10, rp, rePass);
        rePassBox.setStyle(
                "-fx-background-color: transparent; -fx-padding: 0 0 0 0;  -fx-border-color: grey; -fx-border-width: 0 0 2 0;");
        rePassBox.setAlignment(Pos.CENTER_LEFT);

        // -----------------------------------------------------------------------------

        VBox vb2 = new VBox(30, userBox, mailBox, passBox, rePassBox);
        vb2.setStyle("-fx-padding:0");

        // ------------------------------------------------------

        Button btn = new Button("Sign up ");
        btn.setFont(new Font(30));
        btn.setStyle(
                "-fx-background-color:black; -fx-border-color: black; -fx-border-width: 0; -fx-text-fill: white; -fx-border-radius: 100; -fx-background-radius: 100");

        btn.setOnMouseEntered(event -> {

            btn.setScaleX(1.1);
            btn.setScaleY(1.1);

        });

        btn.setOnMouseExited(event -> {
            btn.setScaleX(1);
            btn.setScaleY(1);

        });

        btn.setOnAction(e -> {

            if (name.getText().isEmpty() || email_signUp.getText().isEmpty() || pass_signUp.getText().isEmpty()
                    || rePass.getText().isEmpty()) {

                showAlert("Error", null, "Enter required fields.");

            } else if (pass_signUp.getText().isEmpty() || pass_signUp.getText().length() < 6) {

                showAlert("Error", null, "Password is required and must be greater than or equals to 6 characters");
            } else if (pass_signUp.getText().length() >= 6 && !pass_signUp.getText().equals(rePass.getText())) {

                showAlert("Error", null, "Both passwords don't match.");

            } else {
                System.out.println(name.getText());
                System.out.println(email_signUp.getText());
                System.out.println(pass_signUp.getText());
                boolean haveAcc = farmerController.handleSignUp(name.getText(), email_signUp.getText(),
                        pass_signUp.getText());
                if (haveAcc) {
                    name.clear();
                    email_signUp.clear();
                    pass_signUp.clear();
                    rePass.clear();
                    showAlert("Error", null, "Already have Account");
                }
                name.clear();
                email_signUp.clear();
                pass_signUp.clear();
                rePass.clear();
                updateProfileData();
                ProfilePageView.profileImageView.setImage(new Image("file:farmease\\farmease\\src\\main\\resources\\icons\\profilepic.jpg"));

            }

            // content = new HBox(homeView);

        });
        // --------------------------------------------------------

        Label lb4 = new Label("Already have an account?");
        lb4.setFont(new Font(25));

        Button lg = new Button("Login");
        lg.setFont(new Font(25));
        lg.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: orange; -fx-border-color: none; -fx-padding: 0 0 1 0;");

        lg.setOnMouseEntered(event -> {
            lg.setStyle(
                    "-fx-text-fill:red; -fx-background-color: transparent; -fx-border-color: none; -fx-padding: 0 0 1 0;");

        });

        lg.setOnMouseExited(event -> {

            lg.setStyle(
                    "-fx-text-fill:orange; -fx-background-color: transparent; -fx-border-color: none; -fx-padding: 0 0 1 0;");

        });

        lg.setOnAction(e -> {
            farmerController.navigateToLogin();
        });

        HBox lhb = new HBox(40, lb4, lg);
        lhb.setStyle("-fx-padding:30");
        lhb.setAlignment(Pos.CENTER);

        // ------------------------------------------------------------------------------------------

        VBox right = new VBox(30, vb1, vb2, btn, lhb);
        right.setPrefWidth(700);
        right.setPrefHeight(700);
        right.setAlignment(Pos.CENTER);

        // --------------------------------------------------------------------------------------------

        HBox hbox = new HBox(100, left, right);
        hbox.setPrefWidth(1800);
        hbox.setPrefHeight(1000);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-padding: 0 80 0 20;");

        // --------------------------------------------------------------------------------------------

        VBox vbox = new VBox(back, hbox);
        vbox.setPrefWidth(1800);
        vbox.setPrefHeight(1000);
        vbox.setAlignment(Pos.TOP_LEFT);

        // --------------------------------------------------------------------------------------------
        farmerSignUpView = new Group(vbox);
    }

    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.setX(800);
        alert.setY(50);
        alert.showAndWait();

    }

    public Group getFarmerLoginView() {
        return farmerLoginView;
    }

    public Group getFarmerSignUpView() {
        return farmerSignUpView;
    }

    public Group getFarmerHomePage() {

        return farmerHomePageView;
    }

}
