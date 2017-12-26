package com.lifeistech.android.weatherapi.weatherHacks.response;

/**
 * Created by Masashi Hamaguchi on 2017/05/06.
 */

public class Description {

    public String text;
    public String publicTime;


    public Description(String text, String publicTime) {
        this.text = text;
        this.publicTime = publicTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }
}

