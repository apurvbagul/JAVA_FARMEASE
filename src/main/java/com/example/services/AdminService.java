package com.example.services;

import java.util.concurrent.ExecutionException;

import com.example.model.Admin;
import com.example.model.Businessman;
import com.example.model.Farmer;
import com.example.repository.AdminRepository;
import com.example.repository.BusinessmanRepository;
import com.example.repository.FarmerRepository;

public class AdminService {
    private AdminRepository adminRepository;
    private FarmerRepository farmerRepository;
    private BusinessmanRepository businessmanRepository;
    private Admin loggedAdmin;

    public AdminService() {
        this.adminRepository = new AdminRepository();
        this.farmerRepository = new FarmerRepository();
        this.businessmanRepository = new BusinessmanRepository();
    }


    //Admins accessibility
    public boolean createAdmin(Admin admin) {
        try {
            adminRepository.createAdmin(admin);
            login(admin);
            return true;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Admin getAdminById(String emailId) {
        try {
            return adminRepository.getAdminById(emailId);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void updateAdmin(Admin admin) {
        try {
            adminRepository.updateAdmin(admin);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteAdmin(String emailId) {
        try {
            adminRepository.deleteAdmin(emailId);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    //farmers Accessibility
    public Farmer getFarmerById(String emailId) {
        try {
            return farmerRepository.getFarmerByEmailId(emailId);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void updateFarmer(Farmer farmer) {
        try {
            farmerRepository.updateFarmer(farmer);
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


    //Bussiness man accessibility

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

    public void login(Admin admin){
        this.loggedAdmin = admin;
    }
    public void logout(){
        this.loggedAdmin = null;
        this.adminRepository = null;

    }

    public Admin getLoggedAdmin(){
        return this.loggedAdmin;
    }



}
