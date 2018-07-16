package br.com.wpgomes.treinamento.appcasttreinamento.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import br.com.wpgomes.treinamento.appcasttreinamento.R;
import br.com.wpgomes.treinamento.appcasttreinamento.model.Event;
import br.com.wpgomes.treinamento.appcasttreinamento.model.MarvelUrl;
import br.com.wpgomes.treinamento.appcasttreinamento.util.Constants;

public class EventDetailActivity extends BaseActivity {

    private Event event;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_detail);
        WebView webView = (WebView) findViewById(R.id.webview_event_detail);

        if (getIntent().hasExtra("event")) {
            event = (Event) getIntent().getSerializableExtra("event");
            for (MarvelUrl url : event.urls
                    ) {
                if (url.type.equals(Constants.WIKI)) {
                    webView.loadUrl(url.url);
                }

            }
        }
    }

}
