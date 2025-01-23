package com.example.views;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Product;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
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

public class ProductView {

    public static ScrollPane showBuyProducts() {
        Label opt1 = createNavbarOption("Fertilizers");
        Label opt2 = createNavbarOption("Crop Seeds");
        Label opt3 = createNavbarOption("Vegetable Seeds");
        Label opt4 = createNavbarOption("Pesticides");
        Label opt5 = createNavbarOption("Farming Equipments");

        HBox navbar = new HBox(100, opt1, opt2, opt3, opt4, opt5);
        navbar.setPrefSize(1800, 80);
        navbar.setAlignment(Pos.CENTER);
        navbar.setStyle("-fx-background-color:rgb(79, 111, 82); -fx-opacity:1");

        // fertilizers
        String product1 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\card1.png,Jobes Organic,Ugaoo Organic Vermicompost Fertilizer Manure For Plants - 5 Kg,Rs. 500";
        String product2 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\produst2.jpg,Seed Starter,Dyna-Gro Grow 32 oz. Concentrated Liquid Plant Food,Rs. 1000";
        String product3 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\product3.png,Plant Treat,Schultz SPF45160 All Purpose Liquid Plant Food 10-15-10 4 oz,Rs. 600";
        String product4 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\product4.jpg,All Letuce Mix,Fox Farm FX14091 Big Bloom Liquid Concentrate Fertilizer 1-Pint White,Rs. 200";
        String product5 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\product.jpg,Vermicompost,The Andersons Professional PGF Complete 16-4-8 Fertilizer with Humic (40lb) ,Rs. 300";
        String product6 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\product6.png,Smart Grower Mycorrhizal Fungi Tablets with Beneficial Bacteria,Natural Smart Grower Supercharge Plant Power Pills ,Rs. 900";

        List<String> fertilizers = new ArrayList<>();
        fertilizers.add(product1);
        fertilizers.add(product2);
        fertilizers.add(product3);
        fertilizers.add(product4);
        fertilizers.add(product5);
        fertilizers.add(product6);
        VBox fertilizers_vb = Shopinglist(fertilizers);
        fertilizers_vb.setAlignment(Pos.CENTER);
        fertilizers_vb.setStyle("-fx-padding:30 0 0 15");

        // cropseeds
        String product7 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\crop1.jpg,Jobes Organic,Ugaoo Organic Vermicompost Fertilizer Manure For Plants - 5 Kg,Rs. 500";
        String product8 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\crop2.jpg,Seed Starter,Dyna-Gro Grow 32 oz. Concentrated Liquid Plant Food,Rs. 500";
        String product9 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\crop3.jpg,Plant Treat,Schultz SPF45160 All Purpose Liquid Plant Food 10-15-10 4 oz,Rs. 500";
        String product10 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\crop4.jpg,All Letuce Mix,Fox Farm FX14091 Big Bloom Liquid Concentrate Fertilizer 1-Pint White,Rs. 500";
        String product11 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\crop5.jpg,Vermicompost,The Andersons Professional PGF Complete 16-4-8 Fertilizer with Humic (40lb) ,Rs. 500";
        String product12 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\crop6.jpg,Smart Grower,Mycorrhizal Fungi Tablets with Beneficial Bacteria,Natural Smart Grower Supercharge Plant Power Pills - 20 Tablets for 20 Plants,Rs. 500";

        List<String> cropseeds = new ArrayList<>();
        cropseeds.add(product11);
        cropseeds.add(product7);
        cropseeds.add(product8);
        cropseeds.add(product9);
        cropseeds.add(product10);
        cropseeds.add(product12);
        VBox cropseeds_vb = Shopinglist(cropseeds);
        cropseeds_vb.setAlignment(Pos.CENTER);
        cropseeds_vb.setStyle("-fx-padding:30 0 0 15");

        // vegetables

        String product13 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\veg1.jpg,Jobes Organic,Ugaoo Organic Vermicompost Fertilizer Manure For Plants - 5 Kg,Rs. 500";
        String product14 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\veg2.jpg,Seed Starter,Dyna-Gro Grow 32 oz. Concentrated Liquid Plant Food,Rs. 500";
        String product15 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\veg3.jpg,Plant Treat,Schultz SPF45160 All Purpose Liquid Plant Food 10-15-10 4 oz,Rs. 500";
        String product16 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\veg4.jpg,All Letuce Mix,Fox Farm FX14091 Big Bloom Liquid Concentrate Fertilizer 1-Pint White,Rs. 500";
        String product17 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\veg5.jpg,Vermicompost,The Andersons Professional PGF Complete 16-4-8 Fertilizer with Humic (40lb) ,Rs. 500";
        String product18 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\veg1.jpg,Smart Grower,Mycorrhizal Fungi Tablets with Beneficial Bacteria,Natural Smart Grower Supercharge Plant Power Pills - 20 Tablets for 20 Plants,Rs. 500";

        List<String> vegetables = new ArrayList<>();
        vegetables.add(product13);
        vegetables.add(product14);
        vegetables.add(product15);
        vegetables.add(product16);
        vegetables.add(product17);
        vegetables.add(product18);
        VBox vegetables_vb = Shopinglist(vegetables);
        vegetables_vb.setAlignment(Pos.CENTER);
        vegetables_vb.setStyle("-fx-padding:30 0 0 15");

        // pestisides
        String product19 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\pest1.jpg,Jobes Organic,Ugaoo Organic Vermicompost Fertilizer Manure For Plants - 5 Kg,Rs. 500";
        String product20 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\pest2.jpg,Seed Starter,Dyna-Gro Grow 32 oz. Concentrated Liquid Plant Food,Rs. 500";
        String product21 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\pest3.jpg,Plant Treat,Schultz SPF45160 All Purpose Liquid Plant Food 10-15-10 4 oz,Rs. 500";
        String product22 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\pest3.jpg,All Letuce Mix,Fox Farm FX14091 Big Bloom Liquid Concentrate Fertilizer 1-Pint White,Rs. 500";
        String product23 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\pest5.jpg,Vermicompost,The Andersons Professional PGF Complete 16-4-8 Fertilizer with Humic (40lb) ,Rs. 500";
        String product24 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\pest6.jpg,Smart Grower,Mycorrhizal Fungi Tablets with Beneficial Bacteria,Natural Smart Grower Supercharge Plant Power Pills - 20 Tablets for 20 Plants,Rs. 500";

        List<String> pestisides = new ArrayList<>();
        pestisides.add(product19);
        pestisides.add(product20);
        pestisides.add(product21);
        pestisides.add(product22);
        pestisides.add(product23);
        pestisides.add(product24);
        VBox pestisides_vb = Shopinglist(pestisides);
        pestisides_vb.setAlignment(Pos.CENTER);
        pestisides_vb.setStyle("-fx-padding:30 0 0 15");

        // farming equipment
        String product25 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\pro1.jpg,Jobes Organic,Ugaoo Organic Vermicompost Fertilizer Manure For Plants - 5 Kg,Rs. 500";
        String product26 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\pro2.png,Seed Starter,Dyna-Gro Grow 32 oz. Concentrated Liquid Plant Food,Rs. 500";
        String product27 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\pro3.jpg,Plant Treat,Schultz SPF45160 All Purpose Liquid Plant Food 10-15-10, 4 oz,Rs. 500";
        String product28 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\pro4.jpg,All Letuce Mix,Fox Farm FX14091 Big Bloom Liquid Concentrate Fertilizer 1-Pint White,Rs. 500";
        String product29 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\pro5.jpg,Vermicompost,The Andersons Professional PGF Complete 16-4-8 Fertilizer with Humic (40lb) ,Rs. 500";
        String product30 = "file:farmease\\farmease\\src\\main\\resources\\assets\\images\\pro6.jpg,Smart Grower,Mycorrhizal Fungi Tablets with Beneficial Bacteria,Natural Smart Grower Supercharge Plant Power Pills - 20 Tablets for 20 Plants,Rs. 500";

        List<String> farmeringequipment = new ArrayList<>();
        farmeringequipment.add(product25);
        farmeringequipment.add(product26);
        farmeringequipment.add(product27);
        farmeringequipment.add(product28);
        farmeringequipment.add(product29);
        farmeringequipment.add(product30);
        VBox farmeringequipment_vb = Shopinglist(farmeringequipment);
        farmeringequipment_vb.setAlignment(Pos.CENTER);
        farmeringequipment_vb.setStyle("-fx-padding:30 0 0 15");

        ////////////////////////////////////////////////////////
        VBox main = new VBox(50, navbar, fertilizers_vb);

        opt1.setOnMouseClicked(e -> {
            main.getChildren().clear();
            main.getChildren().addAll(navbar,fertilizers_vb);
        });

        opt2.setOnMouseClicked(e -> {
            main.getChildren().clear();
            main.getChildren().addAll(navbar,cropseeds_vb);
        });

        opt3.setOnMouseClicked(e -> {
            main.getChildren().clear();
            main.getChildren().addAll(navbar,vegetables_vb);
        });

        opt4.setOnMouseClicked(e -> {
            main.getChildren().clear();
            main.getChildren().addAll(navbar,pestisides_vb);
        });

        opt5.setOnMouseClicked(e -> {
            main.getChildren().clear();
            main.getChildren().addAll(navbar,farmeringequipment_vb);
        });

        ScrollPane gp = new ScrollPane(main);
        return gp;
    }

    static HBox createQuantityControl(Label quantityValue) {
        Label quantityLabel = new Label("Quantity:");
        quantityLabel.setFont(new Font("verdana", 20));

        quantityValue.setText("1");

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
        });

        Button decrementButton = new Button("-");
        decrementButton.setFont(new Font("verdana", 20));
        decrementButton.setStyle(
                "-fx-background-color: rgb(79, 111, 82); -fx-text-fill: white; -fx-background-radius: 10; -fx-padding: 5 15;");
        decrementButton.setOnAction(e -> {
            int currentValue = Integer.parseInt(quantityValue.getText());
            if (currentValue > 1) {
                quantityValue.setText(String.valueOf(currentValue - 1));
            }
        });

        HBox quantityControl = new HBox(10, decrementButton, quantityValue, incrementButton);
        quantityControl.setAlignment(Pos.CENTER);

        HBox quantityBox = new HBox(10, quantityLabel, quantityControl);
        quantityBox.setAlignment(Pos.CENTER);
        return quantityBox;
    }

    private static VBox Shopinglist(List<String> product) {
        VBox card1 = createProductCard(product.get(0).split(",")[0], product.get(0).split(",")[1],
                product.get(0).split(",")[2], product.get(0).split(",")[3]);

        VBox card2 = createProductCard(product.get(1).split(",")[0], product.get(1).split(",")[1],
                product.get(1).split(",")[2], product.get(1).split(",")[3]);

        VBox card3 = createProductCard(product.get(2).split(",")[0], product.get(2).split(",")[1],
                product.get(2).split(",")[2], product.get(2).split(",")[3]);

        VBox card4 = createProductCard(product.get(3).split(",")[0], product.get(3).split(",")[1],
                product.get(3).split(",")[2], product.get(3).split(",")[3]);

        VBox card5 = createProductCard(product.get(4).split(",")[0], product.get(4).split(",")[1],
                product.get(4).split(",")[2], product.get(4).split(",")[3]);

        VBox card6 = createProductCard(product.get(5).split(",")[0], product.get(5).split(",")[1],
                product.get(5).split(",")[2], product.get(5).split(",")[3]);
        // VBox card2 =
        // createProductCard("file:farmease\\farmease\\src\\main\\resources\\assets\\images\\produst2.jpg",
        // "Seed Starter", "Dyna-Gro Grow 32 oz. Concentrated Liquid Plant Food", "Rs.
        // 500");
        // VBox card3 =
        // createProductCard("file:farmease\\farmease\\src\\main\\resources\\assets\\images\\product3.png",
        // "Plant Treat", "Schultz SPF45160 All Purpose Liquid Plant Food 10-15-10, 4
        // oz", "Rs. 500");
        // VBox card4 =
        // createProductCard("file:farmease\\farmease\\src\\main\\resources\\assets\\images\\product4.jpg",
        // "All Letuce Mix", "Fox Farm FX14091 Big Bloom Liquid Concentrate Fertilizer,
        // 1-Pint, White", "Rs. 500");
        // VBox card5 =
        // createProductCard("file:farmease\\farmease\\src\\main\\resources\\assets\\images\\product.jpg",
        // "Vermicompost", "The Andersons Professional PGF Complete 16-4-8 Fertilizer
        // with Humic (40lb)",
        // "Rs. 500");
        // VBox card6 =
        // createProductCard("file:farmease\\farmease\\src\\main\\resources\\assets\\images\\product6.png",
        // "Smart Grower",
        // "Mycorrhizal Fungi Tablets with Beneficial Bacteria,Natural Smart Grower
        // Supercharge Plant Power Pills - 20 Tablets for 20 Plants",
        // "Rs. 500");

        HBox hb1 = new HBox(100, card1, card2, card3);
        hb1.setAlignment(Pos.CENTER);
        HBox hb2 = new HBox(100, card4, card5, card6);
        hb2.setAlignment(Pos.CENTER);
        VBox vb = new VBox(50, hb1, hb2);
        return vb;

    }

    static VBox createProductCard(String imagePath, String productName, String description, String price) {
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

        Label priceLabel = new Label(price);
        priceLabel.setFont(new Font("verdana", 35));

        Label quantityValue = new Label();
        HBox quantityBox = createQuantityControl(quantityValue);

        Button cartButton = new Button("Add To Cart");
        cartButton.setPrefWidth(300);
        cartButton.setStyle(
                "-fx-background-color:rgb(79, 111, 82); -fx-font-family:verdana; -fx-background-radius:25; -fx-text-fill:white; -fx-padding:10; -fx-font-size:25");
        cartButton.setOnAction(e -> {
            boolean ispresent = Cart.addProductToCart(imagePath, productName, description,
                    Double.parseDouble(price.split(" ")[1]),
                    quantityValue.getText());
            if (ispresent) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText("Successfully added to cart");
                alert.show();
            }
            Cart.updateTotal();
            System.out.println("demo check");
        });

        VBox card = new VBox(15, img, productLabel, desc, priceLabel, quantityBox, cartButton);
        card.setPrefWidth(350);
        card.setPrefHeight(550);
        card.setAlignment(Pos.TOP_CENTER);
        card.setStyle(
                "-fx-border-color:rgb(26, 77, 46); -fx-border-width:5; -fx-border-radius:25; -fx-background-color:rgb(182, 199, 170,0.3); -fx-background-radius:25;");
        addHoverEffect(card);
        return card;
    }

    static Label createNavbarOption(String text) {
        Label option = new Label(text);
        option.setStyle("-fx-font-size:30; -fx-text-fill:white;");
        addHoverEffect(option);
        return option;
    }

    static private void addHoverEffect(VBox card) {
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

    private static void addHoverEffect(Label option) {
        option.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), option);
            st.setToX(1.1);
            st.setToY(1.1);
            option.setStyle("-fx-font-size:30; -fx-text-fill:yellow;");
            st.play();
        });
        option.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), option);
            st.setToX(1.0);
            st.setToY(1.0);
            option.setStyle("-fx-font-size:30; -fx-text-fill:white;");
            st.play();
        });
    }

}
