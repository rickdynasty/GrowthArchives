package com.rick.tws.Model;

public abstract class MetroArea {
    protected String mName;
    protected int mID;

    public void setName(String name) {
        this.mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setID(int id) {
        this.mID = id;
    }

    public int getID() {
        return mID;
    }

}
