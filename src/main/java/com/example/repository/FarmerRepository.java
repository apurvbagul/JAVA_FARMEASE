package com.example.repository;



import java.util.concurrent.ExecutionException;

import com.example.authentication.FirebaseAuthentication;
import com.example.model.Farmer;
import com.example.util.FireBaseUtil;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

public class FarmerRepository {
    private Firestore db;
     public FarmerRepository() {
        FireBaseUtil firebaseUtil = new FireBaseUtil();
        this.db = FireBaseUtil.getInstance().getDatabase();
    }   

    // public void createFarmer(Farmer farmer) throws ExecutionException, InterruptedException {
    //     ApiFuture<WriteResult> future = db.collection("farmers").document(FirebaseAuthentication.getUserUid()).set(farmer);
    //     System.out.println("Created document with ID: " + future.get().getUpdateTime());
        
    // }

    public void createFarmer(Farmer farmer) throws ExecutionException, InterruptedException {
        // Get the UID from FirebaseAuthentication
        String userUid = FirebaseAuthentication.getUserUid();
        System.out.println(userUid);
        // Set the farmer's UID
        farmer.setFarmerId(userUid);
        
        // Save the updated farmer object to Firestore
        ApiFuture<WriteResult> future = db.collection("farmers").document(userUid).set(farmer);
        
        // Print the update time of the document
        System.out.println("Created document with ID: " + userUid + " at time: " + future.get().getUpdateTime());
    }
    


    public void updateFarmer(Farmer farmer) throws ExecutionException, InterruptedException {
        System.out.println("updated");
        ApiFuture<WriteResult> future = db.collection("farmers").document(FirebaseAuthentication.getUserUid()).set(farmer);
        System.out.println("Update time : " + future.get().getUpdateTime());
    }


     public Farmer getFarmerByEmailId(String uid) throws ExecutionException, InterruptedException {
        System.out.println(uid);
        DocumentReference docRef = db.collection("farmers").document(uid);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(Farmer.class);
        } else {
            System.out.println("No such document!");
            return null;
        }
    }

 
    public void deleteFarmer(String uid) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = db.collection("farmers").document(uid).delete();
        System.out.println("Update time : " + future.get().getUpdateTime());
    }
    
}
