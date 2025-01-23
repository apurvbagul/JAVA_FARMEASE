package com.example.repository;

import java.util.concurrent.ExecutionException;

import com.example.authentication.FirebaseAuthentication;
import com.example.model.Admin;
import com.example.model.Farmer;
import com.example.model.Businessman;
import com.example.util.FireBaseUtil;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

public class AdminRepository {
    private Firestore db;

    public AdminRepository() {
        FireBaseUtil firebaseUtil = new FireBaseUtil(); 
        this.db = firebaseUtil.getInstance().getDatabase();
    }

    // farmer accessibility
    public void createFarmer(Farmer farmer) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = db.collection("farmers").document(FirebaseAuthentication.getUserUid()).set(farmer);
        System.out.println("Created document with ID: " + future.get().getUpdateTime());
    }

    public Farmer getFarmerById(String uid) throws ExecutionException, InterruptedException {
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

    public void updateFarmer(Farmer farmer) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = db.collection("farmers").document(FirebaseAuthentication.getUserUid()).set(farmer);
        System.out.println("Update time : " + future.get().getUpdateTime());
    }

    public void deleteFarmer(String uid) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = db.collection("farmers").document(uid).delete();
        System.out.println("Update time : " + future.get().getUpdateTime());
    }

    // bussiness accesibility
    public void createBusinessman(Businessman businessman) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = db.collection("businessman").document(FirebaseAuthentication.getUserUid())
                .set(businessman);
        System.out.println("Created document with ID: " + future.get().getUpdateTime());
    }

    public Businessman getBusinessmanById(String uid) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("businessman").document(uid);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(Businessman.class);
        } else {
            System.out.println("No such document!");
            return null;
        }
    }

    public void updateBusinessman(Businessman businessman) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = db.collection("businessman").document(FirebaseAuthentication.getUserUid())
                .set(businessman);
        System.out.println("Update time : " + future.get().getUpdateTime());
    }

    public void deleteBusinessman(String uid) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = db.collection("businessman").document(uid).delete();
        System.out.println("Update time : " + future.get().getUpdateTime());
    }

    // admin accesibility
    public void createAdmin(Admin admin) throws ExecutionException, InterruptedException {
        System.out.println(FirebaseAuthentication.getUserUid());
        ApiFuture<WriteResult> future = db.collection("admin").document(FirebaseAuthentication.getUserUid()).set(admin);
        System.out.println("Update time : " + future.get().getUpdateTime());
    }

    public Admin getAdminById(String uid) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("admin").document(uid);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            return document.toObject(Admin.class);
        } else {
            System.out.println("No such document!");
            return null;
        }
    }

    public void updateAdmin(Admin admin) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = db.collection("businessman").document(FirebaseAuthentication.getUserUid()).set(admin);
        System.out.println("Update time : " + future.get().getUpdateTime());
    }

    public void deleteAdmin(String uid) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = db.collection("admin").document(uid).delete();
        System.out.println("Update time : " + future.get().getUpdateTime());
    }

}
