package com.demo.tws.rick.securitystrategy;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "rick_Print:MainActivity";

    public static Activity mCurrentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentActivity = this;

        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_test).setOnClickListener(this);
        mScreenReceiver = new ScreenBroadcastReceiver();
        registerListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                Toast.makeText(this, "BuildConfig KEY is " + BuildConfig.KEY, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "onBackPressed 01");
        super.onBackPressed();
        Log.i(TAG, "onBackPressed 02");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG, "onKeyDown :" + keyCode);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        Intent intent = new Intent();
        intent.putExtra("pageName", this.getComponentName().getPackageName());
        intent.putExtra("className", this.getComponentName().getClassName());
        intent.setClass(this, AppStatusService.class);
        stopService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");

        Intent intent = new Intent();
        intent.putExtra("pageName", this.getComponentName().getPackageName());
        intent.putExtra("className", this.getComponentName().getClassName());
        intent.setClass(this, AppStatusService.class);
        startService(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        stopAppStatusService();
        unregisterListener();
    }

    private void stopAppStatusService() {
        Log.i(TAG, "stopAppStatusService");
        Intent intent = new Intent();
        intent.setClass(this, AppStatusService.class);
        stopService(intent);
    }

    private ScreenBroadcastReceiver mScreenReceiver;

    /**
     * screen状态广播接收者
     */
    private class ScreenBroadcastReceiver extends BroadcastReceiver {
        private String action = null;

        @Override
        public void onReceive(Context context, Intent intent) {
            action = intent.getAction();
            if (Intent.ACTION_SCREEN_ON.equals(action)) { // 开屏
                //mScreenStateListener.onScreenOn();
            } else if (Intent.ACTION_SCREEN_OFF.equals(action)) { // 锁屏
                Log.i(TAG, "receive ACTION_SCREEN_OFF");
                stopAppStatusService();
            } else if (Intent.ACTION_USER_PRESENT.equals(action)) { // 解锁
                //mScreenStateListener.onUserPresent();
            }
        }
    }

    /**
     * 启动screen状态广播接收器
     */
    private void registerListener() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mScreenReceiver, filter);
    }

    /**
     * 停止screen状态监听
     */
    public void unregisterListener() {
        unregisterReceiver(mScreenReceiver);
    }
}
