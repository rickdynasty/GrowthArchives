package com.demo.tws.rick.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.demo.tws.rick.webview.jsclass.AndroidJavaScript;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends Activity {
    private static final String JSNAME = "JSData.data";
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("SecondActivity");

        findViewById(R.id.btn_second).setVisibility(View.GONE);

        mWebView = (WebView) findViewById(R.id.webview);

        // 启用javascript
        mWebView.getSettings().setJavaScriptEnabled(true);

        // 从assets目录下面的加载html
        mWebView.loadUrl("file:///android_asset/serve.html");

        // 注入一个Java对象给JS调用
        mWebView.addJavascriptInterface(new AndroidJavaScript(this), "Android");
        // mWebView.loadUrl("javascript:getStr('" + 122222 + "')");
        mWebView.setWebViewClient(webviewcilnt);
        String phon = "技术服务电话：,0731-22332233,0731-44332234;产品服务QQ:,5733935198,209384022;产品公众微信:,CSHNJK,yung7086,weixin";
//		String path1 = "file:///android_asset/wxicon.png";

        saveHTMLData(phon);
    }

    WebViewClient webviewcilnt = new WebViewClient() {
        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            String phon = loadHTMLData();
            mWebView.loadUrl("javascript:createTable('" + phon + "')");
            // 获取webview加载的html页面
            view.loadUrl("javascript:window.Android.getSource('<html>'+"
                    + "document.getElementsByTagName('html')[0].innerHTML+'</html>');");

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    };

    /**
     * 保存从服务器获取的填充到html的数据
     */
    private void saveHTMLData(String phon) {
        try {
            FileOutputStream out = this.openFileOutput(JSNAME, MODE_PRIVATE);
            byte[] bytes = phon.getBytes();
            out.write(bytes);
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取填充到html的数据
     */
    private String loadHTMLData() {
        String jsstr = null;
        try {
            FileInputStream in = this.openFileInput(JSNAME);
            byte[] bytes = new byte[256];
            in.read(bytes);
            in.close();
            jsstr = new String(bytes);
            return jsstr;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsstr;
    }
}
