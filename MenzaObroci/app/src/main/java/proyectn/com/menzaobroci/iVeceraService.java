package proyectn.com.menzaobroci;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class iVeceraService extends IntentService {

    public static final int NOTIFICATION_ID = 156;

    public iVeceraService() {
        super("iVeceraService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Intent daIntent = new Intent(this, RecieverPrijatno.class);
        daIntent.putExtra(RecieverPrijatno.ACTION_ID, 1);
        PendingIntent daPending = PendingIntent.getBroadcast(this,1, daIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle("Da li uzimate i večeru?")
                .setSmallIcon(R.mipmap.ic_escajg_kvadrat)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(android.R.drawable.sym_def_app_icon, "Uzimam", daPending)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("22", "Podsetnik za večeru", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId("22");
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
