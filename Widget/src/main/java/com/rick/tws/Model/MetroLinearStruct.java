package com.rick.tws.Model;

import java.util.ArrayList;
import java.util.List;

public class MetroLinearStruct extends MetroArea {
    //行
    public static final String METRO_CARD_ROW_PREFIX = "row";

    //带自定义信息的行(ver1.0 有自定义行高)
    public static final String METRO_CARD_CUSTOM_ROW_PREFIX = "row_c";

    public static final String METRO_ROW_CARDS_PREFIX = "cards";
    public static final String METRO_CARD_ROW_KEY_HEIGHT = "row_height";
    private static final int CARDS_DEFAULT_CAPACITY = 3;

    private int mHeight = -1;
    public List<MetroCardStruct> cards;

    public MetroLinearStruct() {
        cards = new ArrayList<>(CARDS_DEFAULT_CAPACITY);
    }

    public MetroLinearStruct(int initialCardsCapacity) {
        if (initialCardsCapacity <= 0) {
            initialCardsCapacity = CARDS_DEFAULT_CAPACITY;
        }

        cards = new ArrayList<>(initialCardsCapacity);
    }

    public void setHeight(int height) {
        this.mHeight = height;
    }

    public int getHeight() {
        return this.mHeight;
    }
}
