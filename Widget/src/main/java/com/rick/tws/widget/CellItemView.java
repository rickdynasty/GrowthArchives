package com.rick.tws.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rick.tws.Model.CellItemStruct;

public class CellItemView extends RelativeLayout {
    private static final String TAG = CellItemView.class.getSimpleName();

    private int mCardType = 0;

    private int mActionType = 0;
    private String mAction;

    private ImageView mBackgroundImage;
    private ImageView mIcon = null;
    private TextView mTitle = null;

    private boolean mIsFocus = false;
    private int mTextColor_normal = Color.WHITE;
    private int mTextColor_focus = Color.WHITE;
    private GradientDrawable mGradientDrawable = null;

    public CellItemView(Context context) {
        this(context, null);
    }

    public CellItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CellItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCardType(int cardType) {
        mCardType = cardType;
    }

    /*
     * 高版本api,低版本没有
     */
    public CellItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void sureWidget() {
        if (null == mBackgroundImage) {
            mBackgroundImage = new ImageView(getContext());
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            addView(mBackgroundImage, lp);
        }

        if (null == mIcon) {
            mIcon = new ImageView(getContext());
            final Resources res = getResources();
            LayoutParams lp = new LayoutParams(res.getDimensionPixelSize(R.dimen.cell_item_cion_size), res.getDimensionPixelSize(R.dimen.cell_item_cion_size));
            lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            if (0 == mCardType) {
                lp.topMargin = res.getDimensionPixelSize(R.dimen.cell_item_icon_margin_top_0);
            } else {
                lp.topMargin = res.getDimensionPixelSize(R.dimen.cell_item_icon_margin_top_1);
            }
            addView(mIcon, lp);
        }

        if (null == mTitle) {
            mTitle = new TextView(getContext());
            final Resources res = getResources();
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            if (0 == mCardType) {
                lp.leftMargin = res.getDimensionPixelSize(R.dimen.cell_item_title_margin_left_0);
                lp.topMargin = res.getDimensionPixelSize(R.dimen.cell_item_title_margin_top_0);
            } else {
                lp.bottomMargin = res.getDimensionPixelSize(R.dimen.cell_item_title_margin_bottom_1);
                lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            }
            addView(mTitle, lp);
        }
    }

    public void setCardContent(CharSequence title, final int iconRes, final int shadowRes) {
        sureWidget();
        Log.i(TAG, "title=" + title);
        setBackgroundResource(shadowRes);
        mTitle.setText(title);
        mIcon.setImageResource(iconRes);
        mBackgroundImage.setVisibility(View.GONE);
    }

    public void setCardContent(CharSequence title, final int iconRes, final int shadowRes, final int bgRes, final boolean isDrawalbeBgRes) {
        sureWidget();
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

    public void setCardContent(int cardType, CharSequence title, final int iconRes, final int shadowRes, @ColorInt int[] bgGradientColors, int actionType, String action) {
        sureWidget();
        Log.i(TAG, "title=" + title);

        mCardType = cardType;

        setBackgroundResource(shadowRes);
        mTitle.setText(title);
        mIcon.setImageResource(iconRes);
        setCardGradientColor(bgGradientColors);

        mActionType = actionType;
        mAction = action;
    }

    public void setCardContent(int cardType, String title, String iconName, String shadowResName, int[] colors, int actionType, String action) {
        sureWidget();
        Log.i(TAG, "title=" + title);

        mCardType = cardType;

        setBackgroundResource(getResources().getIdentifier(shadowResName, "drawable", getContext().getApplicationInfo().packageName));
        mTitle.setText(title);
        mIcon.setImageResource(getResources().getIdentifier(iconName, "drawable", getContext().getApplicationInfo().packageName));
        if (null != colors) {
            setCardGradientColor(colors);
        }
        mActionType = actionType;
        mAction = action;
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

    public CharSequence getTitle() {
        if (null == mTitle) {
            throw new RuntimeException("The \"null == mTileTv\" scenario is theoretically absent~!");
        }

        return mTitle.getText();
    }

    public int getActionType() {
        return mActionType;
    }

    public String getAction() {
        return mAction;
    }

    public void init(CellItemStruct cardStruct) {
        if (null == cardStruct) {
            throw new IllegalArgumentException("cardStruct cannot be empty!");
        }

        sureWidget();
        Log.i(TAG, "title=" + cardStruct.getTitle());

        mCardType = cardStruct.getCardType();

        if (!TextUtils.isEmpty(cardStruct.getShadowResName())) {
            setBackgroundResource(getResources().getIdentifier(cardStruct.getShadowResName(), "drawable", getContext().getApplicationInfo().packageName));
        }

        mTitle.setText(cardStruct.getTitle());
        if (!TextUtils.isEmpty(cardStruct.getIconName())) {
            mIcon.setImageResource(getResources().getIdentifier(cardStruct.getIconName(), "drawable", getContext().getApplicationInfo().packageName));
        }

        cardStruct.checkGradientColor();
        int[] colors = null;
        if (cardStruct.gradientCenterEffective()) {
            colors = new int[]{cardStruct.getGradientStartColor(), cardStruct.getGradientCenterColor(), cardStruct.getGradientEndColor()};
        } else if (cardStruct.gradientEffective()) {
            colors = new int[]{cardStruct.getGradientStartColor(), cardStruct.getGradientEndColor()};
        } else if (cardStruct.bkColorEffective()) {
            colors = new int[]{cardStruct.getGradientStartColor()};
        }

        if (null != colors) {
            setCardGradientColor(colors);
        }

        mActionType = cardStruct.getActionType();
        mAction = cardStruct.getAction();
    }
}
