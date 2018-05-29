package com.rick.tws.Model;

import android.support.v7.widget.GridLayoutManager;

public class WorkspaceSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    protected BaseAdapter<?, ?, ?> adapter;
    protected GridLayoutManager layoutManager;

    public WorkspaceSpanSizeLookup(BaseAdapter<?, ?, ?> adapter, GridLayoutManager layoutManager) {
        this.adapter = adapter;
        this.layoutManager = layoutManager;
    }

    @Override
    public int getSpanSize(int position) {
        if (adapter.isSectionHeaderPosition(position) || adapter.isSectionFooterPosition(position)) {
            return layoutManager.getSpanCount();
        } else {
            return 1;
        }
    }
}
