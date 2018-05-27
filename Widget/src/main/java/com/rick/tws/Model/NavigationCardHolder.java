package com.rick.tws.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rick.tws.widget.MetroCard;
import com.rick.tws.widget.R;

public class NavigationCardHolder extends RecyclerView.ViewHolder {
    public MetroCard card;

    public NavigationCardHolder(View itemView) {
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
