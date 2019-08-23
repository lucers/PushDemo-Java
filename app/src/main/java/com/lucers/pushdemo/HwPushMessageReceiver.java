package com.lucers.pushdemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.support.api.push.PushReceiver;

/**
 * HwPushMessageReceiver
 *
 * @author Lucers
 * @date 2019/8/23 0023
 */
public class HwPushMessageReceiver extends PushReceiver {

    @Override
    public void onEvent(Context context, Event event, Bundle extras) {
        super.onEvent(context, event, extras);
        Log.e("Lucers", "onEvent");
    }

    @Override
    public void onToken(Context context, String token) {
        super.onToken(context, token);
        Log.e("Lucers", "onToken:" + token);
    }

    @Override
    public void onToken(Context context, String token, Bundle extras) {
        super.onToken(context, token, extras);
        Log.e("Lucers", "onTokenWithExtra:" + token + "\nExtra-->" + extras);
    }

    @Override
    public boolean onPushMsg(Context context, byte[] msgBytes, Bundle extras) {
        String message = "";
        try {
            message = new String(msgBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("Lucers", "onPushMsgWithExtra:" + message + "\nExtra-->" + extras);
        return super.onPushMsg(context, msgBytes, extras);
    }

    @Override
    public void onPushMsg(Context context, byte[] msg, String token) {
        super.onPushMsg(context, msg, token);
        String message = "";
        try {
            message = new String(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.e("Lucers", "onPushMsgWithToken:" + message + "\nToken-->" + token);
    }

    @Override
    public void onPushState(Context context, boolean pushState) {
        super.onPushState(context, pushState);
        Log.e("Lucers", "onPushState:" + pushState);
    }
}
