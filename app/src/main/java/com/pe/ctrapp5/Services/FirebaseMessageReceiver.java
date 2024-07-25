package com.pe.ctrapp5.Services;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pe.ctrapp5.Data.Dta19;
import com.pe.ctrapp5.MainActivity;
import com.pe.ctrapp5.MainActivity30;
import com.pe.ctrapp5.MainActivity33;
import com.pe.ctrapp5.Model.Obj19;
import com.pe.ctrapp5.R;

import java.util.Date;

public class FirebaseMessageReceiver  extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        Log.e("message from",remoteMessage.getFrom());


        if(remoteMessage.getNotification()!=null){

            String p1= remoteMessage.getNotification().getTitle();
            String p2= remoteMessage.getNotification().getBody();
            String p3= remoteMessage.getData().get("idCita").toString();

            Log.e("p3__", p3);

            sendNotificationB(p1,p2,p3);
        }

        //super.onMessageReceived(remoteMessage);
    }

    public void sendNotification(String messageTitle,String messageBody) {

        NotificationManager notificationManager =(NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, MainActivity33.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant")
            NotificationChannel notificationChannel=new NotificationChannel("my_notification","n_channel",NotificationManager.IMPORTANCE_MAX);
            notificationChannel.setDescription("description");
            notificationChannel.setName("Channel Name");
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon4)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon5))
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_ALL)
                .setOnlyAlertOnce(true)
                .setChannelId("my_notification")
                .setColor(Color.parseColor("#3F5996"));

        //.setProgress(100,50,false);
        assert notificationManager != null;
        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        notificationManager.notify(m, notificationBuilder.build());
    }


    private void sendNotificationB(String messageTitle,String messageBody, String idCita) {

        NotificationManager notificationManager =(NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, MainActivity33.class);
        intent.putExtra("idCita",idCita);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | android.app.PendingIntent.FLAG_IMMUTABLE);
        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant")
            NotificationChannel notificationChannel=new NotificationChannel("my_notification","n_channel",NotificationManager.IMPORTANCE_MAX);
            notificationChannel.setDescription("description");
            notificationChannel.setName("Channel Name");
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.icon5);
        notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon5));
        notificationBuilder.setContentTitle(messageTitle);
        notificationBuilder.setContentText(messageBody);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSound(soundUri);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setDefaults(Notification.DEFAULT_ALL);
        notificationBuilder.setOnlyAlertOnce(true);
        notificationBuilder.setChannelId("my_notification");
        notificationBuilder.setColor(Color.parseColor("#3F5996"));


        //.setProgress(100,50,false);
        assert notificationManager != null;
        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        notificationManager.notify(m, notificationBuilder.build());

        Log.e("title_____", messageTitle);
        Log.e("body_____", messageBody);

//        Dta19 dta19 = new Dta19(this);
//        Obj19 m19 = new Obj19();
//        m19.setF01(messageTitle);
//        m19.setF02(messageBody);
//        dta19.insert(m19);

//
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("email",obj01.getF01());
//        startActivity(intent);


    }

}
