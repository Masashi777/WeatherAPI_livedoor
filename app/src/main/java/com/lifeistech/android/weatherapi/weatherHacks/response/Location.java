package com.lifeistech.android.weatherapi.weatherHacks.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Masashi Hamaguchi on 2017/05/06.
 */

public class Location {

    public String city;
    public String area;
    public String prefecture;


    public Location(String city, String area, String prefecture) {
        this.city = city;
        this.area = area;
        this.prefecture = prefecture;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }
}
