package com.rick.tws.View;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rick.tws.widget.R;
import com.rick.tws.widget.Workspace;

public class WorkspaceActivity extends Activity {
    private static final String TAG = WorkspaceActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_workspace);
        setTitle("WorkspaceActivity");

        Workspace workspace = findViewById(R.id.navigation_area);

        workspace.initData();
    }
}
