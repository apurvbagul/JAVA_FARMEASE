package com.example.services;

import java.util.concurrent.ExecutionException;

import com.example.model.Businessman;
import com.example.repository.BusinessmanRepository;

public class BusinessmanService {
    private BusinessmanRepository businessmanRepository;
    private Businessman loggedBusinessman;

    public BusinessmanService() {
        this.businessmanRepository = new BusinessmanRepository();

    }

    // Bussiness man accessibility
    public boolean createBusinessman(Businessman businessman) {
        try {
            businessmanRepository.createBusinessman(businessman);
            login(businessman);
            return true;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Businessman getBusinessmanById(String emailId) {
        try {
            return businessmanRepository.getBusinessmanByEmailId(emailId);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateBusinessman(Businessman businessman) {
        try {
            businessmanRepository.updateBusinessman(businessman);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteBusinessman(String emailId) {
        try {
            businessmanRepository.deleteBusinessman(emailId);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void login(Businessman businessman) {
        this.loggedBusinessman = businessman;
    }

    public void logout() {
        this.loggedBusinessman = null;
        this.businessmanRepository = null;
    }

    public Businessman getLoggedInBusinessman() {
        return loggedBusinessman;
    }

}
