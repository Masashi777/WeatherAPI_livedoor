package com.lifeistech.android.weatherapi.weatherHacks.response.forecasts;

/**
 * Created by Masashi Hamaguchi on 2017/05/06.
 */

public class Image {

    public int width;
    public String url;
    public String title;
    public int height;


    public Image(int width, String url, String title, int height) {
        this.width = width;
        this.url = url;
        this.title = title;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
