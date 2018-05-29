package com.rick.tws.Model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.TextUtils;

import com.rick.tws.util.DensityUtils;
import com.rick.tws.widget.R;

import java.util.ArrayList;

public class WorkspaceData {
    private boolean unprocessing = true;
    public ArrayList<WorkspaceGroupContent> workspaceGroups;

    public void processing(Context context) {
        if (unprocessing) {
            final Resources res = context.getResources();
            boolean uniformConfig_item_width, uniformConfig_item_height, uniformConfig_icon_width, uniformConfig_icon_height;
            for (WorkspaceGroupContent content : workspaceGroups) {
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
                    }

                    if (uniformConfig_icon_width) {
                        itemStruct.icon_width = content.icon_width;
                    }

                    if (uniformConfig_icon_height) {
                        itemStruct.icon_height = content.icon_height;
                    }

                }
            }

            unprocessing = true;
        }
    }
}
