package com.example.evgeniy.week11_12.api;

import com.example.evgeniy.week11_12.pojo.EventList;


import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("v2/58fef1c7110000fc08f5fe34")
    Call<EventList> getData();
}
