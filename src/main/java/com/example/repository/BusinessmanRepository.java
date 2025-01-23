package com.example.repository;

import java.util.concurrent.ExecutionException;

import com.example.authentication.FirebaseAuthentication;
import com.example.model.Businessman;
import com.example.util.FireBaseUtil;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

public class BusinessmanRepository {

     private Firestore db;

    public BusinessmanRepository() {
        FireBaseUtil firebaseUtil = new FireBaseUtil();
        this.db = FireBaseUtil.getInstance().getDatabase();
    }

    // bussiness accesibility
    
    public void createBusinessman(Businessman businessman) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = db.collection("businessman").document(FirebaseAuthentication.getUserUid())
                .set(businessman);
        System.out.println("Created document with ID: " + future.get().getUpdateTime());
    }

    public Businessman getBusinessmanByEmailId(String uid) throws ExecutionException, InterruptedException {
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

}