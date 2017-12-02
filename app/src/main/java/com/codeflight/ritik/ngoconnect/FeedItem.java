package com.codeflight.ritik.ngoconnect;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RAJAT-PC on 02-12-2017.
 */
public class FeedItem implements Parcelable {
    public String Activity, Description, Category, Location, NGOName, OtherHelps;

    public FeedItem(){

    }

    public FeedItem(String activity,String description,String category,String name_of_ngo, String location, String otherHelps){
        this.Activity = activity;
        this.Description = description;
        this.Category = category;
        this.NGOName = name_of_ngo;
        this.Location = location;
        this.OtherHelps = otherHelps;
    }

    protected FeedItem(Parcel in) {
        Activity = in.readString();
        Description = in.readString();
        Category = in.readString();
        Location = in.readString();
        NGOName = in.readString();
        OtherHelps = in.readString();
    }

    public static final Creator<FeedItem> CREATOR = new Creator<FeedItem>() {
        @Override
        public FeedItem createFromParcel(Parcel in) {
            return new FeedItem(in);
        }

        @Override
        public FeedItem[] newArray(int size) {
            return new FeedItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Activity);
        parcel.writeString(Description);
        parcel.writeString(Category);
        parcel.writeString(Location);
        parcel.writeString(NGOName);
        parcel.writeString(OtherHelps);
    }
}
