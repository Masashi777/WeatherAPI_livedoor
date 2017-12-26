package com.lifeistech.android.weatherapi.weatherHacks.response;

/**
 * Created by Masashi Hamaguchi on 2017/05/06.
 */

public class PinpointLocation {

    public String link;
    public String name;

    public PinpointLocation(String link, String name) {
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
