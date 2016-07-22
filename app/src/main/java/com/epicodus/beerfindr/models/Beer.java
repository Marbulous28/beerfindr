package com.epicodus.beerfindr.models;

import org.parceler.Parcel;

/**
 * Created by Peter on 7/18/16.
 */

@Parcel
public class Beer {
    private String Name;
    private String Description;
    private String ABV;
    private String IBU;
    private String Id;

    public Beer() {}

    public Beer (String name, String description, String ABV, String IBU, String id) {
        this.Name = name;
        this.Description = description;
        this.ABV = ABV;
        this.IBU = IBU;
        this.Id = id;
    }

    public String getName(){
        return  Name;
    }

    public String getDescription(){
        return Description;
    }

    public String getABV(){
        return ABV;
    }

    public String getIBU(){
        return IBU;
    }

    public String getId() { return Id; }
}
