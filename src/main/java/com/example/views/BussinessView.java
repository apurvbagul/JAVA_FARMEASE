package com.example.views;

import com.example.controller.BusinessController;

import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class BussinessView {

    private Button selectedButton = null;
    private Group BussinessLoginView;
    private Group BussinessSignUpView;
    private Group BussinessHomeView;
    private ScrollPane scrollpane;
    private HBox content;

    private BusinessController businessController;

    public BussinessView(String viewType, BusinessController businessController) {
        this.businessController = businessController;

        bussinessmanLoginPage();
        bussinessmanSignUpPage();
        businessmanHomePage();
    }

    // view code for bussiness homepage
    private void businessmanHomePage() {

        Image img = new Image("assets/images/logo.gif");
        ImageView iv = new ImageView(img);
        iv.setFitHeight(55);
        iv.setFitWidth(55);

        Label title = new Label("FarmEase");
        title.setFont(new Font("IMPACT", 50));
        title.setTextFill(Color.GREEN);

        HBox navLeft = new HBox(30, iv, title);
        navLeft.setAlignment(Pos.CENTER);

       Button home = new Button("Home");
        home.setPrefWidth(300);
        home.setStyle(
                "-fx-font-family:Verdana; -fx-background-color:transparent; -fx-font-size:30; -fx-font-weight:bold;");

        // Profile Photo
        Image profileImage = new Image("file:C:\\Users\\soham\\OneDrive\\Desktop\\Project\\farmease\\farmease\\src\\main\\resources\\assets\\images\\logo.jpg");
        ImageView profileImageView = new ImageView(profileImage);
        profileImageView.setFitHeight(50);
        profileImageView.setFitWidth(50);
        profileImageView.setClip(new Circle(25, 25, 25)); // Circular shape

        // Menu with options
        Image menuImage = new Image("file:C:\\Users\\soham\\OneDrive\\Desktop\\Project\\farmease\\farmease\\src\\main\\resources\\icons\\menu.png");
        ImageView menuImageView = new ImageView(menuImage);
        menuImageView.setFitHeight(50);
        menuImageView.setFitWidth(50);

        MenuButton menuButton = new MenuButton();
        menuButton.setGraphic(menuImageView);
        
        menuButton.setStyle("-fx-background-color:transparent; -fx-font-size:20px; -fx-padding:10px;");
        MenuItem logoutOption = new MenuItem("Logout");
        MenuItem profileOption = new MenuItem("Profile");
        
        logoutOption.setStyle("-fx-font-size:20px; -fx-padding:10px;");
        profileOption.setStyle("-fx-font-size:20px; -fx-padding:10px;");
        
        menuButton.getItems().addAll(profileOption, logoutOption);

        HBox navRight = new HBox(20, home, profileImageView, menuButton);
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
                "-fx-font-size:25px;-fx-background-color:transparent ;  -fx-border-color:brown; -fx-text-fill:black; -fx-font-weight:900; -fx-font-family:Verdana; -fx-border-width:8 0 0 0; -fx-padding:10;");

        Button opt3 = new Button("Sell Products");
        opt3.setPrefWidth(300);
        opt3.setStyle(
                "-fx-font-size:25px; -fx-background-color:transparent ;  -fx-border-color:gold; -fx-text-fill:black; -fx-font-weight:900; -fx-font-family:Verdana; -fx-border-width:8 0 0 0; -fx-padding:10;");

        Button opt4 = new Button("Weather Info");
        opt4.setPrefWidth(300);
        opt4.setStyle(
                "-fx-font-size:25px; -fx-background-color:transparent ;  -fx-border-color:orange; -fx-text-fill:black; -fx-font-weight:900; -fx-font-family:Verdana; -fx-border-width:8 0 0 0; -fx-padding:10; ");
                opt4.setOnAction(e -> {
                    content.getChildren().clear(); // Clear all existing children
                    System.out.println(content.getChildren());
                    StackPane weatherView = WeatherView.showWeather(); // Get the new weather view
                    content.getChildren().add(weatherView); // Add the new weather view
                });
                
        Button opt5 = new Button("Feedback");
        opt5.setPrefWidth(300);
        opt5.setStyle(
                "-fx-font-size:25px; -fx-background-color:transparent ; -fx-border-color:green; -fx-text-fill:black; -fx-font-weight:700; -fx-font-family:Verdana; -fx-border-width:8 0 0 0; -fx-padding:10; ");

        // Event handlers for hover and click
        setButtonEvents(opt1, "red");
        setButtonEvents(opt2, "brown");
        setButtonEvents(opt3, "gold");
        setButtonEvents(opt4, "orange");
        setButtonEvents(opt5, "green");

        HBox opt = new HBox(15, opt1, opt2, opt3, opt4, opt5);
        opt.setAlignment(Pos.CENTER);
        opt.setPrefWidth(1800);
        opt.setPrefHeight(100);
        opt.setStyle("-fx-padding:30 0 0 0; ");

        HBox mid = new HBox(opt);
        mid.setAlignment(Pos.CENTER);
        mid.setPrefWidth(1800);
        mid.setStyle("-fx-border-color:black; -fx-border-width:0 0 1 0");


        ImageView cm = new ImageView(new Image("file:farmease\\farmease\\src\\main\\resources\\assets\\images\\comingSoon.png"));
        cm.setFitWidth(1800);
        cm.setFitHeight(800);

         content = new HBox();
         content.getChildren().add(cm);
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

        BussinessHomeView = new Group(pg);

       
        logoutOption.setOnAction(e -> {
           
            // Call method to logout
            logoutSession();
        
    });
    }

    private void logoutSession() {
        try {
            businessController.handleLogout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    // view code for business loginpage
    private void bussinessmanLoginPage() {

        BussinessLoginView = new Group();
        ImageView iv1 = new ImageView(new Image("farmForm/back6.jpg"));
        iv1.setFitWidth(800);
        iv1.setFitHeight(800);

        HBox left = new HBox(iv1);
        left.setStyle("-fx-padding: 0 0 0 0 ");
        left.setAlignment(Pos.BOTTOM_CENTER);

        // ------------------------------------------------------------------------------------------------------------------

        // Back button
        ImageView bimg = new ImageView(new Image("icons/back.png"));
        Button backButton = new Button();
        backButton.setGraphic(bimg);
       
        backButton.setOnAction(event -> {   
            businessController.navigateBack();
             System.out.println("Back button clicked");        
         });
        backButton.setStyle(
                "-fx-font-size:30; -fx-background-color:transparent; -fx-text-fill:white; -fx-background-radius:50; ");
        backButton.setAlignment(Pos.TOP_LEFT);
        backButton.setPrefWidth(40);

        HBox back = new HBox(backButton);
        back.setStyle("-fx-padding: 10 0 0 10");

        // -------------------------------------------------------------------------------------------------------

        Label lb1 = new Label("Welcome Back !!");
        lb1.setFont(new Font("IMPACT", 50));

        ImageView m = new ImageView(new Image("icons/mail.gif"));
        m.setFitHeight(30);
        m.setFitWidth(30);

        TextField email = new TextField();
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

        PasswordField pass = new PasswordField();
        pass.setPromptText("Password");
        pass.setFont(new Font(30));
        pass.setStyle("-fx-background-color: transparent;");

        HBox passBox = new HBox(10, p, pass);
        passBox.setStyle("-fx-background-color: transparent;  -fx-border-color: grey; -fx-border-width: 0 0 2 0;");
        passBox.setAlignment(Pos.CENTER_LEFT);

        // ------------------------------------------------------------------------------------------------

        Button btn = new Button("Login");
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

        btn.setOnAction(event -> {
            if (email.getText().isEmpty() || pass.getText().isEmpty()) {
                showAlert("Error", null, "Enter required data to login");
            } else {
                System.out.println(email.getText());
                System.out.println(pass.getText());
                try {
                    boolean haveAcc = businessController.handleLogin(email.getText(), pass.getText());
                    if (!haveAcc) {
                        email.clear();
                        pass.clear();
                        showAlert("Error", null, "Don't have Account");
                    }
                } catch (Exception e) {
                    showAlert("Error", null, "Login failed: No Bussinessman found  " );//+ e.getMessage());
                }
                email.clear();
                pass.clear();
            }
        });
        // --------------------------------------------------------

        Label lb4 = new Label("Don't have an account?");
        lb4.setFont(new Font(25));

        Button lg = new Button("Sign Up");
        lg.setFont(new Font(25));
        btn.setPrefWidth(170);
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
            businessController.navigateToSignup();
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

        VBox vb = new VBox(hb);
        vb.setPrefWidth(1600);
        vb.setPrefHeight(1000);
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-padding: 0 0 0 10");

        HBox hbMain = new HBox(70, left, vb);
        hbMain.setPrefSize(1800, 1000);
        hbMain.setStyle("-fx-padding: 0 150 0 60; ");
        hbMain.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(back, hbMain);
        vbox.setPrefWidth(1800);
        vbox.setPrefHeight(1000);
        vbox.setAlignment(Pos.TOP_LEFT);

        BussinessLoginView.getChildren().add(vbox);
    }

    // view code for business signuppage
    private void bussinessmanSignUpPage() {

        ImageView iv = new ImageView(new Image("farmForm/back1.jpg"));
        iv.setFitWidth(900);
        iv.setFitHeight(700);

        VBox left = new VBox(iv);
        left.setPrefWidth(700);
        left.setPrefHeight(700);
        left.setAlignment(Pos.CENTER);

        // Back button
        ImageView bimg = new ImageView(new Image("icons/back.png"));
        Button backButton = new Button();
        backButton.setGraphic(bimg);
        backButton.setOnAction(event -> {   
            businessController.navigateBack();
             System.out.println("Back button clicked");        
         });
        backButton.setStyle(
                "-fx-font-size:30; -fx-background-color:transparent; -fx-text-fill:white; -fx-background-radius:50; ");
        backButton.setAlignment(Pos.TOP_LEFT);
        backButton.setPrefWidth(40);

        HBox back = new HBox(backButton);
        back.setStyle("-fx-padding: 10 0 0 10");

        // ------------------------------------------------------------------------------------------

        Label heading = new Label("For Business");
        heading.setFont(new Font("IMPACT", 40));
        heading.setAlignment(Pos.CENTER);

        Label lb1 = new Label("Save your account now");
        lb1.setFont(new Font("IMPACT", 50));

        Label lb2 = new Label(
                "The farmer has to be an optimist or he wouldn’t\n                         still be a farmer.");
        lb2.setFont(new Font(20));
        lb2.setStyle("-fx-font-weight:500");

        VBox vb1 = new VBox(30, heading, lb1, lb2);
        vb1.setAlignment(Pos.CENTER);

        // -------------------------------------------------

        ImageView usr = new ImageView(new Image("icons/user.gif"));
        usr.setFitHeight(30);
        usr.setFitWidth(30);

        TextField name = new TextField();
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

        TextField email = new TextField();
        email.setPromptText("Email");
        email.setFont(new Font(30));
        email.setStyle("-fx-background-color: transparent;");

        HBox mailBox = new HBox(10, m, email);
        mailBox.setStyle("-fx-background-color: transparent;  -fx-border-color: grey; -fx-border-width: 0 0 2 0;");
        mailBox.setAlignment(Pos.CENTER_LEFT);

        // ----------------------------------------------------------------------------------------------
 
        ImageView p = new ImageView(new Image("icons/lock.gif"));
        p.setFitHeight(30);
        p.setFitWidth(30);

        PasswordField pass = new PasswordField();
        pass.setPromptText("Password");
        pass.setFont(new Font(30));
        pass.setStyle("-fx-background-color: transparent;");

        HBox passBox = new HBox(10, p, pass);
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

        btn.setOnAction(event -> {
            if (name.getText().isEmpty() || email.getText().isEmpty() || pass.getText().isEmpty()
                    || rePass.getText().isEmpty()) {

                showAlert("Error", null, "Enter required fields.");

            } else if (pass.getText().isEmpty() || pass.getText().length() < 6) {

                showAlert("Error", null, "Password is required and must be greater than or equals to 6 characters.");

            } else if (pass.getText().length() >= 6 && !pass.getText().equals(rePass.getText())) {

                showAlert("Error", null, "Both password don't match");

            } else {
                System.out.println(name.getText());
                System.out.println(email.getText());
                System.out.println(pass.getText());
                boolean haveAcc = businessController.handleSignUp(name.getText(), email.getText(), pass.getText());
                if (haveAcc) {
                    showAlert("Error", null, "Already have an account");
                }
                name.clear();
                email.clear();
                pass.clear();
                rePass.clear();
            }

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
            businessController.navigateToLogin();
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
        hbox.setStyle("-fx-padding: 0 80 0 20; ");

        VBox vbox = new VBox(back, hbox);
        vbox.setPrefWidth(1800);
        vbox.setPrefHeight(1000);
        vbox.setAlignment(Pos.TOP_LEFT);

        // --------------------------------------------------------------------------------------------

        BussinessSignUpView = new Group(vbox);
    }

    private void showAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.setX(800);
        alert.setY(50);
        alert.showAndWait();
    }
    // /returns loginPage
    public Group getBusinessmanLoginPage() {
        return BussinessLoginView;
    }

    // returns Signuppage
    public Group getBusinessmanSignUpPage() {
        return BussinessSignUpView;
    }

    // returns homePage
    public Group getBusinessmanHomePage() {
        return BussinessHomeView;
    }
}
