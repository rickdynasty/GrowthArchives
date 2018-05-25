package com.rick.tws.widget;

import android.graphics.Color;

public class MetroCardStruct {
    public static final String CARD_TITLE = "title";
    public static final String CARD_ICON = "icon";
    public static final String CARD_SHADOW_DRAWABLE = "shadow_drawable";
    public static final String CARD_TYPE = "card_type";
    public static final String GRADIENT_START_COLOR = "startColor";
    public static final String GRADIENT_CENTER_COLOR = "centerColor";
    public static final String GRADIENT_END_COLOR = "endColor";
    public static final String CARD_CONTENT_TYPE = "content_type";
    public static final String CARD_CONTENT = "content";
    public static final String CARD_WEIGHT = "weight";

    private String mTitle = "";
    private String mIcon = "";
    private String mShadowResName = "";
    private int mCardType = 0;
    private int mGradientStartColor = -1;
    private int mGradientCenterColor = -1;
    private int mGradientEndColor = -1;
    private int mCardContentType = -1;
    private String mCardContent = "";
    private int mWeight = -1;

    @Override
    public String toString() {
        return "CardStruct info[\\n" + mTitle
                + " \\n" + mIcon
                + " \\n" + mShadowResName
                + " \\n" + mCardType
                + " \\n" + mGradientStartColor
                + " \\n" + mGradientCenterColor
                + " \\n" + mGradientEndColor
                + " \\n" + mCardContentType
                + " \\n" + mCardContent
                + " \\n" + mWeight + "]";
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getIconName() {
        return mIcon;
    }

    public void setIconName(String iconRes) {
        this.mIcon = iconRes;
    }

    public String getShadowResName() {
        return mShadowResName;
    }

    public void setShadowResName(String shadowDrawableResName) {
        this.mShadowResName = shadowDrawableResName;
    }

    public int getCardType() {
        return mCardType;
    }

    public void setCardType(int cardType) {
        this.mCardType = cardType;
    }

    public int getGradientStartColor() {
        return mGradientStartColor;
    }

    public void setGradientStartColor(int gradientStartColor) {
        this.mGradientStartColor = gradientStartColor;
    }

    public int getGradientCenterColor() {
        return mGradientCenterColor;
    }

    public void setGradientCenterColor(int gradientCenterColor) {
        this.mGradientCenterColor = gradientCenterColor;
    }

    public int getGradientEndColor() {
        return mGradientEndColor;
    }

    public void setGradientEndColor(int gradientEndColor) {
        this.mGradientEndColor = gradientEndColor;
    }

    public int getCardContentType() {
        return mCardContentType;
    }

    public void setCardContentType(int cardContentType) {
        this.mCardContentType = cardContentType;
    }

    public String getCardContent() {
        return mCardContent;
    }

    public void setCardContent(String cardContent) {
        this.mCardContent = cardContent;
    }

    public int getWeight() {
        return mWeight;
    }

    public void setWeight(int weight) {
        this.mWeight = weight;
    }

    public void setContent(String key, String value) {
        switch (key) {
            case CARD_TITLE:
                setTitle(value);
                break;
            case CARD_ICON:
                setIconName(value);
                break;
            case CARD_SHADOW_DRAWABLE:
                setShadowResName(value);
                break;
            case CARD_TYPE:
                setCardType(Integer.parseInt(value));
                break;
            case GRADIENT_START_COLOR:
                setGradientStartColor(Color.parseColor(value.toLowerCase()));
                break;
            case GRADIENT_CENTER_COLOR:
                setGradientCenterColor(Color.parseColor(value.toLowerCase()));
                break;
            case GRADIENT_END_COLOR:
                setGradientEndColor(Color.parseColor(value.toLowerCase()));
                break;
            case CARD_CONTENT_TYPE:
                setCardContentType(Integer.parseInt(value));
                break;
            case CARD_CONTENT:
                setCardContent(value);
                break;
            case CARD_WEIGHT:
                setWeight(Integer.parseInt(value));
                break;
        }
    }

    public boolean gradientEffective() {
        return -1 != mGradientStartColor && -1 != mGradientEndColor;
    }

    public boolean gradientCenterEffective() {
        return -1 != mGradientStartColor && -1 != mGradientEndColor && -1 != mGradientCenterColor;
    }

    public boolean weightEffective() {
        return -1 < mWeight;
    }
}
