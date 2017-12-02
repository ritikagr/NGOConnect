package com.codeflight.ritik.ngoconnect;

/**
 * Created by RAJAT-PC on 02-12-2017.
 */
public class NGO {
    public String Name,Website,Email,Office_Email,Office_Phone,Address,Unique_Id,Type;

    public NGO(){

    }

    public NGO(String name, String website, String email, String office_phone, String address, String unique_id,String Type,String office_email){
        this.Name = name;
        this.Website = website;
        this.Email = email;
        this.Office_Phone = office_phone;
        this.Address = address;
        this.Unique_Id = unique_id;
        this.Type = Type;
        this.Office_Email = office_email;
    }
}
