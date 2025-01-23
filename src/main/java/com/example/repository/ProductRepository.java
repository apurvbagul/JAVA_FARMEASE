package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.authentication.FirebaseAuthentication;
import com.example.model.Product;
import com.example.util.FireBaseUtil;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

public class ProductRepository {
    private Firestore db;

    public ProductRepository() {
        this.db = FireBaseUtil.getInstance().getDatabase();
    }

    // Method to create a product for a farmer
    public String createProductForFarmer(String farmerUid, Product product)
            throws ExecutionException, InterruptedException {
                DocumentReference farmerRef = db.collection("productFarmers").document(farmerUid);
                CollectionReference productCollection = farmerRef.collection("products");
            
                // Add the product document without the ID
                ApiFuture<DocumentReference> future = productCollection.add(product);
                DocumentReference productDocRef = future.get();
            
                // Retrieve the generated document ID
                String productId = productDocRef.getId();
                System.out.println("Product document created with ID: " + productId);
            
                // Update the product document with the generated ID
                product.setProductId(productId);
                ApiFuture<WriteResult> updateFuture = productDocRef.set(product);
                updateFuture.get(); // Ensure the update is completed before proceeding
                // System.out.println("Product document updated with ID: " + productId);
                return productId;
    }

    // Method to get a specific product of a farmer
    public Product getProductForFarmer(String farmerUid, String productId)
            throws ExecutionException, InterruptedException {
        DocumentReference productRef = db.collection("productFarmers").document(farmerUid).collection("products")
                .document(productId);

        ApiFuture<DocumentSnapshot> future = productRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(Product.class);
        } else {
            System.out.println("No such product document for farmer!");
            return null;
        }
    }

    // get all products of farmer
    public List<Product> getAllProductsForFarmer(String farmerUid) throws ExecutionException, InterruptedException {
        CollectionReference productsRef = db.collection("productFarmers").document(farmerUid).collection("products");
        ApiFuture<QuerySnapshot> future = productsRef.get();
        List<Product> products = new ArrayList<>();
        for (DocumentSnapshot document : future.get().getDocuments()) {
            products.add(document.toObject(Product.class));
        }
        return products;
    }

    // Method to update a product of a farmer
    public void updateProductForFarmer(String farmerUid, String productId, Product product)
            throws ExecutionException, InterruptedException {
        DocumentReference productRef = db.collection("productFarmers").document(farmerUid).collection("products")
                .document(productId);
        ApiFuture<DocumentSnapshot> future = productRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            ApiFuture<WriteResult> updateFuture = productRef.set(product);
            System.out.println("Product document updated for farmer with ID: " + updateFuture.get().getUpdateTime());
        } else {
            System.out.println("No such product document for farmer to update!");
        }
    }

    // Method to delete a product of a farmer
    // public void deleteProductForFarmer(String farmerUid, String productId)
    // throws ExecutionException, InterruptedException {
    // DocumentReference farmerRef =
    // db.collection("productFarmers").document(farmerUid);
    // CollectionReference productCollection = farmerRef.collection("products");
    // DocumentReference productRef = productCollection.document(productId);

    // ApiFuture<WriteResult> future = productRef.delete();
    // System.out.println("Product document deleted for farmer with ID: " +
    // productId + " at " + future.get().getUpdateTime());
    // }

    public void deleteProductForFarmer(String farmerUid, String productId)
            throws ExecutionException, InterruptedException {
        DocumentReference productRef = db.collection("productFarmers").document(farmerUid).collection("products")
                .document(productId);
        ApiFuture<DocumentSnapshot> future = productRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            ApiFuture<WriteResult> deleteFuture = productRef.delete();
            System.out.println("Product document deleted for farmer with ID: " + productId + " at "
                    + deleteFuture.get().getUpdateTime());
        } else {
            System.out.println("No such product document for farmer to delete!");
        }
    }

    // Method to create a product for a businessman
    public void createProductForBusinessman(String businessmanUid, Product product)
            throws ExecutionException, InterruptedException {
        DocumentReference productsRef = db.collection("productBusinessmen").document(businessmanUid)
                .collection("products").document();
        ApiFuture<WriteResult> future = productsRef.set(product);
        System.out.println("Product document created for businessman with ID: " + future.get().getUpdateTime());
    }

    // Method to get a specific product of a businessman
    public Product getProductForBusinessman(String businessmanUid, String productId)
            throws ExecutionException, InterruptedException {
        DocumentReference productRef = db.collection("productBusinessmen").document(businessmanUid)
                .collection("products").document(productId);
        ApiFuture<DocumentSnapshot> future = productRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(Product.class);
        } else {
            System.out.println("No such product document for businessman!");
            return null;
        }
    }

    // Method to update a product of a businessman
    public void updateProductForBusinessman(String businessmanUid, String productId, Product product)
            throws ExecutionException, InterruptedException {
        DocumentReference productRef = db.collection("productBusinessmen").document(businessmanUid)
                .collection("products").document(productId);
        ApiFuture<WriteResult> future = productRef.set(product);
        System.out.println("Product document updated for businessman with ID: " + future.get().getUpdateTime());
    }

    // Method to delete a product of a businessman
    public void deleteProductForBusinessman(String businessmanUid, String productId)
            throws ExecutionException, InterruptedException {
        DocumentReference productRef = db.collection("productBusinessmen").document(businessmanUid)
                .collection("products").document(productId);
        ApiFuture<WriteResult> future = productRef.delete();
        System.out.println("Product document deleted for businessman with ID: " + future.get().getUpdateTime());
    }
}
