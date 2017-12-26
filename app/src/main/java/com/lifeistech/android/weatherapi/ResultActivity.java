package com.lifeistech.android.weatherapi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lifeistech.android.weatherapi.weatherHacks.WeatherConnect;
import com.lifeistech.android.weatherapi.weatherHacks.WeatherRetrofit;
import com.lifeistech.android.weatherapi.weatherHacks.response.Weather;
import com.lifeistech.android.weatherapi.weatherHacks.response.WeatherResponse;
import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultActivity extends AppCompatActivity {

    // UI
    private String cityCode;

    // Layout
    // forecast
    private TextView[] date = new TextView[3];
    private TextView[] forecasts = new TextView[3];
    private ImageView[] image = new ImageView[3];
    private TextView[] max = new TextView[3];
    private TextView[] min = new TextView[3];

    private TextView areaInfo, description, publicTime;
    private TextView copyright, provider;

    private ListView listView;

    // コネクタ
    private WeatherConnect connect = new WeatherRetrofit();

    private final Weather weather = new Weather();
    private WeatherResponse weatherResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //ひもづけ
        areaInfo = (TextView) findViewById(R.id.areaInfo);

        // forecast
        date[0] = (TextView) findViewById(R.id.date1);
        date[1] = (TextView) findViewById(R.id.date2);
        date[2] = (TextView) findViewById(R.id.date3);

        forecasts[0] = (TextView) findViewById(R.id.forecasts1);
        forecasts[1] = (TextView) findViewById(R.id.forecasts2);
        forecasts[2] = (TextView) findViewById(R.id.forecasts3);

        image[0] = (ImageView) findViewById(R.id.image1);
        image[1] = (ImageView) findViewById(R.id.image2);
        image[2] = (ImageView) findViewById(R.id.image3);

        max[0] = (TextView) findViewById(R.id.max1);
        max[1] = (TextView) findViewById(R.id.max2);
        max[2] = (TextView) findViewById(R.id.max3);
        min[0] = (TextView) findViewById(R.id.min1);
        min[1] = (TextView) findViewById(R.id.min2);
        min[2] = (TextView) findViewById(R.id.min3);

        // Other Information

        description = (TextView) findViewById(R.id.description);
        publicTime = (TextView) findViewById(R.id.publicTime);

        copyright = (TextView) findViewById(R.id.copyright);
        provider = (TextView) findViewById(R.id.provider);

        listView =(ListView) findViewById(R.id.listview);

        // CityCodeの読み取り
        Intent intent = getIntent();
        cityCode = intent.getStringExtra("CityCode");
        Log.d("CityCode", String.valueOf(cityCode));

        // クエリ実行
        search(new SearchListener() {
            @Override
            public void finish() {
                // ListViewの高さ変更
                setListViewHeightBasedOnChildren(listView);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(weatherResponse.getPinpointLocationList().get(position).getLink()));
                startActivity(intent);
            }
        });

    }

    private interface SearchListener {
        public void finish();
    }

    public void search(final SearchListener listener) {

        weather.search(connect, cityCode, new WeatherConnect.WeatherSearchListener() {
            @Override
            public void onSuccess(String result) {

                //json中の{}を""に変換
                String regex = "null";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(result);
                String json = m.replaceAll("{\"celsius\":\"--\",\"fahrenheit\":\"--\"}");

                // String(中身はjson形式)から WeatherResponse型への変換
                Gson gson = new Gson();
                weatherResponse = gson.fromJson(json, WeatherResponse.class);

                Log.d("tag", json);
                Log.d("TAG", weatherResponse.getTitle());
                Log.d("TAG", "success!");

                // タイトルの設定
                setTitle(weatherResponse.getTitle());

                //セットする

                // forecast
                for (int i = 0; i < 2; i++) {
                    date[i].setText(weatherResponse.getForecastsList().get(i).getDateLabel());
                    forecasts[i].setText(weatherResponse.getForecastsList().get(i).getTelop());
                    Picasso.with(getApplicationContext()).load(weatherResponse.getForecastsList().get(i).getImage().getUrl()).into(image[i]);
                    max[i].setText(weatherResponse.getForecastsList().get(i).getTemperature().getMax().getCelsius() + "℃");
                    min[i].setText(weatherResponse.getForecastsList().get(i).getTemperature().getMin().getCelsius() + "℃");

                }

                description.setText(weatherResponse.getDescription().getText());
                publicTime.setText("発表時刻 " + weatherResponse.getPublicTime());

                copyright.setText(weatherResponse.getCopyright().getTitle());
                provider.setText(weatherResponse.getCopyright().getProviderList().get(0).getName());

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1);
                for (int i = 0; i < weatherResponse.getPinpointLocationList().size(); i++) {
                    adapter.add(weatherResponse.getPinpointLocationList().get(i).getName());
                    Log.d("TAG", weatherResponse.getPinpointLocationList().get(i).getName());
                }
                listView.setAdapter(adapter);


                Log.d("TAG", weatherResponse.getTitle());
                listener.finish();

            }

            @Override
            public void onFailed(String error) {
                Log.e("TAG", error);
            }
        });
    }


    // リスト数に応じてリストの高さを変更
    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
