package com.example.evgeniy.week11_12;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evgeniy.week11_12.pojo.Event;
import com.example.evgeniy.week11_12.pojo.EventList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    private List<Event> events;
    private final ItemClickListener mListener;

    public EventsAdapter(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    public EventsAdapter(List<Event> events, ItemClickListener mListener) {
        this.events = events;
        this.mListener = mListener;
    }

    public void setEvents(EventList events) {
        this.events = events.getEventList();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.ViewHolder holder, int position) {
        holder.bind(events.get(position));
    }

    @Override
    public int getItemCount() {
        if (events == null) {
            return 0;
        }
        return events.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.city)
        TextView city;

        @BindView(R.id.picture)
        ImageView picture;
        @BindView(R.id.favorite)
        ImageView favorite;

        private final ItemClickListener mListener;

        ViewHolder(View itemView, ItemClickListener mListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mListener = mListener;
            itemView.setOnClickListener(this);
        }


        void bind(Event event) {
            title.setText(event.getEventTitle());
            date.setText(Event.formatDate(event.getStartDate()));
            city.setText(event.getPlace().getCity());
            GlideApp.with(itemView.getContext())
                    .load(event.getPicture())
                    .placeholder(R.color.placeholder)
                    .error(R.color.placeholder)
                    .into(picture);
            if (event.getPlanned()) {
                favorite.setVisibility(View.VISIBLE);
            } else {
                favorite.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(events.get(getAdapterPosition()));
        }
    }

    public interface ItemClickListener {

        void onClick(Event event);
    }
}
