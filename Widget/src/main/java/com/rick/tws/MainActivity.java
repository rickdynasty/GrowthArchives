package com.rick.tws;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Space;

import com.rick.tws.widget.CardLinearContainer;
import com.rick.tws.widget.MetroCardFactory;
import com.rick.tws.widget.MetroUI;
import com.rick.tws.widget.R;

public class MainActivity extends AppCompatActivity {

    private MetroUI mMainArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainArea = findViewById(R.id.work_main_area);
        initMainArea();
    }

    private void initMainArea() {
        if (null == mMainArea) {
            throw new Resources.NotFoundException("Unable to find work_main_area");
        }

        mMainArea.removeAllViews();

        CardLinearContainer firstLinear = new CardLinearContainer(this);
        firstLinear.addOneCard(MetroCardFactory.createMetroCard(this, MetroCardFactory.NOTIFY_CARD), 2.0f);
        //添加一个spcae做分隔
        Space space = new Space(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DensityUtil.dip2px(this,10), LinearLayout.LayoutParams.MATCH_PARENT);
        firstLinear.addView(space,lp);
        firstLinear.addOneCard(MetroCardFactory.createMetroCard(this, MetroCardFactory.DATE_CARD), 1.0f);
        mMainArea.addOneLinearCard(firstLinear);

        CardLinearContainer secondLinear = new CardLinearContainer(this);
        secondLinear.addOneCard(MetroCardFactory.createMetroCard(this, MetroCardFactory.MEETING_MANAGE_CARD));
        secondLinear.addOneCard(MetroCardFactory.createMetroCard(this, MetroCardFactory.LEADER_AGENDA_CARD));
        mMainArea.addOneLinearCard(secondLinear);


        CardLinearContainer thirdLinear = new CardLinearContainer(this);
        thirdLinear.addOneCard(MetroCardFactory.createMetroCard(this, MetroCardFactory.WAIT_SINGIN_CARD));
        thirdLinear.addOneCard(MetroCardFactory.createMetroCard(this, MetroCardFactory.ARCHIVING_CARD));
        thirdLinear.addOneCard(MetroCardFactory.createMetroCard(this, MetroCardFactory.OFFICIAL_CAR_CARD));
        mMainArea.addOneLinearCard(thirdLinear);
    }
}
