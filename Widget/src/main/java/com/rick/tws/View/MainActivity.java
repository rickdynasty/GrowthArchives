package com.rick.tws.View;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rick.tws.Model.MetroArea;
import com.rick.tws.Model.MetroGroupStruct;
import com.rick.tws.Model.MetroLinearStruct;
import com.rick.tws.Presenter.BasePresenter;
import com.rick.tws.Presenter.JsonPresenterImpl;
import com.rick.tws.util.DensityUtils;
import com.rick.tws.util.FileUtils;
import com.rick.tws.widget.CardGroupContainer;
import com.rick.tws.widget.CardLinearContainer;
import com.rick.tws.widget.MetroCard;
import com.rick.tws.widget.R;
import com.rick.tws.widget.SimpleMetroUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IMetroView {

    private static final String TAG = "rick_Print:MainActivity";
    private SimpleMetroUI mMainArea;
    private TextView mTv;
    private BasePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainArea = findViewById(R.id.work_main_area);
        mTv = findViewById(R.id.main_tv);

        Log.i(TAG, "----=-=-=-===========-==-=---------");

        //初始化Presenter，并通过Presenter获取需要的数据
        mPresenter = new JsonPresenterImpl(this);
        mTv.setText("自定义MetroUI内容");
        mPresenter.loadJsonFromAssets(this, "metro_ui_content.json");
        findViewById(R.id.main_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
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

    private Handler mUiHandle;

    private void sureUIHanlde() {
        if (null == mUiHandle) {
            mUiHandle = new Handler(getMainLooper());
        }
    }

    @Override
    public void showMetroUIData(final List<MetroArea> metroAreasData) {
        sureUIHanlde();
        mUiHandle.post(new Runnable() {
            @Override
            public void run() {
                mMainArea.removeAllViews();
                for (MetroArea metroArea : metroAreasData) {
                    if (metroArea instanceof MetroGroupStruct) {    //Group
                        //注意这里构建CardGroupContainer的时候传入的上下文MainActivity - 实现了OnClickListener
                        CardGroupContainer groupContainer = new CardGroupContainer(MainActivity.this);
                        groupContainer.init((MetroGroupStruct) metroArea);
                        mMainArea.addOneGroupCard(groupContainer);
                        mMainArea.addEndSpace(DensityUtils.dip2px(MainActivity.this, 15));
                    } else if (metroArea instanceof MetroLinearStruct) {    //Row
                        //注意这里构建CardLinearContainer的时候传入的上下文MainActivity - 实现了OnClickListener
                        CardLinearContainer linearContainer = new CardLinearContainer(MainActivity.this);
                        linearContainer.init((MetroLinearStruct) metroArea);
                        mMainArea.addOneLinearCard(linearContainer);
                    } else {
                        //不是Group也不是Row,。。。
                    }
                }

                //最后在末尾添加一段空白
                mMainArea.addEndSpace(DensityUtils.dip2px(MainActivity.this, 15));
            }
        });
    }

    @Override
    public void loadJsonfailure(String errorDes, Exception e) {

        // 这里应该考虑加载默认的模板进行初始化界面
        mPresenter.loadDefaultData();
        mTv.setText("缺省MetroUI内容");

        Log.e(TAG, errorDes, e);
    }
}
