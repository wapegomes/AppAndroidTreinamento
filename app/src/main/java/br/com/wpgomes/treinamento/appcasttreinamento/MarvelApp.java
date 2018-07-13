package br.com.wpgomes.treinamento.appcasttreinamento;

import android.app.Application;
import android.content.Context;

import br.com.wpgomes.treinamento.appcasttreinamento.api.AuthInterceptor;
import okhttp3.Cache;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import okhttp3.OkHttpClient;


public class MarvelApp extends Application {

    private OkHttpClient okHttpClient;
    private final int cacheSize = 10 * 1024 * 1024; // 10 MiB
    private static final String PUBLIC_KEY = "277232a94db26746c653c879d30cec89";
    private static final String PRIVATE_KEY = "6d332bb5f4ad1eb5e428179c15f492ae7eb74b0d";

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().
                setDefaultFontPath("fonts/Roboto-Regular.ttf").
                setFontAttrId(R.attr.fontPath).build());

        createOkHttpClient();
    }

    private void createOkHttpClient() {
        Cache cache = new Cache(getCacheDir(), cacheSize);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(new AuthInterceptor(PUBLIC_KEY,PRIVATE_KEY));
        builder.cache(cache);

        okHttpClient = builder.build();

    }

    public static MarvelApp getInstance(Context context) {
        return (MarvelApp)context.getApplicationContext();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
