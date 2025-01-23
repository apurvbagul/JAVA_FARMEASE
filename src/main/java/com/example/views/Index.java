package com.example.views;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class Index {

    private VBox content;
    private StackPane sp;
    private ComboBox<String> login;
    private ComboBox<String> signup;

    public Index() {
        initializeView();
    }

    private void initializeView() {
        sp = new StackPane();
        
        Image img = new Image("file:C:\\Users\\soham\\OneDrive\\Desktop\\javafx\\form\\src\\main\\resources\\farmForm\\land.jpg");
        ImageView iv = new ImageView(img);
        iv.setFitHeight(1000);
        iv.setFitWidth(1800);
        iv.setPreserveRatio(false); 
        Image logo = new Image("farmForm/logo2.gif");
        ImageView liv = new ImageView(logo);
        liv.setFitWidth(80);
        liv.setFitHeight(80);

        Label title = new Label("FarmEase");
        title.setFont(new Font("IMPACT", 50));
        title.setStyle("-fx-text-fill:linear-gradient(to right, #000000, #434343)");
        //title.setTextFill(Color.BLACK);


        HBox navbarLeft = new HBox(40,liv,title) ;  //navbar
        navbarLeft.setPrefHeight(150);
        navbarLeft.setStyle("-fx-padding:40");
        navbarLeft.setAlignment(Pos.CENTER);

        // -----------------------------------------------------------------------------------------------------------------
        login = new ComboBox<>();
        login.getItems().addAll("Farmer", "Admin", "Business");
        login.setPromptText("Login as");
        login.setStyle("-fx-background-color:transparent; -fx-font-family:IMPACT; -fx-font-size:35; -fx-text-fill:black; ");

        signup = new ComboBox<>();
        signup.getItems().addAll("Farmer", "Admin", "Business");
        signup.setPromptText("Signup as");
        signup.setStyle("-fx-background-color:transparent; -fx-font-family:IMPACT; -fx-font-size:35;");


        signup.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item);
                            setAlignment(Pos.CENTER);  
                            setStyle(
                                "-fx-background-color: linear-gradient(to right, rgba(255, 255, 255, 0.8), rgba(200, 200, 200, 0.8)); " +
                                "-fx-text-fill: black; " +
                                "-fx-background-radius: 0; " +
                                "-fx-border-color: black; " +
                                "-fx-border-width: 1; " +
                                "-fx-border-radius: 0; " +
                                "-fx-padding: 5;" +  
                                "-fx-margin: 0;"  
                            );
                        }
                    }
                };
                return cell;
            }
        });

        login.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> listView) {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item);
                            setAlignment(Pos.CENTER);  
                            setStyle(
                                "-fx-background-color: linear-gradient(to right, rgba(255, 255, 255, 0.8), rgba(200, 200, 200, 0.8)); " +
                                "-fx-text-fill: black; " +
                                "-fx-background-radius: 0; " +
                                "-fx-border-color: black; " +
                                "-fx-border-width: 1; " +
                                "-fx-border-radius: 0; " +
                                "-fx-padding: 5;" +  
                                "-fx-margin: 0;"  
                            );
                        }
                    }
                };
                return cell;
            }
        });

        


        HBox navbarRight = new HBox(40,login,signup) ;  
        navbarRight.setPrefHeight(150);
        navbarRight.setStyle("-fx-padding:60");
        navbarRight.setAlignment(Pos.CENTER);

        //----------------------------------------------------------------------------------------------------------

        HBox navbar = new HBox(600,navbarLeft,navbarRight);     //navbar
        navbar.setStyle("-fx-border-width:5; -fx-border-radius:25; -fx-background-color: linear-gradient(to right, rgba(255, 255, 255, 0.8), rgba(200, 200, 200, 0.8)); -fx-background-radius:25; -fx-border-color:white");
        navbar.setAlignment(Pos.CENTER);

        // ----------------------------------------------------------------------------------------------------------------

        Label story = new Label("FarmEase");
        story.setFont(new Font("Lucida Sans Unicode",45));
        story.setStyle("-fx-text-fill:white");
        
        Label info = new Label(" Agriculture is the backbone of our world, providing essential resources and\n sustaining communities. At farmEase, we are passionate about supporting farmers and\n agricultural professionals by offering innovative tools, comprehensive resources, and\n a vibrant community. Our mission is to empower agriculture through knowledge, technology,\n and collaboration.");
        info.setFont(new Font("Lucida Sans Unicode",27));
        info.setStyle("-fx-padding:0 0 0 50; -fx-text-fill:white; -fx-line-spacing:20 ");

        VBox desc = new VBox(40,story,info);

        // --------------------------------------------------------------------------------------------------------------

        Image flogo = new Image("farmForm/logo.gif");
        ImageView fiv = new ImageView(flogo);
        fiv.setFitHeight(50);
        fiv.setFitWidth(50);
        fiv.setStyle("-fx-background-radius:50");

        Label ftext = new Label("FarmEase");
        ftext.setFont(new Font("IMPACT",30));
        ftext.setStyle("-fx-text-fill:white");

        HBox finfo = new HBox(20,fiv,ftext);
        finfo.setAlignment(Pos.CENTER);

        Label copyright = new Label("                           @ 2024 FarmEase");
        copyright.setFont(new Font("IMPACT",20));
        copyright.setStyle("-fx-text-fill:white");

        VBox footer = new VBox(20,finfo,copyright);
        footer.setStyle("-fx-padding:10 0 0 1400");
        



        // -----------------------------------------------------------------------------------------------------------------

        
        content = new VBox(100,navbar,desc,footer);     //  content
        content.setStyle("-fx-padding:40");


        sp.getChildren().addAll(iv, content);
    }

    public StackPane getContent() {
        return sp;
    }

    public ComboBox<String> getLoginComboBox() {
        return login;
    }

    public ComboBox<String> getSignupComboBox() {
        return signup;
    }
}
