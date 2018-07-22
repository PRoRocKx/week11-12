package com.example.evgeniy.week11_12.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventList implements Parcelable {
    @SerializedName("events")
    private final List<Event> eventList;

    private EventList(Parcel in) {
        eventList = in.createTypedArrayList(Event.CREATOR);
    }

    public static final Creator<EventList> CREATOR = new Creator<EventList>() {
        @Override
        public EventList createFromParcel(Parcel in) {
            return new EventList(in);
        }

        @Override
        public EventList[] newArray(int size) {
            return new EventList[size];
        }
    };

    public List<Event> getEventList() {
        return eventList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(eventList);
    }
}
