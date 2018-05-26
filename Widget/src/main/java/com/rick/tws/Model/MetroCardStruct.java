package com.rick.tws.Model;

import android.graphics.Color;

public class MetroCardStruct {

    public static final String METRO_CARDS_PREFIX = "cards";

    public static final String CARD_TITLE = "title";
    public static final String CARD_ICON = "icon";
    public static final String CARD_SHADOW_DRAWABLE = "shadow_drawable";
    public static final String CARD_TYPE = "card_type";
    public static final String GRADIENT_START_COLOR = "startColor";
    public static final String GRADIENT_CENTER_COLOR = "centerColor";
    public static final String GRADIENT_END_COLOR = "endColor";
    public static final String CARD_ACTION_TYPE = "action_type";
    public static final String CARD_ACTION = "action";
    public static final String CARD_WEIGHT = "weight";

    public static final int INVALID_VALUE = -1;

    private String mTitle = "";
    private String mIcon = "";
    private int mIconResId = INVALID_VALUE;
    private String mShadowResName = "";
    private int mShadowResId = INVALID_VALUE;
    private int mCardType = 0;
    private int mGradientStartColor = INVALID_VALUE;
    private int mGradientCenterColor = INVALID_VALUE;
    private int mGradientEndColor = INVALID_VALUE;
    private int mActionType = INVALID_VALUE;
    private String mAction = "";
    private int mWeight = INVALID_VALUE;

    public MetroCardStruct() {

    }

    public MetroCardStruct(String title, int iconResId, int shadowResId, int cardType, int startColor,
                           int centerColor, int endColor, int actionType, String action, int weight) {
        setTitle(title);
        setIconResId(iconResId);
        setShadowResId(shadowResId);
        setCardType(cardType);
        setGradientStartColor(startColor);
        setGradientCenterColor(centerColor);
        setGradientEndColor(endColor);
        setActionType(actionType);
        setAction(action);
        setWeight(weight);
    }

    @Override
    public String toString() {
        return "CardStruct info[\\n" + mTitle
                + " \\n" + mIcon
                + " \\n" + mShadowResName
                + " \\n" + mCardType
                + " \\n" + mGradientStartColor
                + " \\n" + mGradientCenterColor
                + " \\n" + mGradientEndColor
                + " \\n" + mActionType
                + " \\n" + mAction
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

    public void setIconResId(int iconResId) {
        this.mIconResId = iconResId;
    }

    public String getShadowResName() {
        return mShadowResName;
    }

    public void setShadowResName(String shadowDrawableResName) {
        this.mShadowResName = shadowDrawableResName;
    }

    public void setShadowResId(int shadowResId) {
        this.mShadowResId = shadowResId;
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

    public int getActionType() {
        return mActionType;
    }

    public void setActionType(int actionType) {
        this.mActionType = actionType;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String action) {
        this.mAction = action;
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
            case CARD_ACTION_TYPE:
                setActionType(Integer.parseInt(value));
                break;
            case CARD_ACTION:
                setAction(value);
                break;
            case CARD_WEIGHT:
                setWeight(Integer.parseInt(value));
                break;
        }
    }

    public boolean gradientEffective() {
        return INVALID_VALUE != mGradientStartColor && INVALID_VALUE != mGradientEndColor;
    }

    public boolean gradientCenterEffective() {
        return INVALID_VALUE != mGradientStartColor && INVALID_VALUE != mGradientEndColor && INVALID_VALUE != mGradientCenterColor;
    }

    public boolean weightEffective() {
        return INVALID_VALUE < mWeight;
    }
}
