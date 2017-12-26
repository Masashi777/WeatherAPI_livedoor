package com.lifeistech.android.weatherapi.weatherHacks;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Masashi Hamaguchi on 2017/05/06.
 */

public class WeatherRetrofit extends WeatherConnect {

    public interface WeatherApiService {
        @GET("/v1")
        public void search(@Query("City") int cityCode,
                           Callback<Response> callback);
    }

    public void search(final int cityCode, final WeatherSearchListener listener) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(REQUEST_DOMAIN)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        WeatherApiService service = restAdapter.create(WeatherApiService.class);

        service.search(cityCode, new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {

                // 結果のJsonからString型のresultを作成
                BufferedReader reader = null;
                StringBuilder sb = new StringBuilder();
                try {
                    reader = new BufferedReader(
                            new InputStreamReader(response.getBody().in()));
                    String line;
                    try {
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String result = sb.toString();

                // 結果のJsonをString型にしてonSuccessに渡す。
                listener.onSuccess(result);

                Log.d("TAG", "success!");
            }

            @Override
            public void failure(RetrofitError error) {

                listener.onFailed(error.toString());
                Log.d("TAG", "failed!");

            }
        });
    }
}
