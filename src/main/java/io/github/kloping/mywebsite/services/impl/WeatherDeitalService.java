package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.github.kloping.mywebsite.domain.bo.medias.WeatherDetail;
import io.github.kloping.mywebsite.services.IWeather;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 */
@Service
public class WeatherDeitalService implements IWeather {
    @Override
    public WeatherDetail get(String keyword) throws Exception {
        String urlStr = String.format("https://www.tianqi.com/tianqi/ctiy?keyword=%s", keyword);
        Connection connection;
        Document doc;
        connection = Jsoup.connect(urlStr)
                .ignoreContentType(true)
                .header("Accept", "*/*")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36 Edg/94.0.992.50");
        ;
        doc = connection.get();
        JSONArray joarr = JSON.parseArray(doc.body().text());
        urlStr = joarr.getJSONObject(0).getString("url");
        connection = Jsoup.connect(urlStr)
                .ignoreContentType(true)
                .header("Accept", "*/*")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36 Edg/94.0.992.50");
        ;
        doc = connection.get();
        Element element = doc.getElementsByClass("weather_info").get(0);
        WeatherDetail detail = new WeatherDetail();
        detail.setAddress(element.getElementsByClass("name").get(0).text().replace("[切换城市]", ""));
        detail.setTime(element.getElementsByClass("week").get(0).text());
        String m1 = element.getElementsByClass("weather").text();

        String te = m1.substring(0, m1.indexOf("℃") + 1);
        detail.setTemperatureNow(te.trim());
        m1 = m1.substring(m1.indexOf("℃") + 1);
        String[] ss = m1.split("-|\\d+");
        String de = ss[0].trim();
        detail.setDescribed(de.trim());
        String tew = m1.substring(m1.indexOf(ss[0]) + ss[0].length());
        detail.setTemperature(tew.trim());

        Element melement = element.getElementsByClass("shidu").get(0);
        detail.setHumidity(melement.child(0).text());
        detail.setWind(melement.child(1).text().trim());
        detail.setUva(melement.child(2).text().trim());
        melement = element.getElementsByClass("kongqi").get(0);
        detail.setAir(melement.child(0).text());
        detail.setPm(melement.child(1).text());
        String sunM = melement.child(2).text().replaceAll(": ", ":");
        detail.setSunOn(sunM.split("\\s+")[0]);
        detail.setSunDown(sunM.split("\\s+")[1]);
        return detail;
    }
}
