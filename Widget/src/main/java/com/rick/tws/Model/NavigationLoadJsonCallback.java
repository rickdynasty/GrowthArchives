package com.rick.tws.Model;

public interface NavigationLoadJsonCallback {
    void success(NavigationJsonData result);

    void failure(String errorDes, Exception e);
}
