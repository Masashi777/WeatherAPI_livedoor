package com.lifeistech.android.weatherapi;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Masashi Hamaguchi on 2017/12/26.
 */

public class GetCityClass extends AsyncTask<String, Void, Void>{

    // RSS
    private String requestURL;
    private GetCityCallbackListener listener;

    // インターフェイス
    public interface GetCityCallbackListener {
        public void onSuccess(String success);
        public void onFailed(String failed);
    }


    public void setValue(String requestURL, GetCityCallbackListener listener) {
        this.requestURL = requestURL;
        this.listener = listener;
    }

    // 処理
    @Override
    protected Void doInBackground(String... strings) {

        byte[] w = new byte[1024];
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;

        try {
            //HTTP接続のオープン
            URL url = new URL(requestURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            inputStream = connection.getInputStream();

            //バイト配列の読み込み
            outputStream = new ByteArrayOutputStream();
            while (true) {
                int size = inputStream.read(w);
                if (size <= 0) break;
                outputStream.write(w, 0, size);
            }
            outputStream.close();

            //HTTP接続のクローズ
            inputStream.close();
            connection.disconnect();

            Log.d("InputStream", outputStream.toString());
            listener.onSuccess(outputStream.toString());

        } catch (Exception e) {
            e.printStackTrace();

            listener.onFailed("Http Error");
        }

        return null;
    }

}
