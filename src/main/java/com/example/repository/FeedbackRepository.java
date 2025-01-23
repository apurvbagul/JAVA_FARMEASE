package com.example.repository;

import java.util.concurrent.ExecutionException;

import com.example.model.FeedbackForm;
import com.example.util.FireBaseUtil;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

public class FeedbackRepository {

    private Firestore db = FireBaseUtil.getInstance().getDatabase();

    public boolean saveFeedbackForm(String userId, FeedbackForm feedbackForm) {

        // Create a document reference with the userId as part of the path
        DocumentReference feedbackFormDocRef = db.collection("UserFeedBackForm").document(userId).collection("feedbackForms").document(userId);
        
        // Check if the document already exists
        try {
            if (feedbackFormDocRef.get().get().exists()) {
                System.out.println("Document with ID: " + userId + " already exists.");
                System.out.println("in repository false");
                return false; // Document already exists, return false
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }

        // Set the document if it does not exist
        ApiFuture<WriteResult> future = feedbackFormDocRef.set(feedbackForm);

        // Block on the operation, and get any errors that occurred
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Created document with ID: " + userId);
        System.out.println("in repository true");
        return true; // Document successfully created
    }
}
