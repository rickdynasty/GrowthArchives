package com.rick.tws.Model;

import java.util.ArrayList;
import java.util.List;

public class MetroGroupStruct extends MetroArea {
    public static final String METRO_CARD_GROUP_PREFIX = "group";
    public static final String METRO_CARD_GROUP_KEY_NAME = "group_name";
    public static final String METRO_CARD_GROUP_KEY_ID = "group_id";


    private static final int ROW_DEFAULT_CAPACITY = 2;
    public List<MetroArea> linearStructs;

    public MetroGroupStruct() {
        linearStructs = new ArrayList<>(ROW_DEFAULT_CAPACITY);
    }

    public MetroGroupStruct(int initialRowCapacity) {
        if (initialRowCapacity <= 0) {
            initialRowCapacity = ROW_DEFAULT_CAPACITY;
        }

        linearStructs = new ArrayList<>(initialRowCapacity);
    }
}
