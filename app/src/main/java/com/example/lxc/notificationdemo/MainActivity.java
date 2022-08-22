package com.example.lxc.notificationdemo;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static final String NOTIFICATION_CHANNEL_SOUREDIO = "soundrecorder_notification_channel";//add by liaoxincheng

    private LinearLayout mLin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendnotice = (Button) findViewById(R.id.button);
        Button sendnotice2 = (Button) findViewById(R.id.button2);
        mLin = (LinearLayout)findViewById(R.id.test);
        sendnotice.setOnClickListener(this);
        String string = "123456";
        string.contains("");

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button:
                Intent intent = new Intent(this,notificationActivity.class); //想要启动notificationActivity
                PendingIntent pi = PendingIntent.getActivity(this,0,intent,FLAG_UPDATE_CURRENT); //PendingIntent为延迟执行的Intent

                Intent intent2 = new Intent(this,notificationActivity2.class); //想要启动notificationActivity
                PendingIntent pi2 = PendingIntent.getActivity(this,1,intent2,FLAG_UPDATE_CURRENT); //PendingIntent为延迟执行的Intent

                //Notification.Builder builder = null;

                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);//通知管理器
                Notification notification =new NotificationCompat.Builder(this)
                        .setContentTitle("This is content title")
                        .setContentText("this is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)   //小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher)) //大图标
                        .setContentIntent(pi)  //传入PendingIntent,才能执行
                        .setPriority(NotificationCompat.PRIORITY_MAX)   //通知重要程度为最高
                        .setOngoing(true)//不能划掉，只能点开后
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.pingguo)))
                        .setAutoCancel(true) //当点击了这个通知,通知会自动消失
                        .build();

                Notification notification2 =new NotificationCompat.Builder(this)
                        .setContentTitle("This is content title")
                        .setContentText("this is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)   //小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher)) //大图标
                        .setContentIntent(pi2)  //传入PendingIntent,才能执行
                        .setPriority(NotificationCompat.PRIORITY_MAX)   //通知重要程度为最高
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.pingguo)))
                        .setAutoCancel(true) //当点击了这个通知,通知会自动消失
                        .build();

                manager.notify(1,notification);//1为ID,每条通知设置ID
                manager.notify(2,notification2);//2为ID,每条通知设置ID
                break;
            case R.id.button2:

                break;
            default:
                break;
            }
        }

        //8.0通知
       /** public void sendMediaStyleNotification() {
            Intent intent = new Intent(this,SoundRecorder.class); //想要启动notificationActivity
            PendingIntent pi = PendingIntent.getActivity(this,0,intent,FLAG_UPDATE_CURRENT); //PendingIntent为延迟执行的Intent


            NotificationChannel mChannel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_SOUREDIO,
                    getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_DEFAULT);
            mChannel.setSound(null,null);
            mChannel.setVibrationPattern(null);

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//通知管理器
            manager.createNotificationChannel(mChannel);

            Notification notification =new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_SOUREDIO)
                    .setContentTitle(mRecordingFileNameTextView.getText().toString())
                    .setContentText(getResources().getString(R.string.sound_state))
                    .setSmallIcon(R.drawable.ic_sound)   //小图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_soundrecorder)) //大图标
                    .setContentIntent(pi)  //传入PendingIntent,才能执行
                    .setPriority(NotificationCompat.PRIORITY_MAX)   //通知重要程度为最高
                    .setOngoing(true)//不能划掉，只能点开后
                    .build();

            //发送通知
            manager.notify(1,notification);//1为ID,每条通知设置ID
        }**/

       private boolean validateMicAvailability(){
           Boolean available = true;
           AudioRecord recorder =
                   new AudioRecord(MediaRecorder.AudioSource.MIC, 44100,
                           AudioFormat.CHANNEL_IN_MONO,
                           AudioFormat.ENCODING_DEFAULT, 44100);
           try{
               if(recorder.getRecordingState() != AudioRecord.RECORDSTATE_STOPPED){
                   available = false;
               }
               if(recorder.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING){
                   available = true;
               }
           } finally{
           }
           return available;
       }

}
