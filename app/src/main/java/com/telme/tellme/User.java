package com.telme.tellme;


public class User {

    private String name;
    private String password;
    private String mobile;
    private String email;
    private String userType;

    public User() {
    }

    public User(String name, String password, String mobile, String email, String userType) {
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
        this.userType = userType;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "{"
                + " \"name\":\"" + name + "\""
                + ", \"password\":\"" + password + "\""
                + ", \"mobile\":\"" + mobile + "\""
                + ", \"email\":\"" + email + "\""
                + ", \"userType\":\"" + userType + "\""
                + "}";
    }
}