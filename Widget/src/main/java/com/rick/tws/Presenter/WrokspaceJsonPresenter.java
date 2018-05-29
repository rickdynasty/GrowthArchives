package com.rick.tws.Presenter;

import android.content.Context;

import com.rick.tws.Model.BaseJsonModel;
import com.rick.tws.Model.WorkspaceData;
import com.rick.tws.Model.WorkspaceModel;
import com.rick.tws.View.IWorkspaceUI;

public class WrokspaceJsonPresenter implements BasePresenter, BaseJsonModel.NavigationLoadJsonCallback {

    private final IWorkspaceUI mView;
    private final WorkspaceModel mModel;

    public WrokspaceJsonPresenter(IWorkspaceUI view) {
        if (null == view) {
            throw new RuntimeException("The \"null == mView\" scenario is theoretically absent~!");
        }

        mView = view;
        mModel = new WorkspaceModel();
    }

    @Override
    public void loadJsonFromAssets(Context context, String jsonFileName) {
        mModel.loadJsonFromAssets(context, jsonFileName, this);
    }

    @Override
    public void loadJson(String jsonFilePath) {
        mModel.loadJsonData(jsonFilePath, this);
    }

    @Override
    public void success(WorkspaceData result) {
        if (null == mView) {
            throw new RuntimeException("The \"null == mView\" scenario is theoretically absent~!");
        }

        mView.initWorkspace(result.groupDataList);
    }

    @Override
    public void failure(String errorDes, Exception e) {
        if (null == mView) {
            throw new RuntimeException("The \"null == mView\" scenario is theoretically absent~!");
        }

        mView.loadJsonfailure(errorDes, e);
    }

    @Override
    public void loadDefaultData() {
    }
}
