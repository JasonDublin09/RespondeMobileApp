package com.example.responde;

import java.util.ArrayList;
import java.util.List;

public class UserHelperClass {

    String name, contact, email, home,option,date,status;
    Double lat,lng;
    /*ArrayList<String> emcon;*/
    List<String> emcon;


    public UserHelperClass() {
    }

    public UserHelperClass(String name, String contact, String email, String home, Double lat, Double lng, String option,String date,String status/*, ArrayList<String> emcon*/, List<String>emcon) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.home = home;
        this.lat = lat;
        this.lng = lng;
        this.option = option;
        this.date = date;
        this.status= status;
        this.emcon = emcon;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   /* public ArrayList<String> getEmcon() {
        return emcon;
    }

    public void setEmcon(ArrayList<String> emcon) {
        this.emcon = emcon;
    }*/

    public List<String> getEmcon() {
        return emcon;
    }

    public void setEmcon(List<String> emcon) {
        this.emcon = emcon;
    }
}


