package com.admiralfivetigers.fiveghost;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.admiralfivetigers.fiveghost.utils.PrefUtils;

public class StartActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private RelativeLayout rlSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
   jumpNextPage();

    }
    private void jumpNextPage() {

        // 判断之前有没有展示过功能引导页
        boolean guideShowed = PrefUtils.getBoolean(StartActivity.this,
                PrefUtils.GUIDE_SHOWED, false);
        Log.e("TAG",""+guideShowed);
        // 如果没有展示过功能引导页
        if (!guideShowed) {
            // 跳转到功能引导页
            startActivity(new Intent(StartActivity.this, WelcomActivity.class));
        }else {
            rlSplash = (RelativeLayout) findViewById(R.id.rl_splash);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(StartActivity.this,LoginActivity.class));
                    finish();
                }
            }, 2000);
        }

    }

}
