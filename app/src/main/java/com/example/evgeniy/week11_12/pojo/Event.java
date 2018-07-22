
package com.example.evgeniy.week11_12.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Event implements Parcelable {

    public static String formatDate(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            DateFormat dateFormat2 = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
            return (dateFormat2.format(dateFormat.parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("event_title")
    @Expose
    private String eventTitle;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("place")
    @Expose
    private Place place;
    @SerializedName("event_phone")
    @Expose
    private String eventPhone;
    @SerializedName("event_site")
    @Expose
    private String eventSite;
    @SerializedName("planned")
    @Expose
    private Boolean planned;
    @SerializedName("event_price")
    @Expose
    private String eventPrice;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("event_rating")
    @Expose
    private Double eventRating;
    @SerializedName("min_age")
    @Expose
    private Integer minAge;
    public final static Parcelable.Creator<Event> CREATOR = new Creator<Event>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return (new Event[size]);
        }

    };

    private Event(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.eventTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.startDate = ((String) in.readValue((String.class.getClassLoader())));
        this.endDate = ((String) in.readValue((String.class.getClassLoader())));
        this.place = ((Place) in.readValue((Place.class.getClassLoader())));
        this.eventPhone = ((String) in.readValue((String.class.getClassLoader())));
        this.eventSite = ((String) in.readValue((String.class.getClassLoader())));
        this.planned = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.eventPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.picture = ((String) in.readValue((String.class.getClassLoader())));
        this.eventRating = ((Double) in.readValue((Double.class.getClassLoader())));
        this.minAge = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Event() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getEventPhone() {
        return eventPhone;
    }

    public void setEventPhone(String eventPhone) {
        this.eventPhone = eventPhone;
    }

    public String getEventSite() {
        return eventSite;
    }

    public void setEventSite(String eventSite) {
        this.eventSite = eventSite;
    }

    public Boolean getPlanned() {
        return planned;
    }

    public void setPlanned(Boolean planned) {
        this.planned = planned;
    }

    public String getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(String eventPrice) {
        this.eventPrice = eventPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getEventRating() {
        return eventRating;
    }

    public void setEventRating(Double eventRating) {
        this.eventRating = eventRating;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(eventTitle);
        dest.writeValue(startDate);
        dest.writeValue(endDate);
        dest.writeValue(place);
        dest.writeValue(eventPhone);
        dest.writeValue(eventSite);
        dest.writeValue(planned);
        dest.writeValue(eventPrice);
        dest.writeValue(picture);
        dest.writeValue(eventRating);
        dest.writeValue(minAge);
    }

    public int describeContents() {
        return 0;
    }

}