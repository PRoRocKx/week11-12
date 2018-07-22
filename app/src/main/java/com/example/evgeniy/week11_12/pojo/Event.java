
package com.example.evgeniy.week11_12.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

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



}
