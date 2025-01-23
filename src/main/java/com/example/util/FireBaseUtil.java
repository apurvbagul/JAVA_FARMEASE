package com.example.util;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class FireBaseUtil {
    private static FireBaseUtil instance;
    private Firestore db;

    public FireBaseUtil() {
        try {
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized FireBaseUtil getInstance() {
        if (instance == null) {
            instance = new FireBaseUtil();
        }
        return instance;
    }

    private void initialize() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(
                "C:\\Users\\APURV BAGUL\\OneDrive\\Desktop\\farmease\\farmease\\src\\main\\resources\\sohamFarmEase.json");

        @SuppressWarnings("deprecation")
        // FirebaseOptions options = new FirebaseOptions.Builder()
        // .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        // .build();

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://farmease-e28c1-default-rtdb.asia-southeast1.firebasedatabase.app")
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        db = FirestoreClient.getFirestore();
    }

    public Firestore getDatabase() {
        return db;
    }

}
