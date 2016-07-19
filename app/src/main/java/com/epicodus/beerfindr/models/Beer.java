package com.epicodus.beerfindr.models;

/**
 * Created by Peter on 7/18/16.
 */
public class Beer {
    private String mName;
    private String mDescription;
    private String mABV;
    private String mIBU;
    private String mId;

    public Beer (String name, String description, String ABV, String IBU, String id) {
        this.mName = name;
        this.mDescription = description;
        this.mABV = ABV;
        this.mIBU = IBU;
        this.mId = id;
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

    public String getId() { return mId; }
}
