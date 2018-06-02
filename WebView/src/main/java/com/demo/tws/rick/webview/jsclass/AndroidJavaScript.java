package com.demo.tws.rick.webview.jsclass;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * JS的调用的方法
 * 此类中的打开的QQ 和微信是直接通过包名和类名调用虽然QQ微信包名不容易变 但是主界面好事可能会变
 * 如果发现打不开QQ微信应用可以查看是否是QQ微信升级更改了类名
 */
public class AndroidJavaScript {

    Activity context;
    final String[] qqpackage = new String[]{"com.tencent.mobileqq", "com.tencent.mobileqq.activity.SplashActivity"};
    final String[] wxpackage = new String[]{"com.tencent.mm", "com.tencent.mm.ui.LauncherUI"};

    public AndroidJavaScript(Activity context) {
        this.context = context;
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    @JavascriptInterface
    public void diallPhone(final String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    @JavascriptInterface
    public void callPhone(final String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 发短信（直接发短信）
     *
     * @param msg 电话号码
     */
    @JavascriptInterface
    public void sendSMS(String msg) {
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> list = smsManager.divideMessage(msg);
        for (int i = 0; i < list.size(); i++) {
            smsManager.sendTextMessage("10086", null, list.get(i), null, null);
        }
    }


    /**
     * 发短信（调用系统的发短信界面）
     *
     * @param
     */
    @JavascriptInterface
    public void openSMS(final String number,final String body) {
//        Intent intentFinalMessage = new Intent(Intent.ACTION_VIEW);
//        intentFinalMessage.setType("vnd.android-dir/mms-sms");

        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse("smsto:" + number));
        sendIntent.putExtra("sms_body", body);

        context.startActivity(sendIntent);
    }

    @JavascriptInterface
    public void callQQ(String qq) {
        // 实现调用电话号码

        if (!checkBrowser(qqpackage[0])) {

        } else {
            Intent intent = new Intent();
            ComponentName cmp = new ComponentName(qqpackage[0], qqpackage[1]);
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);
        }

    }

    @JavascriptInterface
    public void callWeixin(String weixin) {

        if (!checkBrowser(wxpackage[0])) {

        } else {
            Intent intent = new Intent();
            ComponentName cmp = new ComponentName(wxpackage[0], wxpackage[1]);
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            context.startActivity(intent);

        }

    }

    // 获取在webview上获取js生成的html的源码
    @JavascriptInterface
    public void getSource(String htmlstr) {
        // Log.e("html", htmlstr);
        // String path = c.getFilesDir().getAbsolutePath() + "/serve.html"; //
        // data/data目录

    }

    public boolean checkBrowser(String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(
                    packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 打开通讯录界面
     */
    @JavascriptInterface
    public void openTXL() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("vnd.android.cursor.dir/phone_v2");
        context.startActivity(intent);
    }

    @JavascriptInterface
    public void showTtoast(final String msg) {
        Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 检查是否拥有指定的所有权限
     */
    @JavascriptInterface
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                return false;
            }
        }
        return true;
    }

    public static final int MY_PERMISSION_REQUEST_CODE = 10000;
    public static final int CAMERA_REQUEST_CODE = 10001;
    public static final int RECORD_SOUND_REQUEST_CODE = 10002;

    /**
     * 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
     */
    @JavascriptInterface
    public void requestPermissions(String[] permissions) {
        // 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
        ActivityCompat.requestPermissions(
                context,
                permissions,
                MY_PERMISSION_REQUEST_CODE
        );
    }

    /**
     * 打开相机——拍照
     */
    @JavascriptInterface
    public void openCamera() {
        // 如果是拍摄 使用：ACTION_VIDEO_CAPTURE
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        context.startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
    }

    /**
     * 打开第三方应用
     *
     * @param pkgName 第三方应用的包名
     * @param clsName 第三方应用activity的Name
     */
    @JavascriptInterface
    public void startActivity(final String pkgName, final String clsName) {
        ComponentName componet = new ComponentName(pkgName, clsName);
        //pkg 就是第三方应用的包名
        //cls 就是第三方应用的进入的第一个Activity
        Intent intent = new Intent();
        intent.setComponent(componet);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 打开录音机
     */
    @JavascriptInterface
    public void openRecordSound() {
        // 如果是拍摄 使用：ACTION_VIDEO_CAPTURE
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        context.startActivityForResult(takePictureIntent, RECORD_SOUND_REQUEST_CODE);
    }

    /**
     * 打开扫码功能界面
     */
    @JavascriptInterface
    public void openCodeScan() {

    }

    /**
     * 打开定位功能界面
     */
    @JavascriptInterface
    public void openLocation() {

    }

}