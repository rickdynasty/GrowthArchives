package com.rick.tws.Presenter;

import android.content.Context;
import android.graphics.Color;

import com.rick.tws.Model.JsonModelImpl;
import com.rick.tws.Model.MetroArea;
import com.rick.tws.Model.MetroCardStruct;
import com.rick.tws.Model.MetroLinearStruct;
import com.rick.tws.View.IMetroView;
import com.rick.tws.widget.MetroCardFactory;

import java.util.ArrayList;
import java.util.List;

public class JsonPresenterImpl implements BasePresenter, JsonModelImpl.LoadDataCallback {

    private final IMetroView mView;
    private final JsonModelImpl mModel;

    public JsonPresenterImpl(IMetroView view) {
        mView = view;
        mModel = new JsonModelImpl();
    }

    @Override
    public void loadJsonFromAssets(Context context, String jsonFileName) {
        mModel.loadJsonFromAssets(context, jsonFileName, this);
    }

    @Override
    public void loadJson(String jsonFilePath) {
        mModel.loadJsonData(jsonFilePath, this);
    }

    @Override
    public void success(List result) {
        if (null == mView) {
            throw new RuntimeException("The \"null == mView\" scenario is theoretically absent~!");
        }

        mView.showMetroUIData(result);
    }

    @Override
    public void failure(String errorDes, Exception e) {
        if (null == mView) {
            throw new RuntimeException("The \"null == mView\" scenario is theoretically absent~!");
        }

        mView.loadJsonfailure(errorDes, e);
    }

    @Override
    public void loadDefaultData() {
        ArrayList<MetroArea> metroAreas = new ArrayList<>();

        MetroLinearStruct zeroRow = new MetroLinearStruct(2);
        zeroRow.cards.add(MetroCardFactory.createMetroCardStruct(MetroCardFactory.OA_CARD));
        zeroRow.cards.add(MetroCardFactory.createMetroCardStruct(MetroCardFactory.ATTENDANCE_NOTICE));
        metroAreas.add(zeroRow);

        MetroLinearStruct firtRow = new MetroLinearStruct(2);
        firtRow.cards.add(MetroCardFactory.createMetroCardStruct(MetroCardFactory.NOTIFY_CARD));
        firtRow.cards.add(MetroCardFactory.createMetroCardStruct(MetroCardFactory.DATE_CARD));
        metroAreas.add(firtRow);

        MetroLinearStruct SecondRow = new MetroLinearStruct(2);
        SecondRow.cards.add(MetroCardFactory.createMetroCardStruct(MetroCardFactory.MEETING_MANAGE_CARD));
        SecondRow.cards.add(MetroCardFactory.createMetroCardStruct(MetroCardFactory.LEADER_AGENDA_CARD));
        metroAreas.add(SecondRow);

        MetroLinearStruct ThridRow = new MetroLinearStruct(3);
        ThridRow.cards.add(MetroCardFactory.createMetroCardStruct(MetroCardFactory.WAIT_SINGIN_CARD));
        ThridRow.cards.add(MetroCardFactory.createMetroCardStruct(MetroCardFactory.ARCHIVING_CARD));
        ThridRow.cards.add(MetroCardFactory.createMetroCardStruct(MetroCardFactory.OFFICIAL_CAR_CARD));
        metroAreas.add(ThridRow);

        mView.showMetroUIData(metroAreas);
    }
}
