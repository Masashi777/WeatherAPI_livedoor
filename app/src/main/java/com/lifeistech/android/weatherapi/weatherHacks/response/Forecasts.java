package com.lifeistech.android.weatherapi.weatherHacks.response;

import com.lifeistech.android.weatherapi.weatherHacks.response.forecasts.Image;
import com.lifeistech.android.weatherapi.weatherHacks.response.forecasts.Temperature;

/**
 * Created by Masashi Hamaguchi on 2017/05/06.
 */

public class Forecasts {

    public String dateLabel;
    public String telop;
    public String date;
    public Temperature temperature;
    public Image image;


    public Forecasts(String dateLabel, String telop, String date, Temperature temperature, Image image) {
        this.dateLabel = dateLabel;
        this.telop = telop;
        this.date = date;
        this.temperature = temperature;
        this.image = image;
    }


    public String getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(String dateLabel) {
        this.dateLabel = dateLabel;
    }

    public String getTelop() {
        return telop;
    }

    public void setTelop(String telop) {
        this.telop = telop;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
