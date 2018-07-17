package br.com.wpgomes.treinamento.appcasttreinamento.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import br.com.wpgomes.treinamento.appcasttreinamento.ui.activities.MainActivity;

/**
 * Created by wapeg on 27/09/2016.
 */

public class Boot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentHello = new Intent(context, MainActivity.class);
        context.startActivity(intentHello);
    }
}
