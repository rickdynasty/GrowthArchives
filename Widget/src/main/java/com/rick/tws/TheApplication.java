package com.rick.tws;

import android.app.Application;
import android.content.Context;

public class TheApplication extends Application {
    private static TheApplication mInstance;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mInstance = this;
    }

    public static TheApplication getInstance() {
        return mInstance;
    }
}
