package com.example.evgeniy.week11_12;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.evgeniy.week11_12.pojo.Event;
import com.example.evgeniy.week11_12.pojo.EventList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class EventListFragment extends Fragment {
    private static final String EVENT_LIST = "eventList";

    private EventList eventList;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    @BindView(R.id.fab_map)
    FloatingActionButton fab;

    private Unbinder unbinder;

    private FragmentListener fragmentListener;

    public EventListFragment() {
        // Required empty public constructor
    }

    public static EventListFragment newInstance(EventList eventList) {
        EventListFragment fragment = new EventListFragment();
        Bundle args = new Bundle();
        args.putParcelable(EVENT_LIST, eventList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            eventList = getArguments().getParcelable(EVENT_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
        EventsAdapter.ItemClickListener itemClickListener = this::showInfo;
        EventsAdapter eventsAdapter = new EventsAdapter(eventList.getEventList(), itemClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(eventsAdapter);
        fab.setOnClickListener(view1 -> showMap());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof FragmentListener) {
            fragmentListener = (FragmentListener) getActivity();
        }
    }

    private void showInfo(Event event) {
        fragmentListener.showInfo(event);
    }

    private void showMap() {
        fragmentListener.showMap();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    interface FragmentListener {
        void showInfo(Event event);

        void showMap(Event event);

        void showMap();

        void showList();
    }
}
