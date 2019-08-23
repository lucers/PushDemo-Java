package com.lucers.pushdemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.huawei.android.hms.agent.HMSAgent;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * @author Lucers
 * @date 2019/8/7 0007
 */
public class DemoApplication extends Application {

    public static final String APP_ID = "2882303761518133466";
    public static final String APP_KEY = "5511813325466";
    public static final String TAG = "Lucers";

    @Override
    public void onCreate() {
        super.onCreate();
        if (shouldInit()) {
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
        }
        //打开Log
        LoggerInterface newLogger = new LoggerInterface() {
            @Override
            public void setTag(String tag) {
                // ignore
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                Log.d(TAG, "log:" + content);
            }
        };
        Logger.setLogger(this, newLogger);
        boolean init = HMSAgent.init(this);
        Log.e("Lucers", "HMSAgent init result:" + init);
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getApplicationInfo().processName;
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
