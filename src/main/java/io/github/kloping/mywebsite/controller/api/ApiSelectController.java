package io.github.kloping.mywebsite.controller.api;

import io.github.kloping.mywebsite.domain.bo.medias.WeatherDetail;
import io.github.kloping.mywebsite.domain.bo.medias.WeatherM;
import io.github.kloping.mywebsite.domain.bo.position.PositionInfo;
import io.github.kloping.mywebsite.services.IShortTimeWeather;
import io.github.kloping.mywebsite.services.IWeather;
import io.github.kloping.mywebsite.services.IgetLngLat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
