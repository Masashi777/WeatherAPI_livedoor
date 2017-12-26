package com.lifeistech.android.weatherapi.weatherHacks.response.temperature;

/**
 * Created by Masashi Hamaguchi on 2017/05/07.
 */

public class Min {

    public String celsius;
    public String fahrenheit;


    public Min(String celsius, String fahrenheit) {
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
    }


    public String getCelsius() {
        return celsius;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

    public String getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(String fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

}
