package com.lifeistech.android.weatherapi;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.lifeistech.android.weatherapi.cityCode.City;
import com.lifeistech.android.weatherapi.cityCode.Pref;
import com.lifeistech.android.weatherapi.cityCode.Warn;
import com.lifeistech.android.weatherapi.GetCityClass.GetCityCallbackListener;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Layout
    private Spinner prefSpinner, citySpinner;
    private Button button;

    // UI
    private List<Pref> prefList = new ArrayList<Pref>();
    private String title;
    private String link;
    private boolean load;
    private City activeCity;

    // RSS
    private final static String rssURL = "http://weather.livedoor.com/forecast/rss/primary_area.xml";
    private GetCityClass getCityClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ひもづけ
        prefSpinner = (Spinner) findViewById(R.id.prefSpinner);
        citySpinner = (Spinner) findViewById(R.id.citySpinner);
        button = (Button) findViewById(R.id.sarchBtn);

        // タイトルの設定
        setTitle("天気予報");


        // 都市一覧の取得
        getCityClass = new GetCityClass();
        getCityClass.setValue(rssURL, new GetCityCallbackListener() {
            @Override
            public void onSuccess(String success) {
                parseCity(success);
            }

            @Override
            public void onFailed(String failed) {

            }
        });
        getCityClass.execute();


        if (!load) {
            //読み込み失敗
        }


        // リスナー
        prefSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // citySpinnerのセット
                ArrayAdapter<String> cityAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);
                for (int i = 0; i < prefList.get(position).getCityList().size(); i++) {
                    cityAdapter.add(prefList.get(position).getCityList().get(i).getTitle());
                }
                citySpinner.setAdapter(cityAdapter);

                // activeCity
                activeCity = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // activeCity
                activeCity = prefList.get(prefSpinner.getSelectedItemPosition()).getCityList().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (activeCity != null) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("CityCode", activeCity.getId());
                    startActivity(intent);

                } else {
                    // no selected
                    Snackbar.make(v, "都市を選択してください", Snackbar.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void parseCity(String rssString) {

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
            load = false;

        } catch (IOException e) {
            e.printStackTrace();

            Log.e("Tag", "ParseError");
            load = false;
        }

        load = true;

//        Log.e("exp", prefList.get(40).title + prefList.get(40).getCityList().get(0).getTitle());

        ArrayAdapter<String> prefAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);
        for (int i = 0; i < prefList.size(); i++) {
            prefAdapter.add(prefList.get(i).getTitle().toString());
        }
        prefSpinner.setAdapter(prefAdapter);

    }
}
