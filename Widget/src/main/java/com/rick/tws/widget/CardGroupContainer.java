package com.rick.tws.widget;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CardGroupContainer extends LinearLayout {
    private int mGroupId = 0;
    private TextView mTileTv;

    public CardGroupContainer(Context context) {
        this(context, null);
    }

    public CardGroupContainer(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardGroupContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CardGroupContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        //ver1.0只支持VERTICAL
        setOrientation(LinearLayout.VERTICAL);

        mTileTv = new TextView(context);
        RelativeLayout.LayoutParams tvParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        int leftMargin = getResources().getDimensionPixelSize(R.dimen.metro_ui_margin_left);
        int topMargin = getResources().getDimensionPixelSize(R.dimen.metro_ui_margin_top);
        int rightMargin = getResources().getDimensionPixelSize(R.dimen.metro_ui_margin_right);
        int buttonMargin = 0;
        tvParams.setMargins(leftMargin, topMargin, rightMargin, buttonMargin);
        addView(mTileTv, tvParams);
        mTileTv.setBackgroundColor(0xFFE8E8E8);
    }

    public void setGroupId(int groupId) {
        mGroupId = groupId;
    }

    public int getGroupId() {
        return mGroupId;
    }

    public void setTile(CharSequence title) {
        if (null == mTileTv) {
            throw new RuntimeException("The \"null == mTileTv\" scenario is theoretically absent~!");
        }

        mTileTv.setText(title);
    }

    public void setTitleBackgroundColor(@ColorInt int color) {
        if (null == mTileTv) {
            throw new RuntimeException("The \"null == mTileTv\" scenario is theoretically absent~!");
        }

        mTileTv.setBackgroundColor(color);
    }

    public void addOneLinearCard(CardLinearContainer linearCard) {
        addView(linearCard);
    }
}
