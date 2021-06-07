package com.rozaanaonline.groceryshopping.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignupPojo {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("_address_street")
    @Expose
    private Object addressStreet;
    @SerializedName("_address_telephone")
    @Expose
    private Object addressTelephone;
    @SerializedName("_address_postcode")
    @Expose
    private Object addressPostcode;
    @SerializedName("_address_region")
    @Expose
    private Object addressRegion;
    @SerializedName("_address_city")
    @Expose
    private Object addressCity;
    @SerializedName("token")
    @Expose
    private String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Object getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(Object addressStreet) {
        this.addressStreet = addressStreet;
    }

    public Object getAddressTelephone() {
        return addressTelephone;
    }

    public void setAddressTelephone(Object addressTelephone) {
        this.addressTelephone = addressTelephone;
    }

    public Object getAddressPostcode() {
        return addressPostcode;
    }

    public void setAddressPostcode(Object addressPostcode) {
        this.addressPostcode = addressPostcode;
    }

    public Object getAddressRegion() {
        return addressRegion;
    }

    public void setAddressRegion(Object addressRegion) {
        this.addressRegion = addressRegion;
    }

    public Object getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(Object addressCity) {
        this.addressCity = addressCity;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
