package com.demo.osl.rick.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.demo.osl.rick.Bean.AsyncMessage;
import com.demo.osl.rick.Bean.BackgroundMessage;
import com.demo.osl.rick.Bean.EventMessage;
import com.demo.osl.rick.Bean.MainMessage;
import com.demo.osl.rick.Bean.PostingMessage;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1, btn2, btn3, btn4, btn5;
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);

        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this);

        findViewById(R.id.btn_secondactivity).setVisibility(View.GONE);
        findViewById(R.id.tv).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                EventBus.getDefault().post(new MainMessage("Second MainMessage"));
                break;
            case R.id.btn2:
                EventBus.getDefault().post(new BackgroundMessage("Second BackgroundMessage"));
                break;
            case R.id.btn3:
                EventBus.getDefault().post(new AsyncMessage("Second AsyncMessage"));
                break;
            case R.id.btn4:
                EventBus.getDefault().post(new PostingMessage("Second PostingMessage"));
                break;
            case R.id.btn5:
                EventBus.getDefault().postSticky(new EventMessage("Second EventMessage"));
                break;
        }
    }
}
