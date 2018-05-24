package com.rick.tws.widget;

import android.content.Context;
import android.graphics.Color;

public class MetroCardFactory {
    public static final int NOTIFY_CARD = 0;       //特别关注
    public static final int DATE_CARD = 1;          //我的日程

    public static final int MEETING_MANAGE_CARD = 2;    //会议管理
    public static final int LEADER_AGENDA_CARD = 3;     //领导日程

    public static final int WAIT_SINGIN_CARD = 4;       //待签收
    public static final int ARCHIVING_CARD = 5;         //归档
    public static final int OFFICIAL_CAR_CARD = 6;      //公务用车

    public static MetroCard createMetroCard(Context context, int type) {
        MetroCard metroCard = new MetroCard(context);
        String title = "unknow";
        int iconRes = R.drawable.ic_launcher;
        int shadowRes = R.drawable.ic_launcher;
        int bgRes = 0;
        boolean isDrawalbeBgRes = false;

        switch (type) {
            case NOTIFY_CARD:
                title = "特别关注";
                shadowRes = R.drawable.shadow_specialcare;
                bgRes = Color.LTGRAY;
                break;
            case DATE_CARD:
                title = "我的日程";
                shadowRes = R.drawable.shadow_black_red;
                bgRes = Color.RED;
                break;
            case MEETING_MANAGE_CARD:
                title = "会议管理";
                shadowRes = R.drawable.shadow_main_39b1b7;
                iconRes = R.drawable.work_main_ic_hygl;
                bgRes = R.drawable.shape_gov_39b1b7;
                isDrawalbeBgRes = true;
                break;
            case LEADER_AGENDA_CARD:
                title = "领导日程";
                shadowRes = R.drawable.shadow_main_52b0ff;
                iconRes = R.drawable.work_main_leader_schedule_icon;
                bgRes = R.drawable.shape_gov_52b0ff;
                isDrawalbeBgRes = true;
                break;
            case WAIT_SINGIN_CARD:
                title = "待签收";
                shadowRes = R.drawable.shadow_main_ff9c68;
                iconRes = R.drawable.work_main_waitsign_icon;
                bgRes = R.drawable.shape_gov_ff9c68;
                isDrawalbeBgRes = true;
                break;
            case ARCHIVING_CARD:
                title = "归档";
                shadowRes = R.drawable.shadow_main_d38ffe;
                iconRes = R.drawable.work_main_collect_icon;
                bgRes = R.drawable.shape_gov_d38ffe;
                isDrawalbeBgRes = true;
                break;
            case OFFICIAL_CAR_CARD:
                title = "公务用车";
                shadowRes = R.drawable.shadow_main_fd8da5;
                iconRes = R.drawable.work_main_item_car_icon;
                bgRes = R.drawable.shape_gov_fd8da5;
                isDrawalbeBgRes = true;
                break;
            default:
                break;
        }

        metroCard.setCardContent(title, iconRes, shadowRes, bgRes, isDrawalbeBgRes);
//        if (0 != startColor || 0 != endColor) {
//            metroCard.setCardGradientColor(new int[]{startColor, endColor});
//        } else {
//        metroCard.setBackgroundColor(bgColor);
//        }

        return metroCard;
    }
}
