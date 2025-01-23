package com.example.views;

import com.example.controller.FarmerController;
import com.example.model.FeedbackForm;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class FeedbackFormView {
    private static ChoiceBox<String> sel;

    private static CheckBox incrPro;
    private static CheckBox redCos;
    private static CheckBox impDes;
    private static CheckBox betPest;

    private static TextArea txt;
    private static TextArea txt2;

    private static CheckBox y1;
    private static CheckBox y2;
    private static CheckBox y3;
    private static CheckBox y4;

    private static FarmerController farmerController;

    public FeedbackFormView(FarmerController farmerController) {
        FeedbackFormView.farmerController = farmerController;
    }

    public static HBox showFeedbackForm() {
        // Image image = new Image("file:farmease/farmease/src/main/resources/icons/back.png"); // Update the path to your
        //                                                                                      // image file
        // ImageView topLeftImageView = new ImageView(image);
        // topLeftImageView.setFitWidth(50); // Set the width of the image
        // topLeftImageView.setFitHeight(50); // Set the height of the image

        Label head = new Label("User Satisfaction Feedback Form");
        head.setFont(new Font(35));
        head.setTextFill(Color.ALICEBLUE);
        head.setStyle("-fx-font-weight: 900");

        // Add a download button to the right
        Button db = new Button("Clear");
        db.setFont(new Font(20));
        db.setStyle("-fx-background-color: lightgreen; -fx-border-color: green; -fx-background-radius:5 -fx-border-radius: 5");


        // Create a spacer Region
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS); // Allow spacer to grow

        // Add the image to the heading HBox
        HBox heading = new HBox(20, head, spacer, db); // Adjust spacing as needed
        heading.setPrefWidth(1100);
        heading.setPrefHeight(50);
        heading.setStyle("-fx-background-color: rgb(79, 111, 82); -fx-padding: 15");
        heading.setAlignment(Pos.CENTER_LEFT); // Align to the left
        HBox.setHgrow(head, Priority.ALWAYS); // Allow head label to grow

        // ------------------------------------------------------------------------------

        Label lb = new Label("Farm Feedback ");
        lb.setFont(new Font(30));

        // -------------------------------

        Label lb1 = new Label("How often do you use the farming application?");
        lb1.setFont(new Font(20));
        sel = new ChoiceBox<>();
        sel.getItems().addAll("--select--", "Daily", "Weekly", "Monthly", "Rarely");
        sel.setValue("--select--");
        sel.setPrefWidth(1050);
        sel.setPrefHeight(20);
        sel.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 20; ");

        VBox vb1 = new VBox(5, lb1, sel);

        // -----------------------------------------------------------------------------

        Label lb2 = new Label(
                "Have you encountered any bugs or issues while using the application? If yes, please describe them.");
        lb2.setFont(new Font(20));
        txt = new TextArea();
        txt.setPrefWidth(1000);
        txt.setPrefHeight(150);
        txt.setFont(new Font(20));

        VBox vb2 = new VBox(5, lb2, txt);

        // -----------------------------------------------------------------------------

        Label lb3 = new Label("How has the application impacted your farming activities?");
        lb3.setFont(new Font(20));
        incrPro = new CheckBox("Increased productivity");
        incrPro.setFont(new Font(18));
        redCos = new CheckBox("Reduced costs");
        redCos.setFont(new Font(18));
        impDes = new CheckBox("Improved decision making");
        impDes.setFont(new Font(18));
        betPest = new CheckBox("Better pest and disease management");
        betPest.setFont(new Font(18));

        HBox chb1 = new HBox(124, incrPro, redCos);
        HBox chb2 = new HBox(89, impDes, betPest);

        VBox chk = new VBox(3, lb3, chb1, chb2);
        chk.setStyle("-fx-padding: 10");

        VBox vb3 = new VBox(5, lb3, chk);

        // -----------------------------------------------------------------------------

        Label lb4 = new Label("Are there any features you find difficult to use? Please explain.");
        lb4.setFont(new Font(20));
        txt2 = new TextArea();
        txt2.setPrefWidth(1000);
        txt2.setPrefHeight(150);
        txt2.setFont(new Font(20));

        VBox vb4 = new VBox(5, lb4, txt2);

        // -----------------------------------------------------------------------

        Label lb5 = new Label("Which features do you use most frequently?");
        lb5.setFont(new Font(20));
        y1 = new CheckBox("Shopping");
        y1.setFont(new Font(18));
        y2 = new CheckBox("Weather Forecast");
        y2.setFont(new Font(18));
        y3 = new CheckBox("Fertilizers");
        y3.setFont(new Font(18));
        y4 = new CheckBox("Other");
        y4.setFont(new Font(18));

        HBox yb1 = new HBox(93, y1, y2);
        HBox yb2 = new HBox(93, y3, y4);

        VBox y = new VBox(3, yb1, yb2);
        y.setStyle("-fx-padding: 10");

        VBox vb5 = new VBox(5, lb5, y);

        // -----------------------------------------------------------------------

        Button btn = new Button("Submit Form");
        btn.setFont(new Font(25));
        btn.setStyle("-fx-background-color: lightgreen; -fx-border-color: green; -fx-border-radius: 5");

        // ---------------------------------------------------------------------------

        VBox vb1Box = new VBox(heading);
        VBox vb2Box = new VBox(30, lb, vb1, vb2, vb3, vb4, vb5, btn);
        vb2Box.setPrefWidth(1200);
        vb2Box.setPrefHeight(1000);
        vb2Box.setAlignment(Pos.CENTER);
        vb2Box.setStyle("-fx-padding: 0 50");

        VBox feedBackView = new VBox(30, vb1Box, vb2Box);
        feedBackView.setPrefWidth(1200);
        feedBackView.setPrefHeight(1000);
        feedBackView.setAlignment(Pos.CENTER);
        feedBackView.setStyle("-fx-padding: 40");

        db.setOnAction(e -> {
            // Alert alt = new Alert(AlertType.INFORMATION);
            // alt.setTitle("Form information");
            // alt.setContentText("Your survey has been downloaded");
            // alt.showAndWait();
            reset();
        });

        btn.setOnAction(e -> {
            FeedbackForm fd = addFeedbackForm();
            // System.out.println(fd.getDifficultFeature());
            boolean fdRes = farmerController.handleFeedBackForm(fd);
            // System.out.println(fdRes);
            if (fdRes) {
            
                alert("Form information", "Thank you!!\nSurvey successfully submitted");
                reset();

            } else {
                
                alert("Form information","Sorry!!\nSurvey Denied due to already submitted");
            }

        });

        HBox container = new HBox(feedBackView);
        container.setPrefWidth(1790);
        container.setAlignment(Pos.CENTER);

        return container;
    }

    private static void alert(String title,String content){
        Alert alt = new Alert(AlertType.INFORMATION);
        alt.setTitle("Form information");
        alt.setContentText("Sorry!!\nSurvey Denied due to already submitted");
        alt.showAndWait();
    }
    private static void reset() {
        txt.clear();
        txt2.clear();
        sel.setValue("--select--");
        incrPro.setSelected(false);
        redCos.setSelected(false);
        betPest.setSelected(false);
        impDes.setSelected(false);
        y1.setSelected(false);
        y2.setSelected(false);
        y3.setSelected(false);
        y4.setSelected(false);
    }

    private static FeedbackForm addFeedbackForm() {
        FeedbackForm fd = new FeedbackForm();
        fd.setAnyBugs(txt2.getText());
        fd.setUseOfApp(sel.getValue());
        fd.setIncresedProductivity(incrPro.isSelected());
        fd.setReducedCost(redCos.isSelected());
        fd.setDecisionMaking(impDes.isSelected());
        fd.setBetterPestDieaseManagement(betPest.isSelected());
        fd.setAnyBugs(txt.getText());
        fd.setDifficultFeature(txt2.getText());
        fd.setFeatureLikeFertilizer(y2.isSelected());
        fd.setFeatureLikeShoping(y1.isSelected());
        fd.setFeatureLikeWeatherForecasting(y2.isSelected());
        fd.setFeatureLikeOther(y4.isSelected());
        return fd;
    }

    public static VBox aboutUS(){

         Label lb1 = new Label("Thank you Sir");
        lb1.setStyle(" -fx-font-weight:900 ; -fx-text-fill: LightGreen; -fx-font-family:verdana; -fx-font-size :40 ");

        // Sir Img
        Image ig = new Image("Sir2.jpg");
        ImageView imView = new ImageView(ig);
        imView.setFitWidth(350);
        imView.setFitHeight(350);

        Text text = new Text("Welcome to our team! We are a group of passionate and dedicated professionals who thrive on creativity and innovation. With a strong commitment to excellence, we aim to deliver high-quality solutions that make a difference. Our team comprises skilled developers who are not only proficient in their craft but also genuinely enjoy what they do. Together, we collaborate, innovate, and strive to exceed expectations in every project we undertake. Thank you for visiting our page and getting to know us better. We look forward to the opportunity to work with you and create something remarkable.\n");
        text.setFont(new Font("Verdana",20));
        text.setWrappingWidth(1000);
        text.setFill(Color.LIGHTGREEN);
        text.setTextAlignment(TextAlignment.JUSTIFY);
      

        // Title
        Label Head1 = new Label("We're a team of people who love what they do.");
        //Head1.setFont(new Font("IMPACT", 40));
        Head1.setStyle(" -fx-font-weight:900 ; -fx-text-fill: LightGreen; -fx-font-size :40");
        Head1.setAlignment(Pos.CENTER);

        // Team Title
        Label teamHeader = new Label("MEET OUR TEAM");
        teamHeader.setStyle("-fx-font-size: 40px; -fx-text-fill: LightGreen; -fx-font-weight: Bold");

        // Team Members
        VBox vimg1 = createTeamMember("Vicky", "Developer", "VickyBack.jpg");
        VBox vimg2 = createTeamMember("Soham", "Developer", "SohamBack.jpg");
        VBox vimg3 = createTeamMember("Piyush", "Developer", "PiyushBack.jpg");
        VBox vimg4 = createTeamMember("Apurv", "Developer", "rupali.jpg");
        //VBox vimg4 = createTeamMember("Apurv", "Developer", "Apurva3.jpg");

        HBox ImageHbox = new HBox(50, vimg1, vimg2, vimg3,vimg4);
        ImageHbox.setAlignment(Pos.CENTER);

        Label con = new Label("Concepts used in our projects");
        con.setStyle("-fx-font-size: 40px; -fx-text-fill: LightGreen; -fx-font-weight: Bold");

        Label con1 = new Label("Firestore : Firestore Database , Storage , Authentication");
        con1.setStyle(" -fx-font-weight:900 ; -fx-text-fill: LightGreen; -fx-font-size :25");
        Label con2 = new Label("JAVA : OOPS , Exception Handling");
        con2.setStyle(" -fx-font-weight:900 ; -fx-text-fill: LightGreen; -fx-font-size :25");

        VBox concepts = new VBox(20 , con , con1 , con2);
        concepts.setAlignment(Pos.CENTER);

        VBox FullScreen = new VBox(30, lb1, imView,text,concepts, Head1, teamHeader, ImageHbox);
        FullScreen.setPrefWidth(1800);
        FullScreen.setPrefHeight(950);
        FullScreen.setAlignment(Pos.CENTER);
        FullScreen.setStyle("-fx-background: #17201B");
        // // Add ScrollPane to FullScreen
        // ScrollPane fullScreenScrollPane = new ScrollPane(FullScreen);
        // fullScreenScrollPane.setFitToWidth(true);

        // HBox main = new HBox(fullScreenScrollPane);
        // main.setPrefWidth(1800);
        // main.setPrefHeight(850);
        // main.setAlignment(Pos.CENTER);

        // // Add another ScrollPane to cover the entire stage
        // ScrollPane stageScrollPane = new ScrollPane(main);
        // stageScrollPane.setFitToWidth(true);
        // stageScrollPane.setFitToHeight(true);

        StackPane background = new StackPane(FullScreen);
        background.setStyle("-fx-background: #17201B");

        addFadeInAnimation(Head1);
        addFadeInAnimation(teamHeader);
        addSlideInAnimation(vimg1);
        addSlideInAnimation(vimg2);
        addSlideInAnimation(vimg3);
        addSlideInAnimation(vimg4);
        addSlideInAnimation(imView);

        VBox vbox = new VBox(background);
        vbox.setStyle("-fx-background: #17201B");

        return vbox;
    }

    private static VBox createTeamMember(String name, String role, String imagePath) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(350);
        imageView.setFitHeight(300);

        Label nameLabel = new Label(name);
        nameLabel.setFont(new Font("vardana", 30));
        nameLabel.setStyle("-fx-text-fill: LightGreen; -fx-font-size:30px; -fx-font-weight:Bold");

        Label roleLabel = new Label(role);
        roleLabel.setFont(new Font("vardana", 20));

        VBox vbox = new VBox(15, imageView, nameLabel, roleLabel);
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }

    private static void addFadeInAnimation(Label label) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), label);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private static void addSlideInAnimation(VBox vbox) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), vbox);
        translateTransition.setFromX(-500);
        translateTransition.setToX(0);
        translateTransition.play();

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), vbox);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private static void addSlideInAnimation(ImageView imageView) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), imageView);
        translateTransition.setFromX(-500);
        translateTransition.setToX(0);
        translateTransition.play();

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), imageView);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public ScrollPane BothPage() {
        VBox lower = new VBox(showFeedbackForm()); // Convert HBox to VBox for consistency
        VBox upper = aboutUS();
    
        VBox main = new VBox(150, upper, lower);
        main.setAlignment(Pos.CENTER); 
        main.setStyle("-fx-background: #17201B");
        
        ScrollPane sp = new ScrollPane(main);
        sp.setStyle("-fx-background: #17201B");
    
        return sp;
    }
    

}
