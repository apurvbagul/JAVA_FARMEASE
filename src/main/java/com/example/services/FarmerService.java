package com.example.services;

import java.util.concurrent.ExecutionException;
import com.example.model.Farmer;
import com.example.repository.FarmerRepository;

public class FarmerService {
    private FarmerRepository farmerRepository;
    private Farmer loggedInFarmer; 
    private static FarmerRepository farmerRepository2;

    public FarmerService() {
        this.farmerRepository = new FarmerRepository();
        this.farmerRepository2 = new FarmerRepository();
    }
    public boolean createFarmer(Farmer farmer) {
        try {
            farmerRepository.createFarmer(farmer);
            login(farmer);
            return true;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public Farmer getFarmerById(String emailId) {
        try {
            return farmerRepository.getFarmerByEmailId(emailId);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void updateFarmer(Farmer farmer) {
        try {
            farmerRepository2.updateFarmer(farmer);
            
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void deleteFarmer(String emailId) {
        try {
            farmerRepository.deleteFarmer(emailId);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void login(Farmer farmer) {
        this.loggedInFarmer = farmer; // Set the logged-in farmer
    }

    public void logout(){
        this.loggedInFarmer = null;
        farmerRepository = null; // Clear the logged-in farmer
    }

    public Farmer getLoggedInFarmer() {
        return loggedInFarmer;
    }
}

