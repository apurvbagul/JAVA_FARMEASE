package com.example.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Acl;
import com.example.views.AddProduct;

public class ImagePicker {
    private static final String bucketName = "farmease-c4b6d.appspot.com";
    private String imageLink;
    private String imgUrls;
    private final ImageView pickedImageView;
    private final Stage stage;
    private static String imgUrl;

    public ImagePicker(Stage stage) {
        this.stage = stage;
        this.pickedImageView = new ImageView();
    }


    public File getImage(String uid) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            imgUrl = selectedFile.getAbsolutePath();
        } else {
            System.out.println("No file selected!");
        }
        return selectedFile;
    }



    public static String getImgPath() {
        
        return imgUrl;
    }



    public void uploadImage(File file, String uid, String productName) throws IOException {
        if (file == null) {
            System.out.println("No file to upload!");
            return;
        }
        String fileName = file.getName();
        imgUrls = fileName;
        // System.out.println(fileName);
        Path filePath = Paths.get(file.getAbsolutePath());
        String blobName = uid + "/" + productName + "/" + fileName;

        // Initialize the Google Cloud Storage client
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream("C:\\Users\\APURV BAGUL\\OneDrive\\Desktop\\farmease\\farmease\\src\\main\\resources\\sohamFarmEase.json")))
                .build()
                .getService();

        // Create a BlobId and BlobInfo with content type set to image
        BlobId blobId = BlobId.of(bucketName, blobName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("image/jpeg")  // Set content type as image
                .build();

        // Upload the file to Google Cloud Storage
        storage.create(blobInfo, Files.readAllBytes(filePath));

        // Make the image publicly accessible
        storage.createAcl(blobId, Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

        // Get the public URL for the uploaded image
        imageLink = String.format("https://storage.googleapis.com/%s/%s", bucketName, blobName);
        System.out.println(imageLink);

        // Update imageLink in AddProduct class
        imgUrl = imageLink;

    }





    







    public void uploadProfileImage(File file, String uid, String productName) throws IOException {
        if (file == null) {
            System.out.println("No file to upload!");
            return;
        }
        String fileName = file.getName();
        imgUrls = fileName;
        // System.out.println(fileName);
        Path filePath = Paths.get(file.getAbsolutePath());
        String blobName = "ProFileImages/"+ uid + "/" + fileName;

        // Initialize the Google Cloud Storage client
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream("farmease/farmease/src/main/resources/farmease.json")))
                .build()
                .getService();

        // Create a BlobId and BlobInfo with content type set to image
        BlobId blobId = BlobId.of(bucketName, blobName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("image/jpeg")  // Set content type as image
                .build();

        // Upload the file to Google Cloud Storage
        storage.create(blobInfo, Files.readAllBytes(filePath));

        // Make the image publicly accessible
        storage.createAcl(blobId, Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

        // Get the public URL for the uploaded image
        imageLink = String.format("https://storage.googleapis.com/%s/%s", bucketName, blobName);
        System.out.println(imageLink);

        // Update imageLink in AddProduct class
        imgUrl = imageLink;

    }


    public static void deleteImage(String uid, String productName, String fileName) throws IOException {
        String blobName = uid + "/" + productName + "/" + fileName;

        // Initialize the Google Cloud Storage client
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream("farmease/farmease/src/main/resources/farmease.json")))
                .build()
                .getService();

        // Create a BlobId
        BlobId blobId = BlobId.of(bucketName, blobName);

        // Delete the Blob
        boolean deleted = storage.delete(blobId);
        if (deleted) {
            System.out.println("Image deleted successfully!");
        } else {
            System.out.println("No such image found!");
        }
    }



    public static void deleteProfileImage(String uid, String productName, String fileName) throws IOException {
        String blobName = uid + "/" + productName + "/" + fileName;

        // Initialize the Google Cloud Storage client
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream("farmease/farmease/src/main/resources/farmease.json")))
                .build()
                .getService();

        // Create a BlobId
        BlobId blobId = BlobId.of(bucketName, blobName);

        // Delete the Blob
        boolean deleted = storage.delete(blobId);
        if (deleted) {
            System.out.println("Image deleted successfully!");
        } else {
            System.out.println("No such image found!");
        }
    }



    public String getImgUrls() {
        return imgUrls;
    }
}
