package com.lifeistech.android.weatherapi.weatherHacks.response.copyright;

/**
 * Created by Masashi Hamaguchi on 2017/05/07.
 */

public class Image {

    public int width;
    public String link;
    public String url;
    public String title;
    public int height;


    public Image(int width, String link, String url, String title, int height) {
        this.width = width;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
