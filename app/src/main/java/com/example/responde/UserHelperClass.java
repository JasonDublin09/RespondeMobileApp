package com.example.responde;

public class UserHelperClass {

    String name, contact, email, home,option,date;
    Double lat,lng;


    public UserHelperClass() {
    }

    public UserHelperClass(String name, String contact, String email, String home, Double lat, Double lng, String option,String date) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.home = home;
        this.lat = lat;
        this.lng = lng;
        this.option = option;
        this.date = date;

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

        return lng;
    }

    public void setLng(Double lng) {

        this.lng = lng;
    }

    public String getOption() {

        return option;
    }
    public void setOption(String option) {

        this.option = option;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
