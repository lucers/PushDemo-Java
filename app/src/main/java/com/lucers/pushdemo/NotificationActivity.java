package com.lucers.pushdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * NotificationActivity
 *
 * @author Lucers
 * @date 2019/8/22 0022
 */
public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        getNotificationTitle(getIntent(), 0);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getNotificationTitle(intent, 1);
    }

    private void getNotificationTitle(Intent intent, int type) {
        if (intent != null) {
            String title = intent.getStringExtra("title");
            if (type == 0) {
                Log.e("Lucers", "onCreate:title-->" + title);
            } else {
                Log.e("Lucers", "onNewIntent:title-->" + title);
            }
        }
    }
}
