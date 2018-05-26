package com.rick.tws.Model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonModelImpl implements BaseJsonModel {

    private static final String TAG = JsonModelImpl.class.getSimpleName();

    @Override
    public void loadJsonData(final String jsonFilePath, final LoadDataCallback callback) {
        if (null == callback) {
            throw new IllegalArgumentException("Callback cannot be empty!");
        }

        //数据获取操作
        new Thread() {
            @Override
            public void run() {
                try {
                    //从json文件中读取配置信息
                    JSONObject jsonObject = new JSONObject(getJsonFromFile(jsonFilePath));

                    callback.success(dillJSONObject(jsonObject));
                } catch (JSONException e) {
                    callback.failure("loadJsonData:" + jsonFilePath + " failure", e);
                } catch (IOException e) {
                    callback.failure("loadJsonData:" + jsonFilePath + " failure", e);
                }
            }
        }.start();
    }

    @Override
    public void loadJsonFromAssets(final Context context, final String jsonFileName, final LoadDataCallback callback) {
        if (null == callback) {
            throw new IllegalArgumentException("Callback cannot be empty!");
        }

        //数据获取操作
        new Thread() {
            @Override
            public void run() {
                try {
                    //从json文件中读取配置信息
                    JSONObject jsonObject = new JSONObject(getJsonFromAssets(context, jsonFileName));

                    callback.success(dillJSONObject(jsonObject));
                } catch (JSONException e) {
                    callback.failure("loadJsonFromAssets:" + jsonFileName, e);
                } catch (IOException e) {
                    callback.failure("loadJsonFromAssets:" + jsonFileName, e);
                }
            }
        }.start();
    }

    private String getJsonFromAssets(Context context, String fileName) throws IOException {
        //获取assets资源管理器
        AssetManager assetManager = context.getAssets();

        return getJsonFromStream(new InputStreamReader(assetManager.open(fileName)));
    }

    private String getJsonFromFile(String file) throws IOException {
        return getJsonFromStream(new InputStreamReader(new FileInputStream(file)));
    }

    private String getJsonFromStream(final InputStreamReader stream) throws IOException {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        //通过管理器打开文件并读取
        BufferedReader bf = new BufferedReader(stream);
        String line;
        while ((line = bf.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    // json root 入口
    private List<MetroArea> dillJSONObject(final JSONObject jsonObject) throws JSONException {
        ArrayList<MetroArea> metroAreas = new ArrayList<>(jsonObject.length());
        metroAreas.clear();

        JSONObject jsonChildObject = null;
        JSONArray jsonArray = null;

        if (null != jsonObject) {
            Iterator keys = jsonObject.keys();
            String key, lowerKey;
            while (keys.hasNext()) {
                key = keys.next().toString();
                lowerKey = key.toLowerCase();
                if (lowerKey.startsWith(MetroGroupStruct.METRO_CARD_GROUP_PREFIX)) {
                    jsonChildObject = jsonObject.getJSONObject(key);
                    dillGroupJsonObject(jsonChildObject, metroAreas);
                } else if (lowerKey.startsWith(MetroLinearStruct.METRO_CARD_CUSTOM_ROW_PREFIX)) {
                    // jsonArray = jsonObject.getJSONArray(key);
                    jsonChildObject = jsonObject.getJSONObject(key);
                    dillRowJSONArray(jsonChildObject, metroAreas);
                } else if (lowerKey.startsWith(MetroLinearStruct.METRO_CARD_ROW_PREFIX)) {
                    dillRowJSONArray(jsonObject.optJSONArray(key), metroAreas);
                } else {
                    //Root下面只有group、row
                }
            }
        }

        return metroAreas;
    }

    // 处理group模块
    private void dillGroupJsonObject(JSONObject jsonObject, final List<MetroArea> metroAreas) throws JSONException {
        if (null == metroAreas) {
            throw new IllegalArgumentException("metroAreas cannot be empty!");
        }

        Iterator keys = jsonObject.keys();
        String key, lowerKey;
        String groupName = "";
        MetroGroupStruct groupStruct = new MetroGroupStruct(jsonObject.length());
        while (keys.hasNext()) {
            key = keys.next().toString();
            lowerKey = key.toLowerCase();

            if (lowerKey.startsWith(MetroLinearStruct.METRO_CARD_CUSTOM_ROW_PREFIX)) {
                //带自定义的Row
                dillRowJSONArray(jsonObject.getJSONObject(key), groupStruct.linearStructs);
            } else if (lowerKey.startsWith(MetroLinearStruct.METRO_CARD_ROW_PREFIX)) {
                // 非自定义的Row
                dillRowJSONArray(jsonObject.getJSONArray(key), groupStruct.linearStructs);
            } else if (MetroGroupStruct.METRO_CARD_GROUP_KEY_NAME.equals(key)) {
                groupName = jsonObject.getString(MetroGroupStruct.METRO_CARD_GROUP_KEY_NAME);
                Log.i(TAG, "dillGroupJsonObject get groupName:" + groupName);
                groupStruct.setName(groupName);
            } else if (MetroGroupStruct.METRO_CARD_GROUP_KEY_ID.equals(key)) {
                int id = Integer.parseInt(jsonObject.getString(MetroGroupStruct.METRO_CARD_GROUP_KEY_ID));
                Log.i(TAG, "dillGroupJsonObject get id:" + id);
                groupStruct.setID(id);
            } else {
                Log.e(TAG, "发现Group:" + groupName + "异常key:" + key + " ：" + jsonObject.optString(key));
            }
        }

        metroAreas.add(groupStruct);
    }

    // 处理带自定义信息的Row模块
    private void dillRowJSONArray(JSONObject jsonObject, final List<MetroArea> metroAreas) throws JSONException {
        if (null == metroAreas) {
            throw new IllegalArgumentException("metroAreas cannot be empty!");
        }

        Iterator keys = jsonObject.keys();
        String key, lowerKey;
        int rowHeight;
        MetroArea linearStruct = new MetroLinearStruct();
        JSONArray jsonArray = null;
        while (keys.hasNext()) {
            key = keys.next().toString();
            lowerKey = key.toLowerCase();

            if (MetroLinearStruct.METRO_CARD_ROW_KEY_HEIGHT.equals(key)) {
                rowHeight = Integer.parseInt(jsonObject.getString(MetroLinearStruct.METRO_CARD_ROW_KEY_HEIGHT));
                ((MetroLinearStruct) linearStruct).setHeight(rowHeight);
            } else if (lowerKey.startsWith(MetroLinearStruct.METRO_ROW_CARDS_PREFIX)) {
                jsonArray = jsonObject.getJSONArray(key);
                dillRowJSONArray(jsonArray, (MetroLinearStruct) linearStruct);
            } else {
                //可能是直接配置card
            }
        }

        metroAreas.add(linearStruct);
    }

    // 处理row模块
    private void dillRowJSONArray(JSONArray jsonArray, final List<MetroArea> metroAreas) throws JSONException {
        MetroArea linearStruct = new MetroLinearStruct();
        dillRowJSONArray(jsonArray, (MetroLinearStruct) linearStruct);
        metroAreas.add(linearStruct);
    }

    // 填充linearStruct - 解析卡片Item信息
    private void dillRowJSONArray(JSONArray jsonArray, final MetroLinearStruct linearStruct) throws JSONException {
        if (null == linearStruct) {
            throw new IllegalArgumentException("metroAreas cannot be empty!");
        }

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

            linearStruct.cards.add(cardStruct);
        }
    }
}
