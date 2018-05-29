package com.rick.tws.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rick.tws.widget.MetroCard;
import com.rick.tws.widget.R;

public class CellItemHolder extends RecyclerView.ViewHolder {
    public MetroCard card;

    public CellItemHolder(View itemView) {
        super(itemView);
        if (itemView instanceof MetroCard) {
            card = (MetroCard) itemView;
        } else {
            sureCard();
        }
    }

    private void sureCard() {
        card = itemView.findViewById(R.id.metro_card);
    }
}
