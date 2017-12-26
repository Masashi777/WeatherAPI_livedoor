package com.lifeistech.android.weatherapi.weatherHacks;

import java.io.Serializable;

/**
 * Created by Masashi Hamaguchi on 2017/05/06.
 */

public abstract class WeatherConnect implements Serializable {

    public static final String REQUEST_DOMAIN = "http://weather.livedoor.com/forecast/webservice/json";

    public interface WeatherSearchListener {

        public void onSuccess(String result);

        public void onFailed(String error);

    }

    public abstract void search(final String cityCode, final WeatherSearchListener listener);

}
