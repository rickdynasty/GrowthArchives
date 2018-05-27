package com.rick.tws.Model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rick.tws.util.FileUtils;

import java.io.IOException;

public class NavigationJsonModel {
    public void loadJsonData(String jsonFilePath, NavigationLoadJsonCallback callback) {
        try {
            String content = FileUtils.getJsonFromFile(jsonFilePath);
            Gson gson = new Gson();
            NavigationJsonData entity = gson.fromJson(content, NavigationJsonData.class);
            callback.success(entity);
        } catch (IOException e) {
            callback.failure("loadJsonData:" + jsonFilePath, e);
        } catch (JsonSyntaxException e) {
            callback.failure("loadJsonData:" + jsonFilePath, e);
        }
    }

    public void loadJsonFromAssets(Context context, String jsonFileName, NavigationLoadJsonCallback callback) {
        try {
            String content = FileUtils.getJsonFromAssets(context, jsonFileName);
            Gson gson = new Gson();
            NavigationJsonData navigationData = gson.fromJson(content, NavigationJsonData.class);
            callback.success(navigationData);
        } catch (IOException e) {
            callback.failure("loadJsonFromAssets:" + jsonFileName, e);
        } catch (JsonSyntaxException e) {
            callback.failure("loadJsonFromAssets:" + jsonFileName, e);
        }
    }
}
