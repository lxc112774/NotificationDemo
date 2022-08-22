package com.example.lxc.notificationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class notificationActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification2);

          //这也是关闭通知的一种方法
//        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        manager.cancel(1);  //1为该通知的ID
    }
}
