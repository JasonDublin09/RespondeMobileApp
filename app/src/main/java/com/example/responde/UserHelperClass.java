package com.example.responde;

public class UserHelperClass {

    String name, contact, home;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String contact, String home) {
        this.name = name;
        this.contact = contact;
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

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
