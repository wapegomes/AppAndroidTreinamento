package br.com.wpgomes.treinamento.appcasttreinamento.broadcast;

import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;

import br.com.wpgomes.treinamento.appcasttreinamento.service.MonitorService;

/**
 * Created by wgomes on 07/07/16.
 */
public class TestReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, MonitorService.class);
        startWakefulService(context, service);
    }
}
