package com.rick.tws.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class NavigationUI extends RecyclerView {
    public static final int GRID_SPANCOUNT = 4;
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
