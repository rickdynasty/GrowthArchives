package com.rick.tws.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.rick.tws.Model.NavigationCardAdapter;
import com.rick.tws.Model.NavigationJsonData;
import com.rick.tws.Model.NavigationJsonModel;
import com.rick.tws.Model.NavigationLoadJsonCallback;
import com.rick.tws.Model.NavigationSpanSizeLookup;
import com.rick.tws.widget.NavigationUI;
import com.rick.tws.widget.R;

public class SecondActivity extends Activity implements NavigationLoadJsonCallback {
    private static final String TAG = "rick_Print:SecondActivity";
    private NavigationUI mNavigationUI;
    private NavigationCardAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        initView();
        NavigationJsonModel model = new NavigationJsonModel();
        model.loadJsonFromAssets(this, "navigation.json", this);
    }

    private void initView() {
        mNavigationUI = findViewById(R.id.navigation_area);
        mAdapter = new NavigationCardAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this, NavigationUI.GRID_SPANCOUNT);
        //设置header
        manager.setSpanSizeLookup(new NavigationSpanSizeLookup(mAdapter,manager));

        mNavigationUI.setLayoutManager(manager);
        mNavigationUI.setAdapter(mAdapter);
    }

    @Override
    public void success(NavigationJsonData result) {
        mAdapter.setData(result.groupDataList);
    }

    @Override
    public void failure(String errorDes, Exception e) {
        Log.e(TAG, errorDes, e);
    }
}
