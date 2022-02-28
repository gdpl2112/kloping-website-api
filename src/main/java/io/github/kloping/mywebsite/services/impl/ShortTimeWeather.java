package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.medias.WeatherM;
import io.github.kloping.mywebsite.services.IShortTimeWeather;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ShortTimeWeather implements IShortTimeWeather {
    @Override
    public WeatherM getWeather(String lng, String lat) {
        try {
            String urlStr = String.format(baseU1, lng, lat);
            Connection connection = Jsoup.connect(urlStr)
                    .ignoreContentType(true)
                    .header("Accept", "*/*")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36 Edg/94.0.992.50");
            ;
            Document document = connection.get();
            Elements elementsM = document.getElementsByTag("article");
            Elements elements;
            elements = elementsM.get(0).getElementsByClass("c-gap-right-small");
            String m2 = elements.get(1).text();
            elements = document.getElementsByTag("script");
            String jsonStr = elements.get(7).toString();
            int i1 = jsonStr.indexOf(">");
            int i2 = jsonStr.lastIndexOf("<");
            jsonStr = jsonStr.substring(i1 + 1, i2);
            JSONObject jsonObject = JSON.parseObject(jsonStr);

            WeatherM weatherM = new WeatherM();
            String[] strings = jsonObject.getJSONObject("data").getJSONObject("weatherCloud").getJSONArray("imgArr").toArray(new String[0]);
//            weatherM.setImgs(strings);
            weatherM.setIntro(jsonObject.getJSONObject("data").getJSONObject("nowcasting").getString("description"));
            weatherM.setName(jsonObject.getJSONObject("data").getJSONObject("nowcasting").getString("showCounty"));
            weatherM.setLng(lng);
            weatherM.setLat(lat);
            weatherM.setName(m2);
            return weatherM;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String baseU1 =
            "https://m.baidu.com/sf?pd=life_compare_weather&openapi=1&dspName=iphone&from_sf=1&resource_id=5135&weatherId=101220704&lng=%s&lat=%s&word=短时预报&title=短时预报&lid=9186109434066578388&referlid=9186109434066578388&ms=1&frsrcid=4982&frorder=1";

}
