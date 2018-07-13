package br.com.wpgomes.treinamento.appcasttreinamento.api;

import android.util.Log;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wgomes on 11/07/16.
 */

public class AuthInterceptor implements Interceptor {
    private static final String TIMESTAMP_KEY = "ts";
    private static final String HASH_KEY = "hash";
    private static final String APIKEY_KEY = "apikey";
    private static final String TAG = AuthInterceptor.class.getSimpleName();

    private final String publicKey;
    private final String privateKey;
    private final AuthHashGenerator authHashGenerator = new AuthHashGenerator();

    public AuthInterceptor(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String hash = null;
        try {
            hash = authHashGenerator.generateHash(timestamp, publicKey, privateKey);
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "cannot generate the api key: ", e);
        }
        Request request = chain.request();
        HttpUrl url = request.url()
                .newBuilder()
                .addQueryParameter(TIMESTAMP_KEY, timestamp)
                .addQueryParameter(APIKEY_KEY, publicKey)
                .addQueryParameter(HASH_KEY, hash)
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}