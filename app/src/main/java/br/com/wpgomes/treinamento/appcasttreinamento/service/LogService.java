package br.com.wpgomes.treinamento.appcasttreinamento.service;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import br.com.wpgomes.treinamento.appcasttreinamento.util.Constants;

/**
 * Created by wapeg on 07/07/2016.
 */
public class LogService extends IntentService {

    public LogService()
    {
        super("LogService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        final String string = intent.getStringExtra(Constants.SERVICETAG);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("LogService",string);
            }
        },5000);
    }
}
