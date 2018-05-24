package com.rick.tws;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Space;

import com.rick.tws.utils.DensityUtil;
import com.rick.tws.widget.CardGroupContainer;
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

        //config

        CardLinearContainer secondLinear = new CardLinearContainer(this);
        secondLinear.addOneCard(MetroCardFactory.createMetroCard(this, MetroCardFactory.MEETING_MANAGE_CARD));
        secondLinear.addOneCard(MetroCardFactory.createMetroCard(this, MetroCardFactory.LEADER_AGENDA_CARD));
        mMainArea.addOneLinearCard(secondLinear);

        //添加第一个Group
        CardGroupContainer firstGroupContainer = new CardGroupContainer(this);
        firstGroupContainer.setGroupId(1001);
        firstGroupContainer.setTile("Group1001");
        CardLinearContainer firstGroupFirstLinear = new CardLinearContainer(this);
        firstGroupFirstLinear.addOneCard(MetroCardFactory.createMetroCard(this, MetroCardFactory.WAIT_SINGIN_CARD));
        firstGroupFirstLinear.addOneCard(MetroCardFactory.createMetroCard(this, MetroCardFactory.ARCHIVING_CARD));
        firstGroupFirstLinear.addOneCard(MetroCardFactory.createMetroCard(this, MetroCardFactory.OFFICIAL_CAR_CARD));
        firstGroupContainer.addOneLinearCard(firstGroupFirstLinear);

        CardLinearContainer firstGroupSecondLinear = new CardLinearContainer(this);
        firstGroupSecondLinear.addOneCard(MetroCardFactory.createMetroCard(this, MetroCardFactory.NOTIFY_CARD), 2.0f);
        //添加一个spcae做分隔
        Space space = new Space(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DensityUtil.dip2px(this, 2), LinearLayout.LayoutParams.MATCH_PARENT);
        firstGroupSecondLinear.addView(space, lp);
        firstGroupSecondLinear.addOneCard(MetroCardFactory.createMetroCard(this, MetroCardFactory.DATE_CARD), 1.0f);
        firstGroupContainer.addOneLinearCard(firstGroupSecondLinear);
        mMainArea.addOneGroupCard(firstGroupContainer);

        //添加第二个Group
        CardGroupContainer secondGroupContainer = new CardGroupContainer(this);
        secondGroupContainer.setGroupId(1002);
        secondGroupContainer.setTile("Group1002");
        CardLinearContainer secondGroupSecondLinear = new CardLinearContainer(this);
        secondGroupSecondLinear.addOneCard(MetroCardFactory.createMetroCard(this, "G2l2-T01", R.drawable.ic_launcher, R.drawable.shadow_specialcare, new int[]{Color.RED, Color.YELLOW}), 1.0F);
        secondGroupSecondLinear.addOneCard(MetroCardFactory.createMetroCard(this, "G2l2-T02", R.drawable.ic_launcher, R.drawable.shadow_specialcare, new int[]{Color.RED, Color.YELLOW, Color.GREEN}), 2.0F);
        secondGroupContainer.addOneLinearCard(secondGroupSecondLinear);
        mMainArea.addOneGroupCard(secondGroupContainer);

        Space space2 = new Space(this);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 15));
        mMainArea.addView(space2, lp2);

    }
}
