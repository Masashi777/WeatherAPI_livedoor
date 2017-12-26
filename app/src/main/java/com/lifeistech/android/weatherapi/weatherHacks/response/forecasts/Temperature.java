package com.lifeistech.android.weatherapi.weatherHacks.response.forecasts;

import com.lifeistech.android.weatherapi.weatherHacks.response.temperature.Min;
import com.lifeistech.android.weatherapi.weatherHacks.response.temperature.Max;

/**
 * Created by Masashi Hamaguchi on 2017/05/06.
 */

public class Temperature {

    public Min min;
    public Max max;


    public Temperature(Min min, Max max) {
        this.min = min;
        this.max = max;
    }


    public Min getMin() {
        return min;
    }

    public void setMin(Min min) {
        this.min = min;
    }

    public Max getMax() {
        return max;
    }

    public void setMax(Max max) {
        this.max = max;
    }

}
