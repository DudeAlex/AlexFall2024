package com.ecommerce.pojo;

public class UserData {

    private String firstName;
    private String lastName;
    private String country;
    private String address;
    private String town;
    private String state;
    private String zipCode;
    private String emailAddress;

    public UserData(String firstName, String lastName, String country, String address, String zipCode, String state, String town, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.address = address;
        this.zipCode = zipCode;
        this.state = state;
        this.town = town;
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town){
        this.town = town;

    }

    public String getState() {
        return state;
    }

    public void setState(String state){
        this.state = state;
    }
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

   public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
   }

   public String toString(){
        return "First Name " + firstName +
                "Last name " + lastName +
                "Country " + country +
                "Address " + address +
                "Town " + town +
                "State " + state +
                "Zip Code " + zipCode +
                "Email Address " + emailAddress;
   }

}
