package com.rick.tws.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class CardLinearContainer extends LinearLayout {
    public CardLinearContainer(Context context) {
        this(context, null);
    }

    public CardLinearContainer(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardLinearContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CardLinearContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        //ver1.0只支持HORIZONTAL 横向
        setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.card_linear_container_height));
        setLayoutParams(lp);
    }

    public void addOneCard(MetroCard card) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);

        card.setLayoutParams(lp);
        addView(card);
    }

    public void addOneCard(MetroCard card, float weight) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, weight);

        card.setLayoutParams(lp);
        addView(card);
    }
}
