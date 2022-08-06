package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.medias.PositionM;
import io.github.kloping.mywebsite.entitys.medias.WeatherDetail;
import io.github.kloping.mywebsite.entitys.medias.WeatherM;
import io.github.kloping.mywebsite.services.IGetSongById;
import io.github.kloping.mywebsite.services.IShortTimeWeather;
import io.github.kloping.mywebsite.services.IWeather;
import io.github.kloping.mywebsite.services.IgetLngLat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author github-kloping
 */
@RestController
@RequestMapping("/api/get")
public class ApiSelectController {

    @Autowired
    IgetLngLat getLngLat;

    @Autowired
    IShortTimeWeather shortTimeWeather;

    @Autowired
    IWeather weather;

    @RequestMapping("/shortWeather")
    public WeatherM shortWea(HttpServletRequest request, String address) {
        try {
            PositionM positionM = getLngLat.get(address);
            String lng = positionM.getResult().getLocation().getLng().toString();
            String lat = positionM.getResult().getLocation().getLat().toString();
            WeatherM weatherM = shortTimeWeather.getWeather(lng, lat);
            weatherM.setLevel(positionM.getResult().getLevel());
            return weatherM;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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

    @Autowired
    @Qualifier("getKugouSongById")
    IGetSongById kugouSongGet;

    @Autowired
    @Qualifier("getQQSongById")
    IGetSongById qqSongGet;

    @Autowired
    @Qualifier("getNetEaseSongById")
    IGetSongById wySongGet;

    @RequestMapping("/subi")
    public String p3(@RequestParam("id") String id, @RequestParam("type") String type) {
        switch (type.trim()) {
            case "kugou":
                return kugouSongGet.getUrl(id);
            case "qq":
                return qqSongGet.getUrl(id);
            case "wy":
                return wySongGet.getUrl(id);
            default:
                return "{\"status\":-1}";
        }
    }
}
