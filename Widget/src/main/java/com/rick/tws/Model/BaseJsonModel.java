package com.rick.tws.Model;

import android.content.Context;

import java.util.List;

public interface BaseJsonModel {
    void loadJsonData(String jsonFilePath, final LoadDataCallback callback);

    void loadJsonFromAssets(Context context, String jsonFileName, final LoadDataCallback callback);


    public interface LoadDataCallback {
        void success(List<MetroArea> result);

        void failure(String errorDes, Exception e);
    }
}
