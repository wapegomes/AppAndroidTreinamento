package br.com.wpgomes.treinamento.appcasttreinamento.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.wpgomes.treinamento.appcasttreinamento.R;
import br.com.wpgomes.treinamento.appcasttreinamento.model.Event;
import br.com.wpgomes.treinamento.appcasttreinamento.ui.activities.EventDetailActivity;

/**
 * Created by wgomes on 22/06/16.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private Context context;
    private List<Event> events;
    private RecyclerView recyclerView;
    private Boolean isTablet;
    private WebView webView;

    public EventAdapter(Context context, List<Event> eventList, RecyclerView recyclerView, boolean isTablet, WebView webView) {
        this.context = context;
        this.events = eventList;
        this.recyclerView = recyclerView;
        this.isTablet = isTablet;
        this.webView = webView;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_event, parent, false);
        EventAdapter.ViewHolder viewHolder = new EventAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {

        Event event = events.get(position);
        holder.eventTitle.setText(event.getTitle());
//        Picasso.get().load(event.getImgUrl()).centerCrop().resize(400, 400).into(holder.eventImg);

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView eventImg;
        TextView eventTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            eventImg = (ImageView) itemView.findViewById(R.id.event_img);
            eventTitle = (TextView) itemView.findViewById(R.id.event_title);

            itemView.setOnClickListener(onClickListener);
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildAdapterPosition(v);
                Event event = events.get(position);
                if (!isTablet) {
                    Intent intent = new Intent(context, EventDetailActivity.class);
                    intent.putExtra("event", event);
                    context.startActivity(intent);
                }else{
//                    webView.loadUrl(event.getUrl());
                }
            }
        };

    }
}
