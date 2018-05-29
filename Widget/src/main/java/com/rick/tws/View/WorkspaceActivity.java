package com.rick.tws.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.rick.tws.Model.CellLayoutAdapter;
import com.rick.tws.Model.WorkspaceGroupContent;
import com.rick.tws.Model.WorkspaceSpanSizeLookup;
import com.rick.tws.Presenter.WrokspaceJsonPresenter;
import com.rick.tws.widget.R;
import com.rick.tws.widget.Workspace;

import java.util.ArrayList;

public class WorkspaceActivity extends Activity implements IWorkspaceUI {
    private static final String TAG = WorkspaceActivity.class.getSimpleName();
    private WrokspaceJsonPresenter mPresenter;
    private CellLayoutAdapter mAdapter;
    private Workspace mWorkspace;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_workspace);
        initUI();
    }

    private void initUI() {
        mWorkspace = findViewById(R.id.navigation_area);
        mPresenter = new WrokspaceJsonPresenter(this);
        mAdapter = new CellLayoutAdapter(this);

        GridLayoutManager manager = new GridLayoutManager(this, Workspace.GRID_SPANCOUNT);
        //设置header
        manager.setSpanSizeLookup(new WorkspaceSpanSizeLookup(mAdapter, manager));

        mWorkspace.setLayoutManager(manager);
        mWorkspace.setAdapter(mAdapter);

        mPresenter.loadJsonFromAssets(this, "workspace.json");
    }

    @Override
    public void initWorkspace(ArrayList<WorkspaceGroupContent> groupDataList) {
        mAdapter.setData(groupDataList);
    }

    @Override
    public void loadJsonfailure(String errorDes, Exception e) {
        Log.e(TAG, errorDes, e);
        //失败了,就选择加载默认数据
        mPresenter.loadDefaultData();
    }
}
