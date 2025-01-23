package com.example.services;


import com.example.authentication.FirebaseAuthentication;
import com.example.model.FeedbackForm;
import com.example.repository.FeedbackRepository;

public class FeedbackService {
    private static FeedbackRepository feedbackRepository;
    public FeedbackService(){
        feedbackRepository = new FeedbackRepository();
    }
    
    public static boolean createFeedback(FeedbackForm feedbackForm) {
            System.out.println("in service");
            System.out.println(feedbackForm);
            boolean fd =  feedbackRepository.saveFeedbackForm(FirebaseAuthentication.getUserUid(), feedbackForm);
            return fd;
        // return true;
    }
}
