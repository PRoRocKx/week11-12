package com.example.evgeniy.week11_12;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evgeniy.week11_12.pojo.Event;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class InfoFragment extends Fragment {
    private static final String EVENT = "event";

    private Event event;

    @BindView(R.id.picture)
    ImageView picture;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.startDate)
    TextView startDate;
    @BindView(R.id.endDate)
    TextView endDate;
    @BindView(R.id.place)
    TextView place;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.site)
    TextView site;
    @BindView(R.id.price)
    TextView price;

    private Unbinder unbinder;

    public InfoFragment() {
    }


    public static InfoFragment newInstance(Event event) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(EVENT, event);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            event = getArguments().getParcelable(EVENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (event != null) {
            GlideApp.with(this)
                    .load(event.getPicture())
                    .placeholder(R.color.placeholder)
                    .error(R.color.placeholder)
                    .into(picture);
            title.setText(event.getEventTitle());
            startDate.setText(Event.formatDate(event.getStartDate()));
            endDate.setText(Event.formatDate(event.getEndDate()));
            place.setText(event.getPlace().getAddress());
            phone.setText(event.getEventPhone());
            site.setText(event.getEventSite());
            price.setText(event.getEventPrice());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
