package com.lifeistech.android.weatherapi.weatherHacks.response;

import com.lifeistech.android.weatherapi.weatherHacks.WeatherConnect;

/**
 * Created by Masashi Hamaguchi on 2017/05/06.
 */

public class Weather {

    public Weather() {

    }

    public void search(WeatherConnect connect, int cityCode, final WeatherConnect.WeatherSearchListener listener) {

        connect.search(cityCode, listener);

    }
}
