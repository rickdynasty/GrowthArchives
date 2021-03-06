package com.rick.tws.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HeaderHolder extends RecyclerView.ViewHolder {
    public TextView titleView;
    public TextView openView;
    public ImageView groupIcon;

    public HeaderHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        titleView = itemView.findViewById(R.id.header_title);
        openView = itemView.findViewById(R.id.header_switch);
        groupIcon = itemView.findViewById(R.id.g_icon);
    }
}
