
package com.example.evgeniy.week11_12.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place implements Parcelable
{

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("event_lat")
    @Expose
    private String eventLat;
    @SerializedName("event_lng")
    @Expose
    private String eventLng;
    public final static Parcelable.Creator<Place> CREATOR = new Creator<Place>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        public Place[] newArray(int size) {
            return (new Place[size]);
        }

    }
            ;

    private Place(Parcel in) {
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.eventLat = ((String) in.readValue((String.class.getClassLoader())));
        this.eventLng = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Place() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEventLat() {
        return eventLat;
    }

    public void setEventLat(String eventLat) {
        this.eventLat = eventLat;
    }

    public String getEventLng() {
        return eventLng;
    }

    public void setEventLng(String eventLng) {
        this.eventLng = eventLng;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(city);
        dest.writeValue(address);
        dest.writeValue(eventLat);
        dest.writeValue(eventLng);
    }

    public int describeContents() {
        return 0;
    }

}
