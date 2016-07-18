package com.epicodus.beerfindr.models;

/**
 * Created by Peter on 7/18/16.
 */
public class Beer {
    private String mName;
    private String mDescription;
    private String mABV;
    private String mIBU;

    public Beer (String name, String description, String ABV, String IBU) {
        this.mName = name;
        this.mDescription = description;
        this.mABV = ABV;
        this.mIBU = IBU;
    }

    public String getName(){
        return  mName;
    }

    public String getDescription(){
        return mDescription;
    }

    public String getABV(){
        return mABV;
    }

    public String getIBU(){
        return mIBU;
    }
}
