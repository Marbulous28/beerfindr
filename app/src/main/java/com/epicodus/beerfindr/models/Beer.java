package com.epicodus.beerfindr.models;

import org.parceler.Parcel;

/**
 * Created by Peter on 7/18/16.
 */

@Parcel
public class Beer {
    private String name;
    private String description;
    private String abv;
    private String ibu;
    private String id;
    private String pushId;

    public Beer() {}

    public Beer (String name, String description, String ABV, String IBU, String id) {
        this.name = name;
        this.description = description;
        this.abv = ABV;
        this.ibu = IBU;
        this.id = id;
    }

    public String getName(){
        return  name;
    }

    public String getDescription(){
        return description;
    }

    public String getABV(){
        return abv;
    }

    public String getIBU(){
        return ibu;
    }

    public String getId() { return id; }

    public String getPushId(){
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
