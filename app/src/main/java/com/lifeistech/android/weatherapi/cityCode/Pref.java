package com.lifeistech.android.weatherapi.cityCode;

import java.util.List;

/**
 * Created by Masashi Hamaguchi on 2017/12/26.
 */

public class Pref {

    public String title;
    public Warn warn;
    public List<City> cityList;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Warn getWarn() {
        return warn;
    }

    public void setWarn(Warn warn) {
        this.warn = warn;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }


}
