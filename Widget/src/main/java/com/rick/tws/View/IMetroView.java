package com.rick.tws.View;

import com.rick.tws.Model.MetroArea;

import java.util.List;

public interface IMetroView {
    void showMetroUIData(List<MetroArea> metroAreasData);
    void loadJsonfailure(String jsonFileName, Exception e);
}
