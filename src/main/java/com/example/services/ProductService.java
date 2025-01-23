package com.example.services;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.model.Businessman;
import com.example.model.Farmer;
import com.example.model.Product;
import com.example.repository.ProductRepository;

public class ProductService {
    private ProductRepository productRepository;
    private Businessman loggedBusinessman;
    private Farmer loggedFarmer;

    public ProductService() {
        this.productRepository = new ProductRepository();

    }

    // Methods for Businessman
    public String createProductForFarmer(String farmerUid, Product product) {
        try {
        String temp =   productRepository.createProductForFarmer(farmerUid, product);
        return temp;
           
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
        
    }

    public Product getProductForFarmer(String farmerUid, String productId) {
        try {
            return productRepository.getProductForFarmer(farmerUid, productId);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Product> getAllProductsForFarmer(String farmerUid) {
        try {
            return productRepository.getAllProductsForFarmer(farmerUid);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void updateProductForFarmer(String farmerUid, String productId, Product product) {
        try {
            productRepository.updateProductForFarmer(farmerUid, productId, product);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductForFarmer(String farmerUid, String productId) {
        try {
            productRepository.deleteProductForFarmer(farmerUid, productId);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Product Methods for Businessman

    public void createProductForBusinessman(String businessmanUid, Product product) {
        try {
            productRepository.createProductForBusinessman(businessmanUid, product);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Product getProductForBusinessman(String businessmanUid, String productId) {
        try {
            return productRepository.getProductForBusinessman(businessmanUid, productId);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateProductForBusinessman(String businessmanUid, String productId, Product product) {
        try {
            productRepository.updateProductForBusinessman(businessmanUid, productId, product);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductForBusinessman(String businessmanUid, String productId) {
        try {
            productRepository.deleteProductForBusinessman(businessmanUid, productId);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Other utility methods

    public void login(Businessman businessman) {
        this.loggedBusinessman = businessman;
    }

    public void logout() {
        this.loggedBusinessman = null;
    }

    public Businessman getLoggedInBusinessman() {
        return loggedBusinessman;
    }

    public void login(Farmer farmer) {
        this.loggedFarmer = farmer;
    }

    public void logoutFarmer() {
        this.loggedFarmer = null;
    }

    public Farmer getLoggedInFarmer() {
        return loggedFarmer;
    }
}
