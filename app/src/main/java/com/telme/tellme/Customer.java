package com.telme.tellme;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class Customer {
    String id;
    String name;
    String mobile;
    String varient;
    String hypo;
    String details;

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public Customer() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return name.equals(customer.name) &&
                mobile.equals(customer.mobile);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(name, mobile);
    }

    public Customer(String id, String name, String mobile, String varient, String hypo) {
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
