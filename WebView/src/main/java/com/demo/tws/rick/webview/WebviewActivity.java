package com.demo.tws.rick.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class WebviewActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView mWebView;
    private TextView logTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.webview);
        setTitle("Webview");

        // 启用javascript
        mWebView.getSettings().setJavaScriptEnabled(true);

        // 从assets目录下面的加载html
        mWebView.loadUrl("file:///android_asset/test.html");

        // 注入一个Java对象给JS调用
        mWebView.addJavascriptInterface(this, "ZWT_javaFunc");
        logTextView = (TextView) findViewById(R.id.text);

        findViewById(R.id.btn_native).setOnClickListener(this);
        findViewById(R.id.btn_second).setOnClickListener(this);
        findViewById(R.id.open_file).setOnClickListener(this);
    }

    @android.webkit.JavascriptInterface
    public void actionFromJs() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(WebviewActivity.this, "js调用了Native函数", Toast.LENGTH_SHORT).show();
                String text = logTextView.getText() + "\njs调用了Native函数";
                logTextView.setText(text);
            }
        });
    }

    @android.webkit.JavascriptInterface
    public void actionFromJsWithParam(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(WebviewActivity.this, "js调用了Native函数传递参数：" + str, Toast.LENGTH_SHORT).show();
                String text = logTextView.getText() + "\njs调用了Native函数传递参数：" + str;
                logTextView.setText(text);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_native:
                // 无参数调用
                mWebView.loadUrl("javascript:actionFromNative()");
                // 传递参数调用
                mWebView.loadUrl("javascript:actionFromNativeWithParam(" + "'come from Native'" + ")");
                break;
            case R.id.btn_second:
                startActivity(new Intent(WebviewActivity.this, SecondActivity.class));
                break;
            case R.id.open_file:
                startActivity(new Intent(WebviewActivity.this, WebViewOpenFileActivity.class));
                break;
        }
    }
}
