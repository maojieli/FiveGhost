package com.admiralfivetigers.fiveghost.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Stack;

/**
 * 你的指尖--有改变世界的力量！
 * Created by Nyh on 2017/12/22.
 */
/*
 *定义抽象的BaseActivity,其余的Activity全部继承BaseActivity，
 *便于统一管理，一处修改，到处有效
 */
public abstract class BaseActivity extends AppCompatActivity{

    //是否状态栏透明
    protected boolean isSetStatusBar = false;
    //是否允许全屏
    protected boolean mAllowFullScreen = false;
    //是否禁止屏幕旋转
    protected boolean isAllowScreenRoate = false;
    //用来保存所有已经打开的Activity
    protected static Stack<Activity> activityList = new Stack<Activity>();

    //记录上次点击按钮的时间
    private long lastClickTime;

    //按钮连续点击最低间隔时间
    public final static int CLICK_TIME = 500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 将activity推入栈中
        activityList.push(this);
        // 初始化布局,记得要在这个方法里面setContentView()
        initLayout();
        // 初始化控件和监听事件
        initView();
        // 初始化数据
        initData();

        //如果设置为true，就显示全屏
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        //状态栏透明的(沉浸式状态栏)
        if (isSetStatusBar) {
            steepStatusBar();
        }
        //如果为false就禁止屏幕旋转
        if (!isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

    }
    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz         所跳转的Activity类
     * @param requestCode 请求码
     */
    public void startActivityForResult(Class<?> clz, int requestCode) {
        startActivityForResult(new Intent(this, clz), requestCode);
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转页面
     *
     * @param clz         所跳转的Activity类
     * @param bundle      跳转所携带的信息
     * @param requestCode 请求码
     */
    public void startActivityForResult(Class<?> clz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 消息提示框
     *
     * @param message 提示消息文本
     */
    public void showToast(String message) {
        if(!message.equals("")){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 消息提示框
     *
     * @param messageId 提示消息文本ID
     */
    public void showToast(int messageId) {
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
    }

    /*
     * 如果你的项目有侧滑栏可以处理此方法,声明成protected的让子类可以重写
     */
    protected void addLeftMenu(boolean enable) {
        // 是否能有侧滑栏
        if (enable) {

        } else {

        }
    }
    /** 初始化布局 **/
    protected abstract void initLayout();

    /** 初始化数据 **/
    protected abstract void initData();

    /** 初始化控件和监听**/
    protected abstract void initView();

    /*
     * 子类可用该方法查找控件
     */
    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
    /**
     * [沉浸状态栏]
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }


    /*
      用于保存Activity的数据不丢失
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    /*
     *子类可以在横竖屏切换时重写该方法用于保存数据不丢失
     */
    protected void saveInstanceState(Bundle outState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 从栈中移除当前activity
        if (activityList.contains(this)) {
            activityList.remove(this);
        }

    }

    /**
     * [是否允许全屏]
     *
     * @param allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * [是否设置沉浸状态栏]
     *
     * @param isSetStatusBar
     */
    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * [是否允许屏幕旋转]
     *
     * @param isAllowScreenRoate
     */
    public void setScreenRoate(boolean isAllowScreenRoate) {
        this.isAllowScreenRoate = isAllowScreenRoate;
    }
    //验证上次点击按钮时间间隔，防止重复点击
    public boolean verifyClickTime() {
        if (System.currentTimeMillis() - lastClickTime <= CLICK_TIME) {
            return false;
        }
        lastClickTime = System.currentTimeMillis();
        return true;
    }
    /**
     * 关闭所有(前台、后台)Activity,注意：请已BaseActivity为父类
     */
    protected static void finishAll() {
        int leng = activityList.size();
        for (int i = 0; i < leng; i++) {
            Activity activity = activityList.pop();
            activity.finish();
        }
    }
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (KeyEvent.KEYCODE_BACK == keyCode) {
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                // 将系统当前的时间赋值给exitTime
                exitTime = System.currentTimeMillis();
            } else {
                finishAll();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private Fragment currentFragment;

    /** Fragment替换(当前destrory,新的create) */
    public void fragmentReplace(int target, Fragment toFragment, boolean backStack) {
        //获取V4包下的Fragment管理器
        FragmentManager manager = getSupportFragmentManager();
        //拿到事务对象
        FragmentTransaction transaction = manager.beginTransaction();
        String toClassName = toFragment.getClass().getSimpleName();
        if (manager.findFragmentByTag(toClassName) == null) {
            transaction.replace(target, toFragment, toClassName);
            if (backStack) {
                transaction.addToBackStack(toClassName);
            }
            transaction.commit();
        }
    }

    /** Fragment替换(核心为隐藏当前的,显示现在的,用过的将不会destrory与create) */
    public void smartFragmentReplace(int target, Fragment toFragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        // 如有当前在使用的->隐藏当前的
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        String toClassName = toFragment.getClass().getSimpleName();
        // toFragment之前添加使用过->显示出来
        if (manager.findFragmentByTag(toClassName) != null) {
            transaction.show(toFragment);
        } else {// toFragment还没添加使用过->添加上去
            transaction.add(target, toFragment, toClassName);
        }
        transaction.commit();
        // toFragment更新为当前的
        currentFragment = toFragment;
    }

}
