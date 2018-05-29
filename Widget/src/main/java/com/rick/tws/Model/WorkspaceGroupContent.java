package com.rick.tws.Model;

import android.graphics.Color;

import java.util.ArrayList;

public class WorkspaceGroupContent {
    public ArrayList<CellItemStruct> cellItemList;

    private String groupName;
    private int groupId;
    public int header_height = CellItemStruct.INVALID_VALUE;
    public String gicon;

    @Deprecated
    public String header_textColor;     //for json
    private int headerTextColor = Color.WHITE;
    public int getHeaderTextColor() {
        return headerTextColor;
    }
    public void setHeaderTextColor(int color) {
        this.headerTextColor = color;
    }

    public int header_textSize = CellItemStruct.INVALID_VALUE;
    public int getHeaderTextSize() {
        return header_textSize;
    }

    private boolean header_boldText = false;
    public boolean getIsHeaderBoldText() {
        return header_boldText;
    }

    private boolean isShrink = true;
    public boolean getIsShrink() {
        return isShrink;
    }

    @Deprecated
    public String header_background;     //for json
    private int header_backgroundColor = Color.WHITE;
    public int getHeaderBackgroundColor() {
        return header_backgroundColor;
    }
    public void setHeaderBackgroundColor(int color) {
        this.header_backgroundColor = color;
    }

    //为当前组统一配置
    public int item_width = CellItemStruct.INVALID_VALUE;
    public int item_height = CellItemStruct.INVALID_VALUE;
    public int icon_width = CellItemStruct.INVALID_VALUE;
    public int icon_height = CellItemStruct.INVALID_VALUE;

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

    public void setGIcon(String icon) {
        this.gicon = icon;
    }

    public String getGIcon() {
        return this.gicon;
    }
}
