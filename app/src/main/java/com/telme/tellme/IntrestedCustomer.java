package com.telme.tellme;



public class IntrestedCustomer {
    String id;
    String name;
    String mobile;
    String varient;
    String hypo;
    User user;


    public IntrestedCustomer(String id, String name, String mobile, String varient, String hypo, User user) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.varient = varient;
        this.hypo = hypo;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVarient() {
        return varient;
    }

    public void setVarient(String varient) {
        this.varient = varient;
    }

    public String getHypo() {
        return hypo;
    }

    public void setHypo(String hypo) {
        this.hypo = hypo;
    }

    public IntrestedCustomer() {
    }

    public IntrestedCustomer(String id, String name, String mobile, String varient, String hypo) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.varient = varient;
        this.hypo = hypo;
    }



    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", varient='" + varient + '\'' +
                ", hypo='" + hypo + '\'' +
                '}';
    }
}
