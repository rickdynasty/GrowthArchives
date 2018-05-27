package com.rick.tws.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class NavigationUI extends RecyclerView {
    //列【即一行显示的个数】
    public static final int GRID_SPANCOUNT = 3;
    // 默认收起显示的行数
    public static final int GRID_GROUP_OFF_MULTIPLE_SPANCOUNT = 1;

    public NavigationUI(Context context) {
        this(context, null);
    }

    public NavigationUI(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationUI(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
