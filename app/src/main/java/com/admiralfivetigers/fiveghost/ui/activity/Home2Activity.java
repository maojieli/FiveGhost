package com.admiralfivetigers.fiveghost.ui.activity;


import android.os.Bundle;

import com.admiralfivetigers.fiveghost.R;
import com.admiralfivetigers.fiveghost.app.BaseActivity;

public class Home2Activity extends BaseActivity {


    @Override
    protected void initLayout() {

        //是否允许屏幕旋转
        setScreenRoate(false);
        //是否允许全屏
        setAllowFullScreen(true);
        //是否是沉浸式状态栏
        setSteepStatusBar(true);
        //设置布局
        setContentView(R.layout.activity_home2);

    }
    /*
     * 填充数据
     */
    @Override
    protected void initData() {

    }
    /*
     * 初始化控件
     */
    @Override
    protected void initView() {

    }
    /*
  * 有侧滑页面的方法
  */
    @Override
    protected void addLeftMenu(boolean enable) {


    }
    /*
     * 在此方法中保存数据
     */
    @Override
    protected void saveInstanceState(Bundle outState) {
        super.saveInstanceState(outState);
    }
}
