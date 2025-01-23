package com.example.model;

public class Admin {
    private String adminName;   
    private String adminEmailId;
    private Long adminPhoneNumber;
    private String adminCity;
    private int adminPincode;
    private String adminGender;
    private String adminAddress;
    private String adminAbout;
    private String adminPassword;

    public int getAdminPincode() {
        return adminPincode;
    }
    public void setAdminPincode(int adminPincode) {
        this.adminPincode = adminPincode;
    }
    private String adminState;
    public String getAdminName() {
        return adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public String getAdminEmailId() {
        return adminEmailId;
    }
    public void setAdminEmailId(String adminEmailId) {
        this.adminEmailId = adminEmailId;
    }
    public Long getAdminPhoneNumber() {
        return adminPhoneNumber;
    }
    public void setAdminPhoneNumber(Long adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }
    public String getAdminCity() {
        return adminCity;
    }
    public void setAdminCity(String adminCity) {
        this.adminCity = adminCity;
    }
    public String getAdminState() {
        return adminState;
    }
    public void setAdminState(String adminState) {
        this.adminState = adminState;
    }
    public String getAdminGender() {
        return adminGender;
    }
    public void setAdminGender(String adminGender) {
        this.adminGender = adminGender;
    }
    public String getAdminAddress() {
        return adminAddress;
    }
    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }
    public String getAdminAbout() {
        return adminAbout;
    }
    public void setAdminAbout(String adminAbout) {
        this.adminAbout = adminAbout;
    }
    public String getAdminPassword() {
        return adminPassword;
    }
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
    
}
