package com.demo.tws.rick.securitystrategy;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.demo.tws.rick.widget.CustomToast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AppStatusService extends Service {
    private static final String TAG = AppStatusService.class.getSimpleName();

    private Timer mTimer = new Timer();
    private String className;
    private String packageName;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        try {
            Bundle bundle = null;
            if (intent != null) {
                bundle = intent.getExtras();
            }
            if (bundle != null) {
                className = bundle.getString("className");
                packageName = bundle.getString("pageName");
            }

            if (TextUtils.isEmpty(className)) {
                className = "com.rick.tws.View.MainActivity";
            }
            if (TextUtils.isEmpty(packageName)) {
                packageName = "com.rick.tws.widget";
            }
            // 通过计时器延迟执行
            mTimer.schedule(timerTask, 50, 50);

        } catch (Exception e) {
            Log.i(TAG, "onStartCommand Exception", e);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 应用是否在前台运行
     *
     * @return true：在前台运行；false：已经被切到后台了
     */
    private boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();

        if (appProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    if (appProcess.processName.equals(packageName)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 定义一个timerTask来发通知和弹出Toast
     */
    TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            boolean isAppOnForeground = isAppOnForeground();
            Log.i(TAG, "TimerTask::run isAppOnForeground=" + isAppOnForeground);
            if (!isAppOnForeground) {
                //发通知
                showNotification();
                //弹出Toast提示
                MainActivity.mCurrentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "runOnUiThread run");
                        CustomToast.makeText(getApplicationContext(), "应用已进入后台", CustomToast.LENGTH_SHORT).show();
                    }
                });
            }
            mTimer.cancel();
        }
    };

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    /**
     * 弹出通知提示
     */
    private void showNotification() {
        Log.i(TAG, "showNotification");
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher); //设置图标
        builder.setTicker("显示第二个通知");
        builder.setContentTitle("通知"); //设置标题
        builder.setContentText("应用已进入后台"); //消息内容
        builder.setWhen(System.currentTimeMillis()); //发送时间
        builder.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
        builder.setAutoCancel(true);//打开程序后图标消失
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.putExtra("notification", "notification");
        intent.setClassName(packageName, className);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        notificationManager.notify(124, notification); // 通过通知管理器发送通知
    }
}
