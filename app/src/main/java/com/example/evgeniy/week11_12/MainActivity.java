package com.example.evgeniy.week11_12;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.evgeniy.week11_12.pojo.EventList;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(android.R.id.content)
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            loadEvents();
        }
    }


    private void showEventList(EventList eventList) {
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
                    showEventList(response.body());
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

}
