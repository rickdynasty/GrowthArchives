package com.rick.tws.Model;

public abstract class MetroArea {
    protected String name;
    protected int id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

}
