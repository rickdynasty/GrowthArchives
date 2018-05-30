package com.rick.tws.Model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rick.tws.TheApplication;
import com.rick.tws.util.FileUtils;

import java.io.IOException;

public class WorkspaceModel implements BaseJsonModel {
    private static final String TAG = WorkspaceModel.class.getSimpleName();

    @Override
    public void loadJsonData(String jsonFilePath, BaseJsonModel.NavigationLoadJsonCallback callback) {
        try {
            String content = FileUtils.getJsonFromFile(jsonFilePath);
            Gson gson = new Gson();
            WorkspaceData entity = gson.fromJson(content, WorkspaceData.class);
            callback.success(entity);
        } catch (IOException e) {
            callback.failure("loadJsonData:" + jsonFilePath, e);
        } catch (JsonSyntaxException e) {
            callback.failure("loadJsonData:" + jsonFilePath, e);
        }
    }

    @Override
    public void loadJsonFromAssets(Context context, String jsonFileName, BaseJsonModel.NavigationLoadJsonCallback callback) {
        try {
            String content = FileUtils.getJsonFromAssets(context, jsonFileName);
            Gson gson = new Gson();
            WorkspaceData workspaceData = gson.fromJson(content, WorkspaceData.class);
            if (null != workspaceData) {
                workspaceData.processing(TheApplication.getInstance());
            }
            callback.success(workspaceData);
        } catch (IOException e) {
            callback.failure("loadJsonFromAssets:" + jsonFileName, e);
        } catch (JsonSyntaxException e) {
            callback.failure("loadJsonFromAssets:" + jsonFileName, e);
        }
    }
}
