package com.lucers.pushdemo;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

/**
 * @author Lucers
 * @date 2019/8/7 0007
 */
public class MiPushMessageReceiver extends PushMessageReceiver {

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
        Log.e("Lucers", "onReceivePassThroughMessage:" + message.toString());
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
        Log.e("Lucers", "onNotificationMessageClicked:" + message.toString());
        String title = message.getTitle();
        Log.e("Lucers", "onNotificationMessageClicked:" + title);
        Intent intent = new Intent(context, NotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
        Log.e("Lucers", "onNotificationMessageArrived:" + message.toString());
        showNotificationMessage(context);
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        Log.e("Lucers", "onCommandResult" + message.toString());
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
        Log.e("Lucers", "onReceiveRegisterResult" + message.toString());
    }

    private void showNotificationMessage(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    }
}
