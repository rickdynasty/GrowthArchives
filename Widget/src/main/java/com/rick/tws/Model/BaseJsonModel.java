package com.rick.tws.Model;

import android.content.Context;

public interface BaseJsonModel {
    void loadJsonData(String jsonFilePath, final NavigationLoadJsonCallback callback);

    void loadJsonFromAssets(Context context, String jsonFileName, final NavigationLoadJsonCallback callback);


    interface NavigationLoadJsonCallback {
        void success(WorkspaceData result);

        void failure(String errorDes, Exception e);
    }
}
