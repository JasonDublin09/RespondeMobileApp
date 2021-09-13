package com.example.responde;

public class UserHelperClass {

    String name, contact, email, home;
    Double lat,lng;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String contact, String email, String home, Double lat, Double lng) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.home = home;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
    public Double getLat(){
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
    public Double getLng(){
        return lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }



}
