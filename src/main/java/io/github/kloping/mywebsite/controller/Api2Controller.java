package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.ApiDetail;
import io.github.kloping.mywebsite.entitys.ApiDetailM;
import io.github.kloping.mywebsite.entitys.idiom.M;
import io.github.kloping.mywebsite.entitys.medias.PositionM;
import io.github.kloping.mywebsite.entitys.medias.WeatherDetail;
import io.github.kloping.mywebsite.entitys.medias.WeatherM;
import io.github.kloping.mywebsite.services.IGetSongById;
import io.github.kloping.mywebsite.services.IShortTimeWeather;
import io.github.kloping.mywebsite.services.IWeather;
import io.github.kloping.mywebsite.services.IgetLngLat;
import io.github.kloping.mywebsite.services.impl.Idiom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static io.github.kloping.mywebsite.services.impl.Idiom.idiom;

/**
 * @author github-kloping
 */
@RestController
@RequestMapping("/api/get")
public class Api2Controller {
    static {
        ApiShowController.LIST.add(new ApiDetailM()
                .setName("查词语")
                .setState("success")
                .setDesc("判断是否是词语(可能不完全")
        );
        ApiShowController.LIST.add(new ApiDetailM()
                .setName("获取词语")
                .setState("success")
                .setDesc("获取随机一个四字词语")
        );
        //=============
        ApiShowDetailController.LIST.add(
                new ApiDetail()
                        .setName("查词语")
                        .setState("success")
                        .setDesc("判断是否是词语(可能不完全")
                        .setAddress("/api/get/idiom?word=词语")
                        .setDetail("判断是否是词语(可能不完全 参数 word ")
                        .setSimpleUrl("/api/get/idiom?word=为所欲为")
        );
        ApiShowDetailController.LIST.add(
                new ApiDetail()
                        .setName("查词语")
                        .setState("success")
                        .setDesc("获取随机一个四字词语")
                        .setAddress("/api/get/idiom/random")
                        .setDetail("获取随机一个四字词语")
                        .setSimpleUrl("/api/get/idiom/random")
        );
    }

    @RequestMapping("/idiom")
    public M p1(String word) {
        word = word.trim();
        char c1 = word.charAt(0);
        if (idiom.containsKey(c1)) {
            if (idiom.get(c1).contains(word)) {
                String[] strings = new String[4];
                int i = 0;
                for (char c : word.toCharArray()) {
                    strings[i++] = Idiom.getCharPinYin(c);
                }
                return new M().setState(1).setWord(word).setPinyin(strings);
            }
        }
        return new M().setState(-1).setPinyin(null).setWord(word);
    }

    @RequestMapping("/idiom/random")
    public String p2() {
        return Idiom.INSTANCE.getRandom();
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
}
