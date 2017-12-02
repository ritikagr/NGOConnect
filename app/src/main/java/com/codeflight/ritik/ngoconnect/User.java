package com.codeflight.ritik.ngoconnect;

/**
 * Created by ritik on 12/2/2017.
 */

public class User {
    private String name,mobile,city,state;

    public User()
    {

    }

    public User(String name,String mobile,String city,String state)
    {
        this.name = name;
        this.mobile = mobile;
        this.city = city;
        this.state = state;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
