package com.rick.tws.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.rick.tws.Model.CellLayoutAdapter;
import com.rick.tws.Model.WorkspaceGroupContent;
import com.rick.tws.Model.WorkspaceSpanSizeLookup;
import com.rick.tws.Presenter.WrokspaceJsonPresenter;
import com.rick.tws.View.IWorkspaceUI;

import java.util.ArrayList;

public class Workspace extends RecyclerView implements IWorkspaceUI {

    private static final String TAG = Workspace.class.getSimpleName();
    protected Context mContext;

    //列【即一行显示的个数】
    public static final int GRID_SPANCOUNT = 3;
    // 默认收起显示的行数
    public static final int GRID_GROUP_OFF_MULTIPLE_SPANCOUNT = 1;


    private WrokspaceJsonPresenter mPresenter;
    private CellLayoutAdapter mAdapter;

    public Workspace(Context context) {
        this(context, null);
    }

    public Workspace(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Workspace(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;

        mPresenter = new WrokspaceJsonPresenter(this);
        mAdapter = new CellLayoutAdapter(context);
    }

    @Override
    public void initWorkspace(ArrayList<WorkspaceGroupContent> groupDataList) {
        mAdapter.setData(groupDataList);
    }

    @Override
    public void loadJsonfailure(String errorDes, Exception e) {
        Log.e(TAG, errorDes, e);
    }

    public void initData() {
        GridLayoutManager manager = new GridLayoutManager(mContext, GRID_SPANCOUNT);
        //设置header
        manager.setSpanSizeLookup(new WorkspaceSpanSizeLookup(mAdapter, manager));

        setLayoutManager(manager);
        setAdapter(mAdapter);

        mPresenter.loadJsonFromAssets(mContext,"workspace.json");
    }
}
