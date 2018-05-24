package com.rick.tws.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MetroCard extends RelativeLayout {
    private static final String TAG = "MetroCard";
    private ImageView mBackgroundImage;
    private ImageView mIcon = null;
    private TextView mTitle = null;

    private boolean mIsFocus = false;
    private int mTextColor_normal = Color.WHITE;
    private int mTextColor_focus = Color.WHITE;
    private GradientDrawable mGradientDrawable = null;

    public MetroCard(Context context) {
        this(context, null);
    }

    public MetroCard(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MetroCard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public MetroCard(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        View view = LayoutInflater.from(context).inflate(R.layout.metrocard_item, this, true);
        mBackgroundImage = view.findViewById(R.id.background_image);
        mIcon = view.findViewById(R.id.card_icon);
        mTitle = view.findViewById(R.id.card_title);
    }

    public void setCardContent(CharSequence title, final int iconRes, final int shadowRes) {
        Log.i(TAG, "title=" + title);
        setBackgroundResource(shadowRes);
        mTitle.setText(title);
        mIcon.setImageResource(iconRes);
        mBackgroundImage.setVisibility(View.GONE);
    }

    public void setCardContent(CharSequence title, final int iconRes, final int shadowRes, final int bgRes, final boolean isDrawalbeBgRes) {
        Log.i(TAG, "title=" + title);
        setBackgroundResource(shadowRes);
        mTitle.setText(title);
        mIcon.setImageResource(iconRes);
        if (isDrawalbeBgRes) {
            mBackgroundImage.setImageResource(bgRes);
        } else {
            //mBackgroundImage.setImageDrawable(new ColorDrawable(bgRes));
        }
    }

    public void setCardContent(CharSequence title, final int iconRes, final int shadowRes, @ColorInt int[] bgGradientColors) {
        Log.i(TAG, "title=" + title);
        setBackgroundResource(shadowRes);
        mTitle.setText(title);
        mIcon.setImageResource(iconRes);
        setCardGradientColor(bgGradientColors);
    }

    public void setCardGradientColor(final int[] colors) {
        setCardGradientColor(GradientDrawable.Orientation.TR_BL, colors);
    }

    public void setCardGradientColor(GradientDrawable.Orientation orientation, @ColorInt int[] colors) {
        if (null == mGradientDrawable) {
            mGradientDrawable = new GradientDrawable();
            mGradientDrawable.setShape(GradientDrawable.RECTANGLE);
        }

        mGradientDrawable.setOrientation(orientation);
        mGradientDrawable.setColors(colors);

        mBackgroundImage.setImageDrawable(mGradientDrawable);
    }

    public void setTextColorNormal(int color) {
        mTextColor_normal = color;
        if (!mIsFocus) {
            mTitle.setTextColor(mTextColor_normal);
        }
    }

    public void setTextColorFocus(int color) {
        mTextColor_focus = color;
        if (mIsFocus) {
            mTitle.setTextColor(mTextColor_focus);
        }
    }

    public void setFocus(boolean focus) {
        if (mIsFocus == focus)
            return;

        mIsFocus = focus;

        if (mIsFocus) {
            mTitle.setTextColor(mTextColor_focus);
        } else {
            mTitle.setTextColor(mTextColor_normal);
        }
    }

    public CharSequence getTitle(){
        if (null == mTitle) {
            throw new RuntimeException("The \"null == mTileTv\" scenario is theoretically absent~!");
        }

        return mTitle.getText();
    }
}
