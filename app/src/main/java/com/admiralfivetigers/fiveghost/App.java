package com.admiralfivetigers.fiveghost;

import android.app.Application;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.io.IOException;

/**
 * Created by 韩学文 on 2017/12/25.
 * 花自飘零水自流。一种相思，两处闲愁。
 * 此情无计可消除，才下眉头，却上心头。
 */

public class App extends Application {
    //底下的放置里面  微信的 QQ的

    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }

    //对sdk进行初始化  开启DEBUG模式
    private static final String TAG = " andrew";

    private static final String APATCH_PATH = "/out.apatch";

    private static final String DIR = "apatch";//补丁文件夹
    /**
     * patch manager
     */
    private PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        //登录注册
        UMShareAPI.get(this);
        Config.DEBUG = true;
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //消息推送
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.e("TAG", deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
        //Bug收集
        CrashReport.initCrashReport(getApplicationContext(), "d7bf6471ca", false);
        MultiDex.install(this);
        /*
        *   热修复
        * */
        // initialize
        mPatchManager = new PatchManager(this);
        mPatchManager.init("1.0");
        Log.d(TAG, "inited.");

        // load patch
        mPatchManager.loadPatch();
        try {
            // .apatch file path
            String patchFileString = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + APATCH_PATH;
            mPatchManager.addPatch(patchFileString);
            Log.d(TAG, "apatch:" + patchFileString + " added.");
//
//            //复制且加载补丁成功后，删除下载的补丁
//            File f = new File(this.getFilesDir(), DIR + APATCH_PATH);
//            if (f.exists()) {
//                boolean result = new File(patchFileString).delete();
//                if (!result)
//                    Log.e(TAG, patchFileString + " delete fail");
//            }
        } catch (IOException e) {
            Log.e(TAG, "", e);
        }
    }
}
