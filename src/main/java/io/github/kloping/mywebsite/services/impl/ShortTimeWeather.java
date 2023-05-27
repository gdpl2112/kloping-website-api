package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.medias.WeatherM;
import io.github.kloping.mywebsite.services.IShortTimeWeather;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 */
@Service
public class ShortTimeWeather implements IShortTimeWeather {
    @Override
    public WeatherM getWeather(String lng, String lat) {
        try {
            String urlStr = String.format(BASEU1, lat, lng, System.currentTimeMillis());
            Connection connection = Jsoup.connect(urlStr).ignoreContentType(true).header("Accept", "*/*")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36 Edg/94.0.992.50");
            Document document = connection.get();
            String jsonStr = document.body().text();
            int r = jsonStr.indexOf("{");
            int e = jsonStr.lastIndexOf("}");
            jsonStr = jsonStr.substring(r, e + 1);
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            WeatherM weatherM = new WeatherM();
            weatherM.setIntro(jsonObject.getString("msg"));
            weatherM.setLng(lng);
            weatherM.setLat(lat);
            return weatherM;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String BASEU1 = "http://d3.weather.com.cn/webgis_rain_new/webgis/minute?lat=%s&lon=%s&callback=fc5m&_=%s";

}
