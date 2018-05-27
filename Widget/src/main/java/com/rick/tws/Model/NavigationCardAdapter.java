package com.rick.tws.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rick.tws.widget.NavigationUI;
import com.rick.tws.widget.R;

import java.util.ArrayList;
import java.util.List;

public class NavigationCardAdapter extends NavigationCardBaseAdapter<NavigationHeaderHolder, NavigationCardHolder, RecyclerView.ViewHolder> {
    public ArrayList<NavigationGroupData> groupDataList;
    private String mSwith_off = "收起";
    private String mSwith_on = "展开";

    private Context mContext;
    private LayoutInflater mInflater;
    private SparseBooleanArray mBooleanMap;

    public NavigationCardAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBooleanMap = new SparseBooleanArray();
    }

    public void setData(ArrayList<NavigationGroupData> groupDataList) {
        this.groupDataList = groupDataList;
        notifyDataSetChanged();
    }

    @Override
    protected int getSectionCount() {
        return isEmpty(groupDataList) ? 0 : groupDataList.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = groupDataList.get(section).cardList.size();
        if (!mBooleanMap.get(section) && count >= NavigationUI.GRID_SPANCOUNT * NavigationUI.GRID_GROUP_OFF_MULTIPLE_SPANCOUNT) {
            count = NavigationUI.GRID_SPANCOUNT * NavigationUI.GRID_GROUP_OFF_MULTIPLE_SPANCOUNT;
        }

        return isEmpty(groupDataList.get(section).cardList) ? 0 : count;
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected NavigationHeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new NavigationHeaderHolder(mInflater.inflate(R.layout.navigation_header_item, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected NavigationCardHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new NavigationCardHolder(mInflater.inflate(R.layout.navigation_card_item, parent, false));
    }

    @Override
    protected void onBindSectionHeaderViewHolder(final NavigationHeaderHolder holder, final int section) {
        holder.openView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpen = mBooleanMap.get(section);
                String text = isOpen ? mSwith_on : mSwith_off;
                mBooleanMap.put(section, !isOpen);
                holder.openView.setText(text);
                notifyDataSetChanged();
            }
        });

        holder.titleView.setText(groupDataList.get(section).getName());
        holder.openView.setText(mBooleanMap.get(section) ? mSwith_off : mSwith_on);
    }

    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(NavigationCardHolder holder, int section, int position) {
        holder.card.init(groupDataList.get(section).cardList.get(position));
    }

    private static <D> boolean isEmpty(List<D> list) {
        return list == null || list.isEmpty();
    }
}
