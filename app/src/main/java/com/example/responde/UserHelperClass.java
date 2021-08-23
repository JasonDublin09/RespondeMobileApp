package com.example.responde;

public class UserHelperClass {

    String name, contact, email, home;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String contact, String email, String home) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.home = home;
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
}
