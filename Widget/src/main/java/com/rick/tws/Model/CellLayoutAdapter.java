package com.rick.tws.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rick.tws.widget.CellItemHolder;
import com.rick.tws.widget.HeaderHolder;
import com.rick.tws.widget.Workspace;
import com.rick.tws.widget.R;

import java.util.ArrayList;
import java.util.List;

public class CellLayoutAdapter extends BaseAdapter<HeaderHolder, CellItemHolder, RecyclerView.ViewHolder> {
    public ArrayList<WorkspaceContent> groupDataList;
    private String mSwith_off = "收起";
    private String mSwith_on = "展开";

    private Context mContext;
    private LayoutInflater mInflater;
    private SparseBooleanArray mBooleanMap;

    public CellLayoutAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBooleanMap = new SparseBooleanArray();
    }

    public void setData(ArrayList<WorkspaceContent> groupDataList) {
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
        if (!mBooleanMap.get(section) && count >= Workspace.GRID_SPANCOUNT * Workspace.GRID_GROUP_OFF_MULTIPLE_SPANCOUNT) {
            count = Workspace.GRID_SPANCOUNT * Workspace.GRID_GROUP_OFF_MULTIPLE_SPANCOUNT;
        }

        return isEmpty(groupDataList.get(section).cardList) ? 0 : count;
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected HeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new HeaderHolder(mInflater.inflate(R.layout.navigation_header_item, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected CellItemHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new CellItemHolder(mInflater.inflate(R.layout.navigation_card_item, parent, false));
    }

    @Override
    protected void onBindSectionHeaderViewHolder(final HeaderHolder holder, final int section) {
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
    protected void onBindItemViewHolder(CellItemHolder holder, int section, int position) {
        holder.card.init(groupDataList.get(section).cardList.get(position));
    }

    private static <D> boolean isEmpty(List<D> list) {
        return list == null || list.isEmpty();
    }
}
