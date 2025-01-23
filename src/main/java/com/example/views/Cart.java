package com.example.views;

import java.awt.Desktop;
import java.net.URI;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import java.net.URI;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Cart {

    public static HBox cartBox = new HBox(15);
    public static VBox main;
    public static double subtotal = 0;
    public static double shipping = 0.0;
    public static double total = 0;
    public static Text subtotalText;
    public static Text totalText;
    public static Text totalPriceText;
    public static Button checkOut;
    public static final String ACCOUNT_SID = "AC1f26c00de0309d4138b978631b22adce"; // Replace with your actual Account
                                                                                   // SID
    public static final String AUTH_TOKEN = "1c91135fdae3f810347704fbd9bb6538";
    public static final String TWILIO_WHATSAPP_NUMBER = "whatsapp:+14155238886";

    public static VBox cartBoxView() {
        cartBox.setAlignment(Pos.CENTER_LEFT); // Align products to the left
        cartBox.setStyle("-fx-padding: 30 15 0 15");

        VBox paymentBox = createPaymentBox(); // Create payment box separately

        HBox proBox = new HBox(50, cartBox); // Use HBox for layout
        proBox.setPrefWidth(1800);
        proBox.setAlignment(Pos.CENTER);

        ScrollPane proBoxScroll = new ScrollPane(proBox);
        // proBoxScroll.setPrefWidth(1800);
        proBoxScroll.setPrefHeight(810);
        proBoxScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        proBoxScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        HBox payBox = new HBox(paymentBox);
        // payBox.setPrefWidth(00);
        payBox.setAlignment(Pos.CENTER);

        VBox btn = new VBox(checkOut);
        btn.setAlignment(Pos.CENTER);

        HBox pay = new HBox(300, payBox, btn);
        pay.setPrefWidth(1800);
        pay.setAlignment(Pos.CENTER);
        main = new VBox(50, proBoxScroll, pay);
        return main;
    }

    private static VBox createPaymentBox() {
        VBox paymentBox = new VBox(10);
        paymentBox.setAlignment(Pos.CENTER_RIGHT);
        paymentBox.setPadding(new javafx.geometry.Insets(20));

        subtotalText = new Text("Subtotal: Rs. " + subtotal);
        subtotalText.setStyle("-fx-font-familt:verdana; -fx-font-size:30; ");

        Text shippingText = new Text("Shipping: Rs. " + shipping);
        shippingText.setStyle("-fx-font-familt:verdana; -fx-font-size:30; ");

        totalText = new Text("Total (tax incl.): Rs. " + total);
        totalText.setStyle("-fx-font-familt:verdana; -fx-font-size:30; ");

        checkOut = new Button("Check out");
        checkOut.setStyle(
                "-fx-background-color:rgb(79, 111, 82);  -fx-font-family:verdana; -fx-background-radius:25; -fx-text-fill:white; -fx-padding:10; -fx-font-size:25");
        checkOut.setOnAction(e -> {

            if (ProfilePageView.phoneField.getText().isEmpty() || ProfilePageView.phoneField.getText().equals("null") ){
                showAlert();
                return ;
          }
            if (cartBox.getChildren().size() >= 1) {
                String productsDetails = "";
                int i = 0;
                for (Node vb : cartBox.getChildren()) {
                    VBox productCard = (VBox) vb;
                    Label prLabel = (Label) productCard.getChildren().get(1);
                    Text prdesc = (Text) productCard.getChildren().get(2);
                    Label prprice = (Label) productCard.getChildren().get(3);
                    productsDetails += (++i) + ") Name : " + prLabel.getText() + "\nDescription : ";
                    productsDetails += prdesc.getText() + "\n Amount : ";
                    productsDetails += prprice.getText() + "\n\n";
                }
                productsDetails += "\nTOTAL AMOUNT  : " + total + " ₹";

                String message = "Order placed for \n\n" +
                "Name: " + ProfilePageView.firstNameField.getText() + "\n" +
                "Mobile no: "
                + (!ProfilePageView.phoneField.getText().isEmpty() ? ProfilePageView.phoneField.getText()
                        : "no mobile number exists")
                + "\n\n" +
                "Details:\n" + productsDetails;
                String phoneNumber = ProfilePageView.phoneField != null && !ProfilePageView.phoneField.getText().isEmpty() 
                      ? ProfilePageView.phoneField.getText() 
                      : " ";
                sendMessageToWhatsApp_client(phoneNumber,message);
                sendMessageToWhatsApp("+919209232056", message);


                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText("Order Sucessfully Placed!!!");
                alert.show();
                
                total = 0;
                shipping = 0;
                subtotal = 0;
                subtotalText.setText("Subtotal: Rs. " + "0.0");
                shippingText.setText("Shipping: Rs. " + "0.0");
                totalText.setText("Total (tax incl.): Rs. " + "0.0");
                cartBox.getChildren().clear();


               
                
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setHeaderText("No Product Present!!\nAdd Products in cart.");
                alert.show();
            }
        });
        paymentBox.getChildren().addAll(subtotalText, shippingText, totalText);
        return paymentBox;
    }

    private static void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Update your Phone number to get Notifiied");
        alert.show();
    }

    public static void clearCart() {
        if (cartBox != null) {
            cartBox.getChildren().clear();
        }
        subtotal = 0;
        updateTotal();
    }

    public static boolean addProductToCart(String imagePath, String productName, String description, double price,
            String quantity) {
        VBox existingProductCard = findProductInCart(productName);
        int quantityToAdd = Integer.parseInt(quantity); // Convert quantity to an integer

        if (existingProductCard != null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("Already in cart!");
            alert.show();

            updateTotal();
            return false;
        } else {
            VBox productCard = createProductCard(imagePath, productName, description, price,
                    String.valueOf(quantityToAdd));
            cartBox.getChildren().add(productCard);
            subtotal += price * quantityToAdd; // Update subtotal for the new product
            shipping = 20;
            updateTotal();
        }

        return true;
    }

    private static VBox findProductInCart(String productName) {
        for (javafx.scene.Node node : cartBox.getChildren()) {
            if (node instanceof VBox) {
                VBox card = (VBox) node;
                Label productLabel = (Label) card.getChildren().get(1); // Assuming the product name is the second child
                if (productLabel.getText().equals(productName)) {
                    return card;
                }
            }
        }
        return null;
    }

    static VBox createQuantityControl(Label quantityValue, double price, VBox card) {
        Label quantityLabel = new Label("Quantity:");
        quantityLabel.setFont(new Font("verdana", 20));

        quantityValue.setFont(new Font("verdana", 20));
        quantityValue.setStyle(
                "-fx-border-width: 0; -fx-border-color: black; -fx-padding: 2 10; -fx-min-width: 40; -fx-alignment: center; -fx-font-style:bold;");

        Button incrementButton = new Button("+");
        incrementButton.setFont(new Font("verdana", 20));
        incrementButton.setStyle(
                "-fx-background-color: rgb(79, 111, 82); -fx-text-fill: white; -fx-background-radius: 10; -fx-padding: 5 10;");
        incrementButton.setOnAction(e -> {
            int currentValue = Integer.parseInt(quantityValue.getText());
            quantityValue.setText(String.valueOf(currentValue + 1));
            subtotal += price;
            System.out.println("Incremented subtotal: " + subtotal);
            updateTotal();
            System.out.println("updated incrememt");
        });

        Button decrementButton = new Button("-");
        decrementButton.setFont(new Font("verdana", 20));
        decrementButton.setStyle(
                "-fx-background-color: rgb(79, 111, 82); -fx-text-fill: white; -fx-background-radius: 10; -fx-padding: 5 15;");
        decrementButton.setOnAction(e -> {
            int currentValue = Integer.parseInt(quantityValue.getText());
            if (currentValue > 1) {
                quantityValue.setText(String.valueOf(currentValue - 1));
                subtotal -= price;
                System.out.println("Decremented subtotal: " + subtotal);
                updateTotal();
                System.out.println("updated decrement");

            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setFont(new Font("verdana", 20));
        deleteButton.setStyle(
                "-fx-background-color: #ff4c4c; -fx-text-fill: white; -fx-background-radius: 10; -fx-padding: 5 15;");
        deleteButton.setOnAction(e -> {
            cartBox.getChildren().remove(card);
            int currentValue = Integer.parseInt(quantityValue.getText());
            subtotal -= price * currentValue;
            System.out.println("Deleted subtotal: " + subtotal);

            if (cartBox.getChildren().size() == 0) {
                shipping = 0;
            } else {
                shipping = 20;
            }
            updateTotal();
        });

        HBox quantityControl = new HBox(10, decrementButton, quantityValue, incrementButton);
        quantityControl.setAlignment(Pos.CENTER);

        HBox quantityBox = new HBox(10, quantityLabel, quantityControl);
        quantityBox.setAlignment(Pos.CENTER);

        VBox vb = new VBox(10, quantityBox, deleteButton);
        vb.setAlignment(Pos.CENTER);
        return vb;
    }

    static VBox createProductCard(String imagePath, String productName, String description, double price,
            String quantity) {
        Rectangle clip = new Rectangle(350, 250);
        clip.setArcWidth(28); // Set the arc width for rounded corners
        clip.setArcHeight(28);

        ImageView img = new ImageView(new Image(imagePath));
        img.setFitHeight(250);
        img.setFitWidth(350);
        img.setClip(clip);

        Label productLabel = new Label(productName);
        productLabel.setFont(new Font("verdana", 30));

        Text desc = new Text(description);
        desc.setWrappingWidth(350);
        desc.setFont(new Font("verdana", 15));
        desc.setTextAlignment(TextAlignment.CENTER);

        Label priceLabel = new Label("Rs. " + price);
        priceLabel.setFont(new Font("verdana", 35));

        Label quantityValue = new Label(quantity);

        VBox card = new VBox(15, img, productLabel, desc, priceLabel);
        VBox quantityBox = createQuantityControl(quantityValue, price, card);

        card.getChildren().add(quantityBox);
        card.setPrefWidth(350);
        card.setPrefHeight(550);
        card.setAlignment(Pos.TOP_CENTER);
        card.setStyle(
                "-fx-border-color:rgb(26, 77, 46); -fx-border-width:5; -fx-border-radius:25; -fx-background-color:rgb(182, 199, 170,0.3); -fx-background-radius:25;");
        // addHoverEffect(card);
        return card;
    }

    private static void addHoverEffect(VBox card) {
        card.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        });
        card.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void sendMessageToWhatsApp_client(String phoneNumber, String message) {
      
        
        try {
            // Encode the message
            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.toString());
    
            // Construct the URL
            String url = "https://wa.me/+91" + phoneNumber + "?text=" + encodedMessage;
    
            // Open the URL in the default browser
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                System.out.println("Desktop browsing not supported.");
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Log the exception for debugging
        }
    }
    
    public static void sendMessageToWhatsApp(String phoneNumber, String message) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(
                new PhoneNumber("whatsapp:" + phoneNumber),
                new PhoneNumber(TWILIO_WHATSAPP_NUMBER),
                message)
                .create();

        System.out.println("Message sent successfully to " + phoneNumber);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void updateTotal() {
        total = subtotal + shipping;
        System.out.println("Updating total: total=" + total + ", subtotal=" +
                subtotal + ", shipping=" + shipping);

        if (subtotalText != null && totalText != null) {
            subtotalText.setText("Subtotal: Rs. " + subtotal);
            totalText.setText("Total (tax incl.): Rs. " + total);
            // totalPriceText.setText("Rs. " + total);
        }
    }
}
