package com.lucers.pushdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.huawei.android.hms.agent.HMSAgent;
import com.huawei.android.hms.agent.common.handler.ConnectHandler;
import com.huawei.android.hms.agent.push.handler.GetTokenHandler;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_PUSH_PERMISSION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PUSH_PERMISSION);
        } else {
            initHWPush();
        }
    }

    private void initHWPush() {
        HMSAgent.connect(this, new ConnectHandler() {
            @Override
            public void onConnect(int state) {
                Log.e("Lucers", "onConnect:" + state);
            }
        });
        HMSAgent.Push.getToken(new GetTokenHandler() {
            @Override
            public void onResult(int state) {
                Log.e("Lucers", "onResult:" + state);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("Lucers", "onRequestPermissionsResult:requestCode-->" + requestCode);
        for (int i = 0; i < permissions.length; i++) {
            Log.e("Lucers", "onRequestPermissionsResult:permission--->" + permissions[i] + "==" + grantResults[i]);
        }
        boolean result = true;
        for (int f = 0; f < grantResults.length; f++) {
            if (grantResults[f] == PackageManager.PERMISSION_DENIED) {
                result = false;
            }
        }
        if (result) {
            initHWPush();
        } else {
            Log.e("Lucers", "推送权限被拒绝");
        }
    }
}
