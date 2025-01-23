package com.example.authentication;

import java.io.IOException;
import okhttp3.*;
import org.json.JSONObject;

public class FirebaseAuthentication {
    private static final String API_KEY = "AIzaSyDwcdlNaZXSo450KtA9lJeGLgOiQGsAA8k";
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static String userUid;
    
    //SIGN-UP USED FOR THE AUTHENTICATION FOR USER
    public static String signUp(String email, String password) throws IOException {
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY;
        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("password", password);
        json.put("returnSecureToken", true);
        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();    
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("already have an account");
                throw new IOException("Unexpected code ");
            }
            String responseData = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseData);
            userUid = jsonResponse.getString("localId");
            return jsonResponse.getString("idToken");
        }
    }

    //SIGN-IP USED FOR THE AUTHENTICATION FOR USER
    public static String signIn(String email, String password) throws IOException {
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;
        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("password", password);
        json.put("returnSecureToken", true);
        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String responseData = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseData);
            userUid = jsonResponse.getString("localId");
            return jsonResponse.getString("idToken");
        }
    }

    public static String getUserUid(){
        return userUid;
    }
}
