package com.telme.tellme;

public class Holidays {

    private final String name;
    private final String mobile;
    private final String varient;
    private final String hypo;


    public Holidays(String name, String mobile,String varient, String hypo) {
        this.name = name;
        this.mobile = mobile;
        this.hypo  = hypo;
        this.varient = varient;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }
    public String getVarient(){
        return varient;
    }
    public String getHypo(){
        return hypo;
    }
}