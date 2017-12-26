package com.lifeistech.android.weatherapi.weatherHacks.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Masashi Hamaguchi on 2017/05/06.
 */

public class WeatherResponse {

    @SerializedName("pinpointLocations")
    public List<PinpointLocation> pinpointLocationList;

    @SerializedName("link")
    public String link;

    @SerializedName("forecasts")
    public List<Forecasts> forecastsList;

    @SerializedName("location")
    public Location location;

    @SerializedName("publicTime")
    public String publicTime;

    @SerializedName("copyright")
    public Copyright copyright;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public Description description;


    public WeatherResponse(List<PinpointLocation> pinpointLocationList, String link, List<Forecasts> forecastsList, Location location, String publicTime, Copyright copyright, String title, Description description) {
        this.pinpointLocationList = pinpointLocationList;
        this.link = link;
        this.forecastsList = forecastsList;
        this.location = location;
        this.publicTime = publicTime;
        this.copyright = copyright;
        this.title = title;
        this.description = description;
    }


    public List<PinpointLocation> getPinpointLocationList() {
        return pinpointLocationList;
    }

    public void setPinpointLocationList(List<PinpointLocation> pinpointLocationList) {
        this.pinpointLocationList = pinpointLocationList;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Forecasts> getForecastsList() {
        return forecastsList;
    }

    public void setForecastsList(List<Forecasts> forecastsList) {
        this.forecastsList = forecastsList;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    public Copyright getCopyright() {
        return copyright;
    }

    public void setCopyright(Copyright copyright) {
        this.copyright = copyright;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

}
