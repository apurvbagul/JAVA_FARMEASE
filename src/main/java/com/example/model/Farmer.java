package com.example.model;

public class Farmer {
    private String farmerId;
    private String farmerName;   
    private String farmerEmailId;
    private Long farmerPhoneNumber;
    private String farmerCity;
    private String farmerState;
    private String farmerGender;
    private String farmerAddress;
    private String farmerAbout;
    private String  farmerPassword;
    private int farmerPincode;
    private String profilePageUrl;

    public String getProfilePageUrl() {
        return profilePageUrl;
    }


    public void setProfilePageUrl(String profilePageUrl) {
        this.profilePageUrl = profilePageUrl;
    }


    public Farmer(){

    }


    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String userUid) {
        this.farmerId = userUid;
    }
    public int getFarmerPincode() {
        return farmerPincode;
    }
    public void setFarmerPincode(int farmerPincode) {
        this.farmerPincode = farmerPincode;
    }
    public String getFarmerPassword() {
        return farmerPassword;
    }
    public void setFarmerPassword(String farmerPassword) {
        this.farmerPassword = farmerPassword;
    }
    public String getFarmerName() {
        return farmerName;
    }
    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }
    public String getFarmerEmailId() {
        return farmerEmailId;
    }
    public void setFarmerEmailId(String farmerEmailId) {
        this.farmerEmailId = farmerEmailId;
    }
    public Long getFarmerPhoneNumber() {
        return farmerPhoneNumber;
    }
    public void setFarmerPhoneNumber(Long farmerPhoneNumber) {
        this.farmerPhoneNumber = farmerPhoneNumber;
    }
    public String getFarmerCity() {
        return farmerCity;
    }
    public void setFarmerCity(String farmerCity) {
        this.farmerCity = farmerCity;
    }
    public String getFarmerState() {
        return farmerState;
    }
    public void setFarmerState(String farmerState) {
        this.farmerState = farmerState;
    }
    public String getFarmerGender() {
        return farmerGender;
    }
    public void setFarmerGender(String farmerGender) {
        this.farmerGender = farmerGender;
    }
    public String getFarmerAddress() {
        return farmerAddress;
    }
    public void setFarmerAddress(String farmerAddress) {
        this.farmerAddress = farmerAddress;
    }
    public String getFarmerAbout() {
        return farmerAbout;
    }
    public void setFarmerAbout(String farmerAbout) {
        this.farmerAbout = farmerAbout;
    }
}
