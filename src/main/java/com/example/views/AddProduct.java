package com.example.views;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.example.authentication.FirebaseAuthentication;
import com.example.controller.FarmerController;
import com.example.model.Product;
import com.example.util.ImagePicker;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AddProduct {
    // public static String imageLink;
    private static String uid = FirebaseAuthentication.getUserUid();
    private static TextField productName;
    private static TextArea productDescription;
    private static TextField quantityField;
    private static TextField priceField;
    private static TextField imagePathField;
    private static RadioButton equipmentRadio;
    private static RadioButton fertilizersRadio;
    private static RadioButton seedsRadio;
    private static RadioButton pesticidesRadio;
    private static RadioButton vegetablesRadio;
    private static File selectedFile;
    private static ImagePicker imgp;
    private static VBox prod_cart;
    public static List<Product> products ;
    public static VBox vb ;
    public static VBox prod_cart(){
        return vb;
    }


    public static HBox showAddProduct(Stage stage) {
        imgp = new ImagePicker(stage);
    
        // Add a heading for the product form
        Label headingLabel = new Label("Add Your Products");
        headingLabel.setFont(new Font("impact", 45));
        headingLabel.setStyle("");
        headingLabel.setAlignment(Pos.TOP_CENTER);
    
        HBox headingBox = new HBox(headingLabel);
        headingBox.setAlignment(Pos.CENTER);
    
        // Create a VBox for the product photo
        VBox photoBox = new VBox();
        photoBox.setPrefSize(220, 220);
        photoBox.setAlignment(Pos.CENTER);
        photoBox.setStyle("-fx-background-color:white; -fx-border-color: black; -fx-border-width: 2;");

        HBox outer = new HBox(photoBox);
        outer.setAlignment(Pos.CENTER);
    
        // Create a text field for the product name
        Label nameLabel = new Label("Product Name:");
        nameLabel.setFont(new Font("Verdana", 20));
    
        productName = new TextField();
        productName.setPromptText("Product Name");
        productName.setPrefHeight(30);
        productName.setPrefWidth(600);
        productName.setAlignment(Pos.CENTER_LEFT);
        productName.setFont(new Font("Verdana", 20));
        productName.setStyle(" -fx-focus-color: transparent;-fx-faint-focus-color: transparent; -fx-background-color:transparent; -fx-border-color:black; -fx-border-width:0 0 3 0;");
    
        HBox proName = new HBox(30, nameLabel, productName);
    
        // Create a text area for the product description
        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setFont(new Font("Verdana", 20));
    
        productDescription = new TextArea();
        productDescription.setPromptText("Description");
        productDescription.setPrefRowCount(2);
        productDescription.setPrefHeight(100);
        productDescription.setPrefWidth(600);
        productDescription.setWrapText(true);
        productDescription.setFont(new Font("Verdana", 20));
        productDescription.setStyle(" -fx-focus-color: transparent;-fx-faint-focus-color: transparent; -fx-background-color:transparent; -fx-border-color:black; -fx-border-width:0 0 3 0;");
    
        HBox descBox = new HBox(60, descriptionLabel, productDescription);
        descBox.setAlignment(Pos.CENTER);
    
        // Create a text field for the quantity
        Label quantityLabel = new Label("Quantity:");
        quantityLabel.setFont(new Font("Verdana", 20));
    
        quantityField = new TextField("0");
        quantityField.setPrefWidth(200);
        quantityField.setEditable(true);
        quantityField.setAlignment(Pos.CENTER);
        quantityField.setFont(new Font("Verdana", 20));
        quantityField.setStyle(" -fx-focus-color: transparent;-fx-faint-focus-color: transparent; -fx-background-color:transparent; -fx-border-color:black; -fx-border-width:0 0 3 0;");

    
        // Create a button to select the image file
        Button selectImageBtn = new Button("Select Image");
        selectImageBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        selectImageBtn.setFont(new Font("Verdana", 16));
    
        // Create a text field to display selected image path
        imagePathField = new TextField();
        imagePathField.setPromptText("Selected Image Path");
        imagePathField.setPrefWidth(600);
        imagePathField.setEditable(false);
        imagePathField.setFont(new Font("Verdana", 20));
        imagePathField.setStyle(" -fx-focus-color: transparent;-fx-faint-focus-color: transparent; -fx-background-color:transparent; -fx-border-color:black; -fx-border-width:0 0 3 0;");
    
        HBox imgBox = new HBox(55, selectImageBtn, imagePathField);
        imgBox.setAlignment(Pos.CENTER);
    
        selectImageBtn.setOnAction(e -> {
            selectedFile = imgp.getImage(uid);
            if (selectedFile != null) {
                imagePathField.setText(selectedFile.getAbsolutePath());
                ImageView imageView = new ImageView(new Image("file:" + selectedFile.getAbsolutePath()));
                imageView.setFitWidth(200);
                imageView.setFitHeight(200);
                imageView.setStyle("-fx-border-color: black; -fx-border-width: 2;");
                photoBox.getChildren().clear();
                photoBox.getChildren().add(imageView);
            }
        });
    
        // Create a button to upload the selected image
        Button uploadImageBtn = new Button("Upload Product");
        uploadImageBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size:25;");
        uploadImageBtn.setFont(new Font("Verdana", 16));
    
        uploadImageBtn.setOnAction(e -> {
            if (selectedFile != null) {
                try {
                    imgp.uploadImage(selectedFile, uid, productName.getText());
                    String productId = FarmerController.handleUploadProduct();
                    getProductsAdded(addedProduct(), productId);
                    resetFields();
                    photoBox.getChildren().clear();
                } catch (IOException exce) {
                    exce.printStackTrace();
                    showAlert("Upload Error", "Failed to upload the image. Please try again.");
                }
            } else {
                showAlert("No file selected", "Please select a file before uploading.");
            }
        });
    
        // Create buttons for increasing and decreasing the quantity
        Button plusButton = new Button("+");
        plusButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        plusButton.setFont(new Font("Verdana", 16));
    
        Button minusButton = new Button("-");
        minusButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        minusButton.setFont(new Font("Verdana", 16));
    
        // Set actions for the buttons
        plusButton.setOnAction(e -> {
            int currentQuantity = Integer.parseInt(quantityField.getText());
            quantityField.setText(String.valueOf(currentQuantity + 1));
        });
    
        minusButton.setOnAction(e -> {
            int currentQuantity = Integer.parseInt(quantityField.getText());
            if (currentQuantity > 0) {
                quantityField.setText(String.valueOf(currentQuantity - 1));
            }
        });
    
        // Create a HBox to contain the quantity field and buttons
        HBox quantityBox = new HBox(10);
        quantityBox.setAlignment(Pos.CENTER);
        quantityBox.getChildren().addAll(minusButton, quantityField, plusButton);


        HBox qBox = new HBox(85,quantityLabel,quantityBox);
        //qBox.setAlignment(Pos.CENTER);
    
        // Create radio buttons for product type
        ToggleGroup productTypeGroup = new ToggleGroup();
    
        equipmentRadio = new RadioButton("Equipment");
        equipmentRadio.setToggleGroup(productTypeGroup);
        equipmentRadio.setFont(new Font("Verdana", 16));
        equipmentRadio.setStyle("-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");
    
        fertilizersRadio = new RadioButton("Grains");
        fertilizersRadio.setToggleGroup(productTypeGroup);
        fertilizersRadio.setFont(new Font("Verdana", 16));
        fertilizersRadio.setStyle("-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");

    
        seedsRadio = new RadioButton("Cotton");
        seedsRadio.setToggleGroup(productTypeGroup);
        seedsRadio.setFont(new Font("Verdana", 16));
        seedsRadio.setStyle("-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");

    
        pesticidesRadio = new RadioButton("Sugarcane");
        pesticidesRadio.setToggleGroup(productTypeGroup);
        pesticidesRadio.setFont(new Font("Verdana", 16));
        pesticidesRadio.setStyle("-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");

    
        vegetablesRadio = new RadioButton("Vegetables");
        vegetablesRadio.setToggleGroup(productTypeGroup);
        vegetablesRadio.setFont(new Font("Verdana", 16));
        vegetablesRadio.setStyle("-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");

    
        // Create an HBox to contain the radio buttons
        HBox productTypeBox = new HBox(10);
        productTypeBox.setAlignment(Pos.CENTER);
        productTypeBox.getChildren().addAll(equipmentRadio, fertilizersRadio, seedsRadio, pesticidesRadio, vegetablesRadio);
    
        // Create labels for the text fields
        Label priceLabel = new Label("Price-Kg/Piece:");
        priceLabel.setFont(new Font("Verdana", 20));
    
        // Create a text field for the price
        priceField = new TextField();
        priceField.setPromptText("Price");
        priceField.setPrefWidth(600);
        priceField.setAlignment(Pos.CENTER_LEFT);
        priceField.setFont(new Font("Verdana", 20));
        priceField.setStyle(" -fx-focus-color: transparent;-fx-faint-focus-color: transparent; -fx-background-color:transparent; -fx-border-color:black; -fx-border-width:0 0 3 0;");
    
        HBox priceBox = new HBox(30, priceLabel, priceField);
        priceBox.setAlignment(Pos.CENTER);
    
        // Product type
        Label ptype = new Label("Product Type:");
        ptype.setFont(new Font("Verdana", 20));
    
        HBox pTyBox = new HBox(40, ptype, productTypeBox);
        //pTyBox.setAlignment(Pos.CENTER);
    
        // Create a VBox layout and add the nodes
        VBox formBox = new VBox(20);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(3,0,0,20));
        formBox.getChildren().addAll(
            headingBox,
            outer,
            proName,
            descBox,
            qBox,
            pTyBox,
            priceBox,
            imgBox, uploadImageBtn
        );
        formBox.setStyle("-fx-border-width:0 0 0 8; -fx-border-color:black");

    
        // Create a heading for the product cart
        Label head_OurProduct = new Label("Your Products");
        head_OurProduct.setStyle("-fx-font-size: 45; -fx-font-family: impact; ");
    
        HBox head = new HBox(head_OurProduct);
        head.setAlignment(Pos.CENTER);
        head.setPrefWidth(860);
    
        // Create a VBox for the product cart and style it
        prod_cart = new VBox(20);
        prod_cart.setStyle("-fx-padding: 0 0 0 15");

        VBox vb = new VBox(20, head, prod_cart);
        vb.setPrefWidth(860);
        vb.setAlignment(Pos.CENTER);
    
    
        // Create a ScrollPane for the product cart
        ScrollPane scrollPane = new ScrollPane(vb);
        scrollPane.setPrefWidth(900);
        scrollPane.setStyle("-fx-background-color: transparent;");
    
        // Create a HBox for the combined layout
        HBox combinedBox = new HBox(10);
        combinedBox.setAlignment(Pos.CENTER);
        combinedBox.setPadding(new Insets(20));
        combinedBox.getChildren().addAll(scrollPane, formBox);

    
        getAllPrBox();
    
        return combinedBox;
    }
    
    
    
    
    

 ////////////needs to be edited

    public static void getProductsAdded(Product product,String id) {
        HBox container = new HBox();
        HBox hb = new HBox(20);
        hb.setPrefWidth(860);
        hb.setStyle("-fx-border-width: 5; -fx-border-color: black;");
        hb.setAlignment(Pos.CENTER);

        VBox vb = new VBox(10);
        vb.setAlignment(Pos.CENTER);
        ImageView img = null;

        try {
            img = new ImageView(new Image("file:" + product.getProductUrl()));
        } catch (Exception d) {
            System.out.println("image not present");
        }

        img.setFitHeight(200);
        img.setFitWidth(200);

        Button dlt = new Button("Delete");
        dlt.setStyle("-fx-background-color:black; -fx-font-family:verdana; -fx-text-fill:white; -fx-font-size:25; -fx-background-radius:50; ");
      
        Label prod_title = new Label("product title :  ");
        prod_title.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        Label prod_title_ = new Label(product.getProductTitle());
        prod_title_.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        HBox hb1 = new HBox(30, prod_title, prod_title_);
        
        Label prod_type = new Label("product type :");
        prod_type.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        Label prod_type_ = new Label(product.getProductType());
        prod_type_.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        HBox hb2 = new HBox(30, prod_type, prod_type_);

        Label prod_price = new Label("product price :");
        prod_price.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        Label prod_price_ = new Label("" + product.getProductPrice());
        prod_price_.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        HBox hb3 = new HBox(30, prod_price, prod_price_);

        Label prod_quant = new Label("product Quantity :");
        prod_quant.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        Label prod_quant_ = new Label("" + product.getProductQuantity());
        prod_quant_.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        HBox hb4 = new HBox(30, prod_quant, prod_quant_);

        dlt.setOnAction(e->{
            System.out.println("i am dlted");
            prod_cart.getChildren().remove(container);
            // System.out.println(id);
            FarmerController.handleDltProduct(product,id);////////////
        });

        vb.getChildren().addAll(hb1, hb2, hb3,hb4);
        vb.setPrefWidth(350);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(5);

        // if (img != null) {
        //     hb.getChildren().add(img);
        // }
        // hb.getChildren().addAll(vb);
        // hb.setAlignment(Pos.CENTER);


          // Apply hover effect
          hb.setOnMouseEntered(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), hb);
            scaleTransition.setToX(1.03);
            scaleTransition.setToY(1.03);
            scaleTransition.play();
        });
        hb.setOnMouseExited(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), hb);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });

        HBox imgContainer= new HBox(img);
        imgContainer.setPrefWidth(300);
        imgContainer.setAlignment(Pos.CENTER);
        
        hb.getChildren().add(imgContainer);
        hb.getChildren().addAll(vb,dlt);
        hb.setAlignment(Pos.CENTER);

        container.getChildren().add(hb);
        prod_cart.getChildren().add(container);
    }







    public static Product getAddProduct() {
        return addedProduct();
    }

    public static Product addedProduct() {

        Product product = new Product();
        product.setProductTitle(productName.getText());
        product.setProductDescription(productDescription.getText());
        product.setProductImgUrl(ImagePicker.getImgPath());
        product.setProductQuantity(Integer.parseInt(quantityField.getText()));
        product.setProductType(getSelectedProductType());
        product.setProductPrice(Double.parseDouble(priceField.getText()));
        product.setProductUrl(imagePathField.getText());
        product.setProduct_img_Name(selectedFile.getName());

        product.setFarmerId(uid);
        return product;
    }

    private static String getSelectedProductType() {
        if (equipmentRadio.isSelected()) {
            return "Equipment";
        } else if (fertilizersRadio.isSelected()) {
            return "Grains";
        } else if (seedsRadio.isSelected()) {
            return "Cotton";
        } else if (pesticidesRadio.isSelected()) {
            return "SugarCane";
        } else if (vegetablesRadio.isSelected()) {
            return "Vegetables";
        } else {
            return null; // Handle case where no radio button is selected
        }
    }

    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void resetFields() {
        productName.clear();
        productDescription.clear();
        quantityField.setText("0");
        equipmentRadio.setSelected(false);
        fertilizersRadio.setSelected(false);
        seedsRadio.setSelected(false);
        pesticidesRadio.setSelected(false);
        vegetablesRadio.setSelected(false);
        priceField.setPromptText("Price");
        imagePathField.clear();
    }

    public static void getAllPrBox() {
        prod_cart.getChildren().clear();
        products  = FarmerView.getProducts();;
        for (int i = 0; i < products.size(); i++) {
            callfun(products.get(i));
        }
    }
    private static void callfun(Product p) {
        HBox contaniner = new HBox();
        HBox hb = new HBox(20);
        hb.setPrefWidth(860);
        hb.setStyle("-fx-border-width: 5; -fx-border-color: black;");
        hb.setAlignment(Pos.CENTER);

        VBox vb = new VBox(10);
        vb.setAlignment(Pos.CENTER);
        System.out.println(p.getProductImgUrl());
        ImageView imageView;
        try{
            System.out.println(p.getProductImgUrl());
            imageView = new ImageView(new Image(p.getProductImgUrl()));
          
        }catch(Exception e){
            imageView = new ImageView(new Image("farmease\\farmease\\src\\main\\resources\\farmForm\\back1.jpg"));
            System.out.println(e.getMessage());
        }
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);

        Label prod_title = new Label("product title :  ");
        prod_title.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        Label prod_title_ = new Label(p.getProductTitle());
        prod_title_.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        HBox hb1 = new HBox(30, prod_title, prod_title_);
        
        Label prod_type = new Label("product type :");
        prod_type.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        Label prod_type_ = new Label(p.getProductType());
        prod_type_.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        HBox hb2 = new HBox(30, prod_type, prod_type_);

        Label prod_price = new Label("product price :");
        prod_price.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        Label prod_price_ = new Label("" + p.getProductPrice());
        prod_price_.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        HBox hb3 = new HBox(30, prod_price, prod_price_);

        Label prod_quant = new Label("product Quantity :");
        prod_quant.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        Label prod_quant_ = new Label("" + p.getProductQuantity());
        prod_quant_.setStyle("-fx-font-family:verdana; -fx-font-size:25; ");
        HBox hb4 = new HBox(30, prod_quant, prod_quant_);

        vb.getChildren().addAll(hb1, hb2, hb3,hb4);
        vb.setPrefWidth(350);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(5);
        
        Button dlt = new Button("Delete");
        dlt.setStyle("-fx-background-color:black; -fx-background-radius:50; -fx-font-family:verdana; -fx-text-fill:white; -fx-font-size:25;");


        dlt.setOnAction(e->{
            System.out.println("i am dlted");
            prod_cart.getChildren().remove(contaniner);
            FarmerController.handleDltProduct(p,null);
        });

        HBox imgContainer= new HBox(imageView);
        imgContainer.setPrefWidth(300);
        imgContainer.setAlignment(Pos.CENTER);


        hb.getChildren().add(imgContainer);
        hb.getChildren().addAll(vb,dlt);
        hb.setAlignment(Pos.CENTER);

       

        // Apply hover effect
        hb.setOnMouseEntered(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), hb);
            scaleTransition.setToX(1.03);
            scaleTransition.setToY(1.03);
            scaleTransition.play();
        });
        hb.setOnMouseExited(e -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), hb);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });

        contaniner.getChildren().add(hb);
        prod_cart.getChildren().add(contaniner);

        // Adding separation between each product box
        //VBox.setMargin(hb, new Insets(10,5,10,5));
    }
}
