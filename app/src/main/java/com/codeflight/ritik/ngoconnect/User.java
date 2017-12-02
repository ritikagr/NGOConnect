package com.codeflight.ritik.ngoconnect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ritik on 12/2/2017.
 */

public class User {
    public String Email,Name,Mobile,City,State,Type;
    public List<String> Category;

    public User()
    {
        Category = new ArrayList<>();
    }

    public User(String email,String name,String mobile,String city,String state,List<String> list,String type)
    {
        this.Email = email;
        this.Name = name;
        this.Mobile = mobile;
        this.City = city;
        this.State = state;
        this.Category = list;
        this.Type = type;
    }
}
