package com.rick.tws.Model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.TextUtils;

import com.rick.tws.util.DensityUtils;
import com.rick.tws.widget.R;

import java.util.ArrayList;

/**
 * Copyright (C) 2018 pa_zwt Licensed under the Apache License, Version 1.0 (the "License");
 *
 * @author yongchen
 * @version v1.0
 * @date 2018-05-30
 * @des 工作台Wrokspace数据结构，主要用于适配GSON解析
 * @modify On 2018-05-30 by author for reason ...
 */
public class WorkspaceData {
    private boolean unprocessing = true;
    public ArrayList<WorkspaceGroupContent> workspaceGroups;
    private boolean needDivider = true;
    public boolean getNeedDivider(){
        return needDivider;
    }

    public void processing(Context context) {
        if (unprocessing) {
            final Resources res = context.getResources();
            boolean uniformConfig_item_width, uniformConfig_item_height, uniformConfig_icon_width, uniformConfig_icon_height;
            for (WorkspaceGroupContent content : workspaceGroups) {
                if (!content.needDivider) {
                    //只要有地方配置了不需要divider就不设置DividerDecoration
                    needDivider = false;
                }

                //行高 dp到pix
                if (CellItemStruct.INVALID_VALUE < content.header_height) {
                    content.header_height = DensityUtils.dip2px(context, content.header_height);
                }

                //将json配置的header文本颜色转成可用的int
                if (!TextUtils.isEmpty(content.header_textColor)) {
                    content.setHeaderTextColor(Color.parseColor(content.header_textColor));
                }

                //将json配置的header背景颜色转成可用的int
                if (!TextUtils.isEmpty(content.header_background)) {
                    content.setHeaderBackgroundColor(Color.parseColor(content.header_background));
                }

                uniformConfig_item_width = content.item_width != CellItemStruct.INVALID_VALUE;
                if (uniformConfig_item_width) {
                    content.item_width = DensityUtils.dip2px(context, content.item_width);
                }

                uniformConfig_item_height = content.item_height != CellItemStruct.INVALID_VALUE;
                if (uniformConfig_item_height) {
                    content.item_height = DensityUtils.dip2px(context, content.item_height);
                }

                uniformConfig_icon_width = content.icon_width != CellItemStruct.INVALID_VALUE;
                if (uniformConfig_icon_width) {
                    content.icon_width = DensityUtils.dip2px(context, content.icon_width);
                }

                uniformConfig_icon_height = content.icon_height != CellItemStruct.INVALID_VALUE;
                if (uniformConfig_icon_height) {
                    content.icon_height = DensityUtils.dip2px(context, content.icon_height);
                }

                //rick_Note:这里应该做一个事 - 宽高应该按屏幕比例来赋值
                for (CellItemStruct itemStruct : content.cellItemList) {
                    if (uniformConfig_item_width) {
                        itemStruct.item_width = content.item_width;
                    } else if (itemStruct.item_width != CellItemStruct.INVALID_VALUE) {
                        itemStruct.item_width = DensityUtils.dip2px(context, itemStruct.item_width);
                    } else {
                        itemStruct.item_width = res.getDimensionPixelSize(R.dimen.cell_item_width);
                    }

                    if (uniformConfig_item_height) {
                        itemStruct.item_height = content.item_height;
                    } else if (itemStruct.item_height != CellItemStruct.INVALID_VALUE) {
                        itemStruct.item_height = DensityUtils.dip2px(context, itemStruct.item_height);
                    } else {
                        itemStruct.item_height = res.getDimensionPixelSize(R.dimen.cell_item_height);
                    }

                    if (uniformConfig_icon_width) {
                        itemStruct.icon_width = content.icon_width;
                    } else if (itemStruct.icon_width != CellItemStruct.INVALID_VALUE) {
                        itemStruct.icon_width = DensityUtils.dip2px(context, itemStruct.icon_width);
                    } else {
                        itemStruct.icon_width = res.getDimensionPixelSize(R.dimen.cell_item_cion_size);
                    }

                    if (uniformConfig_icon_height) {
                        itemStruct.icon_height = content.icon_height;
                    } else if (itemStruct.icon_width != CellItemStruct.INVALID_VALUE) {
                        itemStruct.icon_height = DensityUtils.dip2px(context, itemStruct.icon_height);
                    } else {
                        itemStruct.icon_height = res.getDimensionPixelSize(R.dimen.cell_item_cion_size);
                    }

                    if (!TextUtils.isEmpty(itemStruct.background)) {
                        itemStruct.setBackgroundColor(Color.parseColor(itemStruct.background));
                    }

                    if (!TextUtils.isEmpty(itemStruct.startColor)) {
                        itemStruct.setGradientStartColor(Color.parseColor(itemStruct.startColor));
                    }


                    if (!TextUtils.isEmpty(itemStruct.centerColor)) {
                        itemStruct.setGradientCenterColor(Color.parseColor(itemStruct.centerColor));
                    }

                    if (!TextUtils.isEmpty(itemStruct.endColor)) {
                        itemStruct.setGradientEndColor(Color.parseColor(itemStruct.endColor));
                    }
                }
            }

            unprocessing = true;
        }
    }
}
