package com.lifeistech.android.weatherapi.weatherHacks.response;

import com.google.gson.annotations.SerializedName;
import com.lifeistech.android.weatherapi.weatherHacks.response.copyright.Image;
import com.lifeistech.android.weatherapi.weatherHacks.response.copyright.Provider;

import java.util.List;

/**
 * Created by Masashi Hamaguchi on 2017/05/06.
 */

public class Copyright {

    @SerializedName("provider")
    public List<Provider> providerList;
    public String link;
    public String title;
    public Image image;


    public Copyright(List<Provider> providerList, String link, String title, Image image) {
        this.providerList = providerList;
        this.link = link;
        this.title = title;
        this.image = image;
    }

    public List<Provider> getProviderList() {
        return providerList;
    }

    public void setProviderList(List<Provider> providerList) {
        this.providerList = providerList;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
