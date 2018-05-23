package com.demo.osl.rick.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.demo.osl.rick.Bean.AsyncMessage;
import com.demo.osl.rick.Bean.BackgroundMessage;
import com.demo.osl.rick.Bean.EventBean;
import com.demo.osl.rick.Bean.EventMessage;
import com.demo.osl.rick.Bean.MainMessage;
import com.demo.osl.rick.Bean.PostingMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册，这样就可以在当前接收事件【注意生命周期结束的时候反注册】
        EventBus.getDefault().register(this);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn_secondactivity).setOnClickListener(this);

        mTextview = (TextView) findViewById(R.id.tv);
    }

    /**
     * 消息处理的方法可以随便取名，但是需要添加一个注解@Subscribe，并且要指定线程模型（默认为POSTING）
     *
     * EventBus 会在编译时和运行时（取决于你是否添加索引）通过处理注解和反射的方式拿到订阅方法和所在的类，
     * 然后将订阅者、订阅方法、订阅的事件分别保存在两个属性中。
     *
     * 在有发送者发送事件时，EventBus 根据事件去前面保存的属性里找到订阅者和订阅方法，然后以反射的方式调用它。
     */
    //主线程中执行
    //ThreadMode.MAIN 事件的处理会在UI线程中执行
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEventBus(MainMessage msg) {
        mTextview.setText("MAIN主线程中发出：" + msg.getMessage());
    }

    //主线程 粘性(比如传递intent使用)
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMainStickEvent(EventMessage event) {
        mTextview.setText("MAIN主线程中发出(粘性)：" + event.getMessage());
    }

    //后台线程
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackgroundEventBus(BackgroundMessage msg) {
        mTextview.setText("BACKGROUND后台线程中发出：" + msg.getMessage());
    }

    //异步线程 - 重新开启一个线程来执行
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAsyncEventBus(AsyncMessage msg) {
        mTextview.setText("ASYNC异步线程中发出：" + msg.getMessage());
    }

    //默认情况，和发送事件在同一个线程
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPostEventBus(PostingMessage msg) {
        mTextview.setText("POSTING发送事件在同一个线程中发出：" + msg.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //反注册
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                EventBus.getDefault().post(new MainMessage("MainMessage"));
                break;
            case R.id.btn2:
                EventBus.getDefault().post(new BackgroundMessage("BackgroundMessage"));
                break;
            case R.id.btn3:
                EventBus.getDefault().post(new AsyncMessage("AsyncMessage"));
                break;
            case R.id.btn4:
                EventBus.getDefault().post(new PostingMessage("PostingMessage"));
                break;
            case R.id.btn5:
                EventBus.getDefault().postSticky(new EventMessage("EventMessage"));
                break;
            case R.id.btn_secondactivity:
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
                break;
        }
    }
}
