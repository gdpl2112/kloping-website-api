package io.github.kloping.mywebsite.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.domain.bo.medias.WeatherDetail;
import io.github.kloping.mywebsite.domain.bo.medias.WeatherM;
import io.github.kloping.mywebsite.domain.bo.position.PositionInfo;
import io.github.kloping.mywebsite.services.IShortTimeWeather;
import io.github.kloping.mywebsite.services.IWeather;
import io.github.kloping.mywebsite.services.IgetLngLat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @author github-kloping
 */
@RestController
@RequestMapping("/api/get")
public class ApiGetController {

    @Autowired
    IgetLngLat getLngLat;

    @Autowired
    IShortTimeWeather shortTimeWeather;

    @RequestMapping("/shortWeather")
    public WeatherM shortWea(HttpServletRequest request, String address) {
        try {
            PositionInfo info = getLngLat.get(address);
            String lng = info.getDetail().getPointx();
            String lat = info.getDetail().getPointy();
            WeatherM weatherM = shortTimeWeather.getWeather(lng, lat);
            weatherM.setName(info.getAllName());
            weatherM.setLevel("暂无数据");
            return weatherM;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Autowired
    IWeather weather;

    @RequestMapping("/weather")
    public WeatherDetail weather(HttpServletRequest request, String address) {
        try {
            WeatherDetail weatherDetail = weather.get(address);
            return weatherDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/163host")
    public Object host() throws Exception {
        Document doc0 = Jsoup.connect("https://music.163.com/discover/toplist?id=3778678")
                .header("Accept", "text/html;")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header("Connection", "Keep-Alive")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67")
                .get();
        Element div = doc0.getElementById("song-list-pre-data");
        JSONArray arr = JSON.parseArray(div.text());
        List<JSONObject> list = new LinkedList<>();
        for (Object o1 : arr) {
            try {
                JSONObject i1 = (JSONObject) o1;
                JSONObject o0 = new JSONObject();
                o0.put("name", i1.getString("name"));
                o0.put("id", i1.get("id"));
                String artist = null;
                for (Object e1 : i1.getJSONArray("artists")) {
                    JSONObject i0 = (JSONObject) e1;
                    if (artist != null) artist = artist + "/" + i0.getString("name");
                    else artist = i0.getString("name");
                }
                o0.put("artist", artist);
                list.add(o0);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return list;
    }
}
