package com.rick.tws.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rick.tws.Model.CellLayoutAdapter;
import com.rick.tws.Model.WorkspaceData;
import com.rick.tws.Model.WorkspaceSpanSizeLookup;
import com.rick.tws.Presenter.WrokspaceJsonPresenter;
import com.rick.tws.widget.CellItemView;
import com.rick.tws.widget.R;
import com.rick.tws.widget.Workspace;

public class WorkspaceActivity extends Activity implements IWorkspaceUI, View.OnClickListener {
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
    public void initWorkspace(WorkspaceData result) {
        if (result.getNeedDivider()) {
            mWorkspace.addItemDecoration(new DividerDecoration(this, Workspace.GRID_SPANCOUNT));
        }

        mAdapter.setData(result.workspaceGroups);
    }

    @Override
    public void loadJsonfailure(String errorDes, Exception e) {
        Log.e(TAG, errorDes, e);
        //失败了,就选择加载默认数据
        mPresenter.loadDefaultData();
    }

    @Override
    public void onClick(View v) {
        if (v instanceof CellItemView) {
            Toast.makeText(this, "点击了：" + ((CellItemView) v).getTitle(), Toast.LENGTH_SHORT).show();
        }
    }
}
