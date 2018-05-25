package com.rick.tws;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rick.tws.utils.DensityUtil;
import com.rick.tws.widget.CardGroupContainer;
import com.rick.tws.widget.CardLinearContainer;
import com.rick.tws.widget.MetroCard;
import com.rick.tws.widget.MetroCardFactory;
import com.rick.tws.widget.MetroCardStruct;
import com.rick.tws.widget.MetroUI;
import com.rick.tws.widget.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "rick_Print:MainActivity";
    private MetroUI mMainArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "----=-=-=-===========-==-=---------");

        mMainArea = findViewById(R.id.work_main_area);
        try {
            initMainArea("metro_ui_content.json");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static final String METRO_CARD_GROUP_PREFIX = "group";
    private static final String METRO_CARD_ROW_PREFIX = "row";
    private static final String METRO_CARD_GROUP_KEY_NAME = "group_name";
    private static final String METRO_CARD_GROUP_KEY_ID = "group_id";

    // 处理group模块
    private void dillGroupJsonObject(JSONObject jsonObject) throws JSONException {
        CardGroupContainer groupContainer = new CardGroupContainer(this);
        Iterator keys = jsonObject.keys();
        String key, lowerKey;
        String groupName = "";
        while (keys.hasNext()) {
            key = keys.next().toString();
            lowerKey = key.toLowerCase();

            if (lowerKey.startsWith(METRO_CARD_ROW_PREFIX)) {
                groupContainer.addOneLinearCard(dillRowJSONArray(jsonObject.getJSONArray(key)));
            } else if (METRO_CARD_GROUP_KEY_NAME.equals(key)) {
                groupName = jsonObject.getString(METRO_CARD_GROUP_KEY_NAME);
                Log.i(TAG, "dillGroupJsonObject groupName:" + groupName);
                groupContainer.setGroupName(groupName);
            } else if (METRO_CARD_GROUP_KEY_ID.equals(key)) {
                groupContainer.setGroupId(Integer.parseInt(jsonObject.getString(METRO_CARD_GROUP_KEY_ID)));
            } else {
                Log.e(TAG, "发现Group:" + groupName + "异常key:" + key + " ：" + jsonObject.optString(key));
            }
        }

        mMainArea.addOneGroupCard(groupContainer);

        mMainArea.addEndSpace(DensityUtil.dip2px(this, 15));
    }

    // 处理row模块
    private CardLinearContainer dillRowJSONArray(JSONArray jsonArray) throws JSONException {
        CardLinearContainer linear = new CardLinearContainer(this);
        String key, value;

        //每一个row块里面都是多个MetroCard
        for (int i = 0; i < jsonArray.length(); i++) {
            // 遍历 jsonarray 数组，把每一个对象转成 json 对象
            MetroCardStruct cardStruct = new MetroCardStruct();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                key = keys.next().toString();
                value = jsonObject.optString(key);
                cardStruct.setContent(key, value);
            }
            Log.i(TAG, "get MetroCardStruct:" + cardStruct);

            MetroCard card = MetroCardFactory.createMetroCard(this, cardStruct);
            if (null != card) {
                linear.addOneCard(card, cardStruct.weightEffective() ? cardStruct.getWeight() : 1.0f);
            } else {
                Log.e(TAG, "Error create MetroCard for cardStruct：" + cardStruct);
            }
        }
        return linear;
    }

    //解析json文件
    private void initMainArea(String jsonFileName) throws JSONException {
        if (null == mMainArea) {
            throw new Resources.NotFoundException("Unable to find work_main_area");
        }

        mMainArea.removeAllViews();

        //从json文件中读取配置信息
        JSONObject jsonObject = new JSONObject(getJsonStringFromFile(jsonFileName, this));
        JSONObject jsonChildObject = null;
        JSONArray jsonArray = null;

        if (null != jsonObject) {
            Iterator keys = jsonObject.keys();
            String key, lowerKey;
            while (keys.hasNext()) {
                key = keys.next().toString();
                lowerKey = key.toLowerCase();
                if (lowerKey.startsWith(METRO_CARD_GROUP_PREFIX)) {
                    jsonChildObject = jsonObject.getJSONObject(key);
                    dillGroupJsonObject(jsonChildObject);
                } else if (lowerKey.startsWith(METRO_CARD_ROW_PREFIX)) {
                    jsonArray = jsonObject.getJSONArray(key);
                    mMainArea.addOneLinearCard(dillRowJSONArray(jsonArray));
                }
            }
        }

        mMainArea.addEndSpace(DensityUtil.dip2px(this, 15));
    }

    @Override
    public void onClick(View v) {
        if (v instanceof MetroCard) {
            Toast.makeText(this, "Card:" + ((MetroCard) v).getTitle(), Toast.LENGTH_SHORT).show();
        }
    }

    private String getJsonStringFromFile(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}
