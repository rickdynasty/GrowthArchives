package com.rick.tws.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rick.tws.widget.CellItemHolder;
import com.rick.tws.widget.HeaderHolder;
import com.rick.tws.widget.R;
import com.rick.tws.widget.Workspace;

import java.util.ArrayList;
import java.util.List;

public class CellLayoutAdapter extends BaseAdapter<HeaderHolder, CellItemHolder, RecyclerView.ViewHolder> {
    public ArrayList<WorkspaceGroupContent> groupDataList;
    private String mSwith_off = "收起";
    private String mSwith_on = "展开";

    private Context mContext;
    private LayoutInflater mInflater;
    // 是否是收起的状态【默认是展开的】
    private SparseBooleanArray mCollapseStateMap;

    public CellLayoutAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mCollapseStateMap = new SparseBooleanArray();
    }

    public void setData(ArrayList<WorkspaceGroupContent> groupDataList) {
        this.groupDataList = groupDataList;
        notifyDataSetChanged();
    }

    @Override
    protected int getSectionCount() {
        return isEmpty(groupDataList) ? 0 : groupDataList.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = groupDataList.get(section).cellItemList.size();

        //判断是否是收起状态，并且当前count超过了收起状态的显示个数
        if (mCollapseStateMap.get(section) && count >= Workspace.GRID_SPANCOUNT * Workspace.GRID_GROUP_OFF_MULTIPLE_SPANCOUNT) {
            count = Workspace.GRID_SPANCOUNT * Workspace.GRID_GROUP_OFF_MULTIPLE_SPANCOUNT;
        }

        return isEmpty(groupDataList.get(section).cellItemList) ? 0 : count;
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected HeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new HeaderHolder(mInflater.inflate(R.layout.workspace_header_item, parent, false));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected CellItemHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new CellItemHolder(mInflater.inflate(R.layout.workspace_cell_item, parent, false));
    }

    @Override
    protected void onBindSectionHeaderViewHolder(final HeaderHolder holder, final int section) {
        holder.openView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isCollapse = mCollapseStateMap.get(section);
                String text = isCollapse ? mSwith_off : mSwith_on;
                mCollapseStateMap.put(section, !isCollapse);
                holder.openView.setText(text);
                notifyDataSetChanged();
            }
        });

        holder.titleView.setText(groupDataList.get(section).getName());
        if (Workspace.GRID_SPANCOUNT * Workspace.GRID_GROUP_OFF_MULTIPLE_SPANCOUNT < groupDataList.get(section).cellItemList.size()) {
            holder.openView.setVisibility(View.VISIBLE);
            holder.openView.setText(mCollapseStateMap.get(section) ? mSwith_on : mSwith_off);
        } else {
            holder.openView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(CellItemHolder holder, int section, int position) {
        holder.card.init(groupDataList.get(section).cellItemList.get(position));
        if(mContext instanceof View.OnClickListener){
            holder.card.setOnClickListener((View.OnClickListener) mContext);
        }
    }

    private static <D> boolean isEmpty(List<D> list) {
        return list == null || list.isEmpty();
    }
}
