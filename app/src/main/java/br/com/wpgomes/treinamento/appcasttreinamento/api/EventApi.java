package br.com.wpgomes.treinamento.appcasttreinamento.api;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import br.com.wpgomes.treinamento.appcasttreinamento.model.Event;
import br.com.wpgomes.treinamento.appcasttreinamento.model.MarvelCollection;
import br.com.wpgomes.treinamento.appcasttreinamento.model.MarvelResponse;
import br.com.wpgomes.treinamento.appcasttreinamento.util.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class EventApi extends BaseApi {

    public EventApi(Context context) {
        super(context);
    }

    public void events(final OnEventListener onEventListener) {


        Request request = new Request.Builder()
                .url(Constants.API_EVENT)
                .get().build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (onEventListener != null) {
                    onEventListener.onEventListener(null, 500);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (onEventListener == null) {
                    return;
                }

                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    MarvelResponse<MarvelCollection<Event>> events = gson.fromJson
                            (response.body().charStream(),
                                    new TypeToken<MarvelResponse<MarvelCollection<Event>>>() {
                                    }.getType());

                    onEventListener.onEventListener(events.data.results, 0);
                } else {
                    onEventListener.onEventListener(null, response.code());
                }
            }

        });
    }

    public interface OnEventListener {
        void onEventListener(List<Event> events, int errorCode);
    }
}



