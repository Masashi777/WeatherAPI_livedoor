package com.lifeistech.android.weatherapi.weatherHacks.response.copyright;

/**
 * Created by Masashi Hamaguchi on 2017/05/07.
 */

public class Provider {

    public String link;
    public String name;


    public Provider(String link, String name) {
        this.link = link;
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
