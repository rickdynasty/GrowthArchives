package com.demo.tws.rick.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 自定义Toast,主要是绕过系统的NotificationManagerService控制机制，通过handle自己控制
 */
public class CustomToast {
    private Context mContext;
    private WindowManager wm;
    private int mDuration;
    private View mNextView;
    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;

    public CustomToast(Context context) {
        mContext = context.getApplicationContext();
        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    public static CustomToast makeText(Context context, CharSequence text, int duration) {
        CustomToast result = new CustomToast(context);
        View view = android.widget.Toast.makeText(context, text, android.widget.Toast.LENGTH_SHORT).getView();
        if (view != null) {
            TextView tv = (TextView) view.findViewById(android.R.id.message);
            tv.setText(text);
        }
        result.mNextView = view;
        result.mDuration = (LENGTH_LONG == duration ? 3500 : 2000);//from NotificationManagerService
        return result;
    }

    public static CustomToast makeText(Context context, int resId, int duration)
            throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId), duration);
    }

    public void show() {
        if (mNextView != null) {
            WindowManager.LayoutParams params = new WindowManager.LayoutParams();
            params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
            params.format = PixelFormat.TRANSLUCENT;
            params.windowAnimations = android.R.style.Animation_Toast;
            params.y = dip2px(mContext, 64);
            params.type = WindowManager.LayoutParams.TYPE_TOAST;
            boolean addSucess = false;
            try {
                //部分机器WM和ViewRoot token逻辑耦合，如果最后一个activity窗口退出了造成token null is not valid异常。
                wm.addView(mNextView, params);
                addSucess = true;
            } catch (WindowManager.BadTokenException e) {
                addSucess = false;
            }

            if (addSucess) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        if (mNextView != null) {
                            wm.removeView(mNextView);
                            mNextView = null;
                            wm = null;
                        }
                    }
                }, mDuration);
            }
        }
    }

    /**
     * dip与px的转换
     *
     * @参数 @param context
     * @参数 @param dipValue
     * @返回值 int
     */
    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
