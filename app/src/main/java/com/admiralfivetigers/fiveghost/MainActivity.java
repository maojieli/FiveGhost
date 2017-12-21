package com.admiralfivetigers.fiveghost;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.admiralfivetigers.fiveghost.widget.customtitle.TitleBarView;


public class MainActivity extends AppCompatActivity {


    private TitleBarView tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        tb.setLeftImageisible(View.GONE);
        tb.setRightImageisible(View.GONE);
        tb.setLeftTextisible(View.VISIBLE);
        tb.setRightTextisible(View.VISIBLE);

        tb.setLeftTextView("左侧文本");
        tb.setRightTextView("右侧文本");
        tb.setTitleTextView("我是标题");

    }

    private void initView() {
        tb = (TitleBarView) findViewById(R.id.tb);
    }
}

