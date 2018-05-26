package com.rick.tws.Presenter;

import android.content.Context;

public interface BasePresenter {
    void loadJsonFromAssets(Context context, String jsonFileName);

    void loadJson(String fileName);

    void loadDefaultData();
}
