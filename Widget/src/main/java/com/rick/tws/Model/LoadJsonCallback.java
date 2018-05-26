package com.rick.tws.Model;

import java.util.List;

public interface LoadJsonCallback {
    void success(List<? extends MetroArea> result);

    void failure();
}
