package com.rick.tws.View;

import com.rick.tws.Model.WorkspaceContent;

import java.util.ArrayList;

public interface IWorkspaceUI {
    void initWorkspace(ArrayList<WorkspaceContent> groupDataList);

    void loadJsonfailure(String errorDes, Exception e);
}
