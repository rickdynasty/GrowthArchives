package com.rick.tws.Model;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;

public class CellItemStruct {
    private static final String TAG = CellItemStruct.class.getSimpleName();

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

    private String title = "";
    private String icon = "";
    private int card_type = 0;

    private int iconResId = INVALID_VALUE;
    private String shadow_drawable = "";
    private int shadowResId = INVALID_VALUE;

    private int gradientStartColor = INVALID_VALUE;
    private int gradientCenterColor = INVALID_VALUE;
    private int gradientEndColor = INVALID_VALUE;

    //for json config
    private String startColor;
    private String centerColor;
    private String endColor;

    private int action_type = INVALID_VALUE;
    private String action = "";

    private int weight = INVALID_VALUE;

    public CellItemStruct() {
    }

    public CellItemStruct(String title, int iconResId, int shadowResId, int cardType, int startColor,
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
        return "CardStruct info[\\n" + title
                + " \\n" + icon
                + " \\n" + shadow_drawable
                + " \\n" + card_type
                + " \\n" + gradientStartColor
                + " \\n" + gradientCenterColor
                + " \\n" + gradientEndColor
                + " \\n" + action_type
                + " \\n" + action
                + " \\n" + weight + "]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconName() {
        return icon;
    }

    public void setIconName(String iconRes) {
        this.icon = iconRes;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public String getShadowResName() {
        return shadow_drawable;
    }

    public void setShadowResName(String shadowDrawableResName) {
        this.shadow_drawable = shadowDrawableResName;
    }

    public void setShadowResId(int shadowResId) {
        this.shadowResId = shadowResId;
    }

    public int getCardType() {
        return card_type;
    }

    public void setCardType(int cardType) {
        this.card_type = cardType;
    }

    public int getGradientStartColor() {
        return gradientStartColor;
    }

    public void setGradientStartColor(int gradientStartColor) {
        this.gradientStartColor = gradientStartColor;
    }

    public int getGradientCenterColor() {
        return gradientCenterColor;
    }

    public void setGradientCenterColor(int gradientCenterColor) {
        this.gradientCenterColor = gradientCenterColor;
    }

    public int getGradientEndColor() {
        return gradientEndColor;
    }

    public void setGradientEndColor(int gradientEndColor) {
        this.gradientEndColor = gradientEndColor;
    }

    public void setStartColor(String startColor) {
        Log.i(TAG, "call setStartColor");
        this.startColor = startColor;
    }

    public void setCenterColor(String centerColor) {
        Log.i(TAG, "call setCenterColor");
        this.centerColor = centerColor;
    }

    public void setEndColor(String endColor) {
        Log.i(TAG, "call setEndColor");
        this.endColor = endColor;
    }

    public int getActionType() {
        return action_type;
    }

    public void setActionType(int actionType) {
        this.action_type = actionType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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

    public boolean gradientCenterEffective() {
        return INVALID_VALUE != gradientStartColor && INVALID_VALUE != gradientEndColor && INVALID_VALUE != gradientCenterColor;
    }

    public boolean gradientEffective() {
        return INVALID_VALUE != gradientStartColor && INVALID_VALUE != gradientEndColor;
    }

    public boolean bkColorEffective() {
        return INVALID_VALUE != gradientStartColor;
    }

    public boolean weightEffective() {
        return INVALID_VALUE < weight;
    }

    public void checkGradientColor() {
        if (INVALID_VALUE == gradientStartColor && !TextUtils.isEmpty(startColor)) {
            gradientStartColor = Color.parseColor(startColor);
        }

        if (INVALID_VALUE == gradientCenterColor && !TextUtils.isEmpty(centerColor)) {
            gradientCenterColor = Color.parseColor(centerColor);
        }

        if (INVALID_VALUE == gradientEndColor && !TextUtils.isEmpty(endColor)) {
            gradientEndColor = Color.parseColor(endColor);
        }
    }
}
