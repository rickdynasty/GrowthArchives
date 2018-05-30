package com.rick.tws.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rick.tws.Model.CellItemStruct;

public class CellItemView extends RelativeLayout {
    private static final String TAG = CellItemView.class.getSimpleName();

    // 卡片类型 0：默认是标题在上方 1、标题在下放 2、自定义卡片
    public static final int TITLE_ON_CARD_TYPE = 0;     //标题在上
    public static final int TITLE_DOWN_CARD_TYPE = 1;   //标题在下
    //    public static final int CUSTOMIZED_CARD_TYPE = 2;   //自定义
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

    private boolean hasInit = false;

    public void init(CellItemStruct cardStruct) {
        if (hasInit)
            return;

        ViewGroup.LayoutParams lp = getLayoutParams();
        lp.width = cardStruct.item_width;
        lp.height = cardStruct.item_height;
        setLayoutParams(lp);

        if (null == cardStruct) {
            throw new IllegalArgumentException("cardStruct cannot be empty!");
        }

        mCardType = cardStruct.getCardType();
        initWidget(cardStruct);

        mActionType = cardStruct.getActionType();
        mAction = cardStruct.getAction();
        hasInit = true;
    }

    private void initWidget(final CellItemStruct cardStruct) {
        //处理渐变背景
        int[] colors;
        if (cardStruct.gradientCenterEffective()) {
            colors = new int[]{cardStruct.getGradientStartColor(), cardStruct.getGradientCenterColor(), cardStruct.getGradientEndColor()};
        } else if (cardStruct.gradientEffective()) {
            colors = new int[]{cardStruct.getGradientStartColor(), cardStruct.getGradientEndColor()};
        } else {
            colors = null;
        }

        //优先使用渐变
        if (null != colors) {
            // 处理 shadowImageView mBackgroundImage
            if (null == mBackgroundImage) {
                mBackgroundImage = new ImageView(getContext());
                LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                addView(mBackgroundImage, lp);
            }

            mBackgroundImage.setVisibility(VISIBLE);
            setCardGradientColor(colors);

            if (!TextUtils.isEmpty(cardStruct.getShadowResName())) {
                setBackgroundResource(getResources().getIdentifier(cardStruct.getShadowResName(), "drawable", getContext().getApplicationInfo().packageName));
            }
        } else {
            if (null != mBackgroundImage) {
                mBackgroundImage.setVisibility(GONE);
            }

            //如果没使用渐变，就采用背景色
            if (cardStruct.bkColorEffective()) {
                setBackgroundColor(cardStruct.getBackgroundColor());
            }
        }

        if (null == mIcon) {
            mIcon = new ImageView(getContext());
            final Resources res = getResources();
            LayoutParams lp = new LayoutParams(cardStruct.icon_width, cardStruct.icon_height);
            lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            switch (mCardType) {
                case TITLE_ON_CARD_TYPE:
                    lp.topMargin = (cardStruct.item_height - cardStruct.icon_height) / 2 + res.getDimensionPixelSize(R.dimen.cell_item_title_margin_top_0);
                    break;
                case TITLE_DOWN_CARD_TYPE:
                    lp.topMargin = (cardStruct.item_height - cardStruct.icon_height) / 2 - res.getDimensionPixelSize(R.dimen.cell_item_title_margin_top_0);
                    break;
            }

            addView(mIcon, lp);
        }

        if (null == mTitle) {
            mTitle = new TextView(getContext());
            final Resources res = getResources();
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            switch (mCardType) {
                case TITLE_ON_CARD_TYPE:
                    lp.leftMargin = res.getDimensionPixelSize(R.dimen.cell_item_title_margin_left_0);
                    lp.topMargin = res.getDimensionPixelSize(R.dimen.cell_item_title_margin_top_0);
                    break;
                case TITLE_DOWN_CARD_TYPE:
                    lp.bottomMargin = res.getDimensionPixelSize(R.dimen.cell_item_title_margin_bottom_1);
                    lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    break;
            }

            addView(mTitle, lp);
        }

        mTitle.setText(cardStruct.getTitle());
        if (!TextUtils.isEmpty(cardStruct.getIconName())) {
            mIcon.setImageResource(getResources().getIdentifier(cardStruct.getIconName(), "drawable", getContext().getApplicationInfo().packageName));
        }
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
}
