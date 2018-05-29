package com.rick.tws.Model;

import java.util.ArrayList;

public class WorkspaceGroupContent {
    public ArrayList<CellItemStruct> cellItemList;

    protected String groupName;
    protected int groupId;

    public void setName(String name) {
        this.groupName = name;
    }

    public String getName() {
        return groupName;
    }

    public void setID(int id) {
        this.groupId = id;
    }

    public int getID() {
        return groupId;
    }
}
