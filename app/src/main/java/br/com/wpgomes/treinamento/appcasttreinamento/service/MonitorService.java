package br.com.wpgomes.treinamento.appcasttreinamento.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import androidx.core.app.NotificationCompat;

import br.com.wpgomes.treinamento.appcasttreinamento.R;
import br.com.wpgomes.treinamento.appcasttreinamento.broadcast.TestReceiver;
import br.com.wpgomes.treinamento.appcasttreinamento.ui.activities.MainActivity;

/**
 * Created by wgomes on 07/07/16.
 */
public class MonitorService extends IntentService {

    public MonitorService(String name) {
        super(name);
    }

    public MonitorService()
    {
        super("MonitorService");
    }

    @Override
    protected void onHandleIntent(Intent i) {
        try{
            Thread.sleep(10000);
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_marvel)
                    .setContentTitle(getResources().getString(R.string.app_name)+" - Intent Service Notification")
                    .setContentText("Opa, Intent Service")
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            TestReceiver.completeWakefulIntent(i);
        }

    }

}
