package com.rick.tws.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.rick.tws.Model.MetroCardStruct;
import com.rick.tws.Model.MetroLinearStruct;
import com.rick.tws.util.DensityUtils;

public class CardLinearContainer extends LinearLayout {
    private final int mDefaultHeight;
    private Context mContext;

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
        mContext = context;

        mDefaultHeight = getResources().getDimensionPixelSize(R.dimen.card_linear_container_height);
        //ver1.0只支持HORIZONTAL 横向
        setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, mDefaultHeight);
        setLayoutParams(lp);
    }

    public void init(MetroLinearStruct linearStruct) {
        if (null == linearStruct) {
            throw new IllegalArgumentException("groupStruct cannot be empty!");
        }

        //如果设置了行高，在这里处理
        if (0 < linearStruct.getHeight()) {
            final int height = DensityUtils.dip2px(mContext, linearStruct.getHeight());
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, height);
            setLayoutParams(lp);
        }

        //添加所以卡片Item
        for (MetroCardStruct cardStruct : linearStruct.cards) {
            addOneCard(MetroCardFactory.createMetroCard(mContext, cardStruct), cardStruct.weightEffective() ? cardStruct.getWeight() : 1.0f);
        }
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
