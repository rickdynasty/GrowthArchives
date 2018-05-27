package com.rick.tws.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rick.tws.widget.R;

public class NavigationHeaderHolder extends RecyclerView.ViewHolder {
    public TextView titleView;
    public TextView openView;

    public NavigationHeaderHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        titleView = itemView.findViewById(R.id.header_title);
        openView = itemView.findViewById(R.id.header_switch);
    }
}
