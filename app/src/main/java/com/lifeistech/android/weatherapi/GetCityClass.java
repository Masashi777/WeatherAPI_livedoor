package com.lifeistech.android.weatherapi;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import com.lifeistech.android.weatherapi.cityCode.City;
import com.lifeistech.android.weatherapi.cityCode.Pref;
import com.lifeistech.android.weatherapi.cityCode.Warn;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Masashi Hamaguchi on 2017/12/26.
 */

public class GetCityClass extends AsyncTask<String, Void, String>{

    // RSS
    private String requestURL;
    private String rssString;
    private String title, link;
    private List<Pref> prefList = new ArrayList<>();
    private GetCityCallbackListener listener;

    // インターフェイス
    public interface GetCityCallbackListener {
        public void onSuccess(List<Pref> prefList, String title, String link);
        public void onFailed(String failed);
    }


    public void setValue(String requestURL, GetCityCallbackListener listener) {
        this.requestURL = requestURL;
        this.listener = listener;
    }

    // 処理
    @Override
    protected String doInBackground(String... strings) {

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
            rssString = outputStream.toString();

        } catch (Exception e) {
            e.printStackTrace();

            listener.onFailed("Http Error");
        }

        return null;

    }

    @Override
    protected void onPostExecute(String string) {
        // XMLパース
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(rssString));
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_TAG) {

                    if (parser.getName().equals("pref")) {
                        // pref
                        Pref pref = new Pref();
                        pref.setTitle(parser.getAttributeValue(null, "title"));

                        List<City> cityList = new ArrayList<City>();

                        while (true) {

                            if (eventType == XmlPullParser.START_TAG) {
                                if (parser.getName().equals("warn")) {
                                    // warn
                                    Warn warn = new Warn();
                                    warn.setTitle(parser.getAttributeValue(null, "title"));
                                    warn.setSource(parser.getAttributeValue(null, "source"));
                                    pref.setWarn(warn);
                                    Log.d("ADD WARN", warn.title);

                                } else if (parser.getName().equals("city")) {
                                    // city
                                    City city = new City();
                                    city.setTitle(parser.getAttributeValue(null, "title"));
                                    city.setId(parser.getAttributeValue(null, "id"));
                                    city.setSource(parser.getAttributeValue(null, "source"));

                                    cityList.add(city);
                                    Log.d("ADD CITY", city.title + city.id);

                                }

                            } else if (eventType == XmlPullParser.END_TAG) {
                                if (parser.getName().equals("pref")) {
                                    // pref
                                    pref.setCityList(cityList);
                                    prefList.add(pref);
                                    Log.d("ADD PREF", pref.title);
                                    break;
                                }
                            }

                            eventType = parser.next();
                        }

                    } else if (parser.getName().equals("ldWeather:source")) {
                        title = parser.getAttributeValue(null, "title");
                        link = parser.getAttributeValue(null, "link");
                    }

                } else if (eventType == XmlPullParser.END_TAG) {

                }

                eventType = parser.next();

            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();

            Log.e("Tag", "ParseError");

        } catch (IOException e) {
            e.printStackTrace();

            Log.e("Tag", "ParseError");
        }

        listener.onSuccess(prefList, title, link);
    }

}
