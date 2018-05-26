package com.rick.tws.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;

/**
 * 简单的MetroUI
 */
public class SimpleMetroUI extends LinearLayout {
    private Context mContext;

    public SimpleMetroUI(Context context) {
        this(context, null);
    }

    public SimpleMetroUI(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleMetroUI(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SimpleMetroUI(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;

        //ver1.0只支持VERTICAL
        setOrientation(LinearLayout.VERTICAL);

        //设置布局的LayoutParams
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        int topMargin = getResources().getDimensionPixelSize(R.dimen.metro_ui_margin_top);
        int leftMargin = getResources().getDimensionPixelSize(R.dimen.metro_ui_margin_left);
        int rightMargin = getResources().getDimensionPixelSize(R.dimen.metro_ui_margin_right);
        int bottomMargin = getResources().getDimensionPixelSize(R.dimen.metro_ui_margin_right);
        layoutParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        setLayoutParams(layoutParams);
    }

    public void addOneLinearCard(CardLinearContainer linearCard) {
        addView(linearCard);
    }

    public void addOneGroupCard(CardGroupContainer groupContainer) {
        addView(groupContainer);
    }

    public void addEndSpace(int height) {
        Space space = new Space(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
        addView(space, lp);
    }
}
