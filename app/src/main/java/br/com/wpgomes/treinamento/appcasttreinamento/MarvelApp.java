package br.com.wpgomes.treinamento.appcasttreinamento;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MarvelApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().
                setDefaultFontPath("fonts/Roboto-Regular.ttf").
                setFontAttrId(R.attr.fontPath).build());
    }
}
