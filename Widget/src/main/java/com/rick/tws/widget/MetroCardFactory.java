package com.rick.tws.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.rick.tws.Model.MetroCardStruct;

public class MetroCardFactory {
    public static final int NOTIFY_CARD = 0;       //特别关注
    public static final int DATE_CARD = 1;          //我的日程

    public static final int MEETING_MANAGE_CARD = 2;    //会议管理
    public static final int LEADER_AGENDA_CARD = 3;     //日程

    public static final int WAIT_SINGIN_CARD = 4;       //待签收
    public static final int ARCHIVING_CARD = 5;         //归档
    public static final int OFFICIAL_CAR_CARD = 6;      //用车

    public static final int OA_CARD = 7;                //OA站点
    public static final int ATTENDANCE_NOTICE = 8;      //考勤通知

    public static MetroCardStruct createMetroCardStruct(int type) {
        String title = "unknow";
        int iconRes = R.drawable.ic_launcher;
        int shadowRes = R.drawable.ic_launcher;
        int startColor = -1;
        int centerColor = -1;
        int endColor = -1;
        int weight = 1;

        int cardType = 1;
        String action = "action";
        int actionType = 0;

        switch (type) {
            case NOTIFY_CARD:
                title = "特别关注";
                shadowRes = R.drawable.shadow_specialcare;
                startColor = Color.LTGRAY;
                endColor = Color.LTGRAY;
                weight = 2;
                break;
            case DATE_CARD:
                title = "我的日程";
                shadowRes = R.drawable.shadow_black_red;
                startColor = Color.RED;
                endColor = Color.RED;
                weight = 1;
                break;
            case MEETING_MANAGE_CARD:
                title = "会议管理";
                shadowRes = R.drawable.shadow_main_39b1b7;
                iconRes = R.drawable.work_main_ic_hygl;
                startColor = 0xFF39B1B7;
                endColor = 0xFF1EDE81;
                break;
            case LEADER_AGENDA_CARD:
                title = "日程";
                shadowRes = R.drawable.shadow_main_52b0ff;
                iconRes = R.drawable.work_main_leader_schedule_icon;
                startColor = 0xFF1E78FF;
                endColor = 0xFF52B0FF;
                break;
            case WAIT_SINGIN_CARD:
                title = "待签收";
                shadowRes = R.drawable.shadow_main_ff9c68;
                iconRes = R.drawable.work_main_waitsign_icon;
                startColor = 0xFFFF6451;
                endColor = 0xFFFF9C68;
                break;
            case ARCHIVING_CARD:
                title = "归档";
                shadowRes = R.drawable.shadow_main_d38ffe;
                iconRes = R.drawable.work_main_collect_icon;
                startColor = 0xFF8E33FE;
                endColor = 0xFFD38FFE;
                break;
            case OFFICIAL_CAR_CARD:
                title = "用车";
                shadowRes = R.drawable.shadow_main_fd8da5;
                iconRes = R.drawable.work_main_item_car_icon;
                startColor = 0xFFFA5779;
                endColor = 0xFFFD8DA5;
                break;
            case OA_CARD:
                title = "OA站点";
                shadowRes = R.drawable.shadow_main_fd8da5;
                iconRes = R.drawable.work_main_item_car_icon;
                startColor = 0xFFFA5779;
                endColor = 0xFFFD8DA5;
                weight = 1;
                break;

            case ATTENDANCE_NOTICE:
                title = "考勤通知";
                shadowRes = R.drawable.shadow_blug;
                iconRes = R.drawable.function_icon_cmail;
                startColor = 0xFFFA5779;
                endColor = 0xFFFD8DA5;
                weight = 2;
                break;
            default:
                break;
        }

        return new MetroCardStruct(title, iconRes, shadowRes, cardType, startColor, centerColor, endColor, actionType, action, weight);
    }

    public static MetroCard createMetroCard(Context context, MetroCardStruct cardStruct) {
        if (null == cardStruct) {
            return null;
        }

        MetroCard metroCard = new MetroCard(context);
        metroCard.setCardType(cardStruct.getCardType());

        if (cardStruct.gradientCenterEffective()) {
            metroCard.setCardContent(
                    cardStruct.getCardType(),
                    cardStruct.getTitle(),
                    cardStruct.getIconName(),
                    cardStruct.getShadowResName(),
                    new int[]{cardStruct.getGradientStartColor(), cardStruct.getGradientCenterColor(), cardStruct.getGradientEndColor()},
                    cardStruct.getActionType(),
                    cardStruct.getAction());
        } else if (cardStruct.gradientEffective()) {
            metroCard.setCardContent(
                    cardStruct.getCardType(),
                    cardStruct.getTitle(),
                    cardStruct.getIconName(),
                    cardStruct.getShadowResName(),
                    new int[]{cardStruct.getGradientStartColor(), cardStruct.getGradientEndColor()},
                    cardStruct.getActionType(),
                    cardStruct.getAction());
        } else {
            metroCard.setCardContent(
                    cardStruct.getCardType(),
                    cardStruct.getTitle(),
                    cardStruct.getIconName(),
                    cardStruct.getShadowResName(),
                    null,
                    cardStruct.getActionType(),
                    cardStruct.getAction());
        }

        if (context instanceof View.OnClickListener) {
            metroCard.setOnClickListener((View.OnClickListener) context);
        }

        return metroCard;
    }
}
