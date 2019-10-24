package br.com.wpgomes.treinamento.appcasttreinamento.ui.fragments;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.List;

import br.com.wpgomes.treinamento.appcasttreinamento.api.EventApi;
import br.com.wpgomes.treinamento.appcasttreinamento.model.Event;
import br.com.wpgomes.treinamento.appcasttreinamento.ui.adapters.EventAdapter;
import br.com.wpgomes.treinamento.appcasttreinamento.R;


public class EventFragment extends Fragment {

    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private Boolean isTablet;
    private WebView webView;


    public static Fragment newInstance() {
        return new EventFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isTablet = getResources().getBoolean(R.bool.is_tablet);
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);

        if (isTablet) {
            webView = (WebView) view.findViewById(R.id.webview_event_detail_tablet);
        }


        getEvents(isTablet);
    }


    private void getEvents(final Boolean isTablet) {

        final EventApi eventApi = new EventApi(getActivity());
        eventApi.events(new EventApi.OnEventListener() {
            @Override
            public void onEventListener(final List<Event> events, int errorCode) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (events != null) {
                            if (isTablet) {
                                eventAdapter = new EventAdapter(getActivity(), events, recyclerView,
                                        isTablet, webView);
                            } else {
                                eventAdapter = new EventAdapter(getActivity(), events, recyclerView,
                                        isTablet, null);
                            }
                            recyclerView.setAdapter(eventAdapter);
                        } else {
                            Toast.makeText(getActivity(), R.string.msg_error_generic,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
