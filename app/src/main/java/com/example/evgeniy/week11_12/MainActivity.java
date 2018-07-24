package com.example.evgeniy.week11_12;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.evgeniy.week11_12.pojo.Event;
import com.example.evgeniy.week11_12.pojo.EventList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements EventListFragment.FragmentListener, OnMapReadyCallback, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {

    private static final Float MAP_ZOOM_LIST = 10.0f;
    private static final Float MAP_ZOOM_ELEMENT = 14.0f;

    @BindView(android.R.id.content)
    View view;

    private EventList eventList;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            loadEvents();
        }
    }

    private void showFirstList() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, EventListFragment.newInstance(eventList), EventListFragment.class.getSimpleName())
                .disallowAddToBackStack()
                .commit();
    }

    private void loadEvents() {
        App.getApi().getData().enqueue(new Callback<EventList>() {
            @Override
            public void onResponse(Call<EventList> call, Response<EventList> response) {
                if (response.isSuccessful()) {
                    eventList = response.body();
                    showFirstList();
                } else {
                    if (view != null) {
                        try {
                            Snackbar.make(view, response.errorBody().string(), Snackbar.LENGTH_INDEFINITE)
                                    .setAction(R.string.repeat, (view -> loadEvents()))
                                    .show();
                        } catch (IOException | NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<EventList> call, Throwable t) {
                if (view != null) {
                    Snackbar.make(view, R.string.load_fail, Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.repeat, (view -> loadEvents()))
                            .show();
                }
            }
        });
    }

    private void addMarker(GoogleMap googleMap, Event event) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(event.getPlace().getEventLat(), event.getPlace().getEventLng()))
                .title(event.getEventTitle()))
                .setTag(event);
    }

    private void moveMapCamera(GoogleMap googleMap, Event event, Float zoom) {
        LatLng latLng = new LatLng(event.getPlace().getEventLat(), event.getPlace().getEventLng());
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (event != null) {
            addMarker(googleMap, event);
            moveMapCamera(googleMap, event, MAP_ZOOM_ELEMENT);
        } else {
            if (eventList != null) {
                for (Event event : eventList.getEventList()) {
                    addMarker(googleMap, event);
                }
                if (eventList.getEventList().size() > 0) {
                    moveMapCamera(googleMap, eventList.getEventList().get(0), MAP_ZOOM_LIST);
                }
            }
        }
        googleMap.setInfoWindowAdapter(this);
        googleMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }


    @Override
    public View getInfoContents(Marker marker) {
        View view = getLayoutInflater().inflate(R.layout.info_window, null);
        RatingBar eventRating = view.findViewById(R.id.eventRating);
        ImageView picture = view.findViewById(R.id.picture);
        TextView title = view.findViewById(R.id.title);
        TextView minAge = view.findViewById(R.id.minAge);
        TextView startDate = view.findViewById(R.id.startDate);
        TextView endDate = view.findViewById(R.id.endDate);
        TextView place = view.findViewById(R.id.place);

        Event event = (Event) marker.getTag();
        eventRating.setRating(event.getEventRating());
        title.setText(event.getEventTitle());
        minAge.setText(Event.formatMinAge(event.getMinAge()));
        place.setText(event.getPlace().getAddress());
        startDate.setText(Event.formatDate(event.getStartDate()));
        endDate.setText(Event.formatDate(event.getEndDate()));
        GlideApp.with(view)
                .load(event.getPicture())
                .placeholder(R.color.placeholder)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        marker.showInfoWindow();
                        return true;
                    }
                })
                .into(picture);

        return view;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        showInfo((Event) marker.getTag());
    }

    @Override
    public void showInfo(Event event) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, InfoFragment.newInstance(event), InfoFragment.class.getSimpleName())
                .addToBackStack(InfoFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void showList() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, EventListFragment.newInstance(eventList), EventListFragment.class.getSimpleName())
                .addToBackStack(EventListFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void showMap() {
        event = null;
        showMapFragment();
    }

    @Override
    public void showMap(Event event) {
        this.event = event;
        showMapFragment();
    }


    private void showMapFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, MyMapFragment.newInstance(), MyMapFragment.class.getSimpleName())
                .addToBackStack(MyMapFragment.class.getSimpleName())
                .commit();
    }
}
