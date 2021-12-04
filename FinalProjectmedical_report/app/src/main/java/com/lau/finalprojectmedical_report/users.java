package com.lau.finalprojectmedical_report;

public class users {

    private int id;
    private String username, email;

    public users(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;

    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

}
