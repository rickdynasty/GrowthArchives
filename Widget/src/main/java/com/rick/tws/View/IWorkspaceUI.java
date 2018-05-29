package com.rick.tws.View;

import com.rick.tws.Model.WorkspaceGroupContent;

import java.util.ArrayList;

public interface IWorkspaceUI {
    void initWorkspace(ArrayList<WorkspaceGroupContent> groupDataList);

    void loadJsonfailure(String errorDes, Exception e);
}
