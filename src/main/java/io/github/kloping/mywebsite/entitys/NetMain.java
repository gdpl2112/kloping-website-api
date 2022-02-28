package io.github.kloping.mywebsite.entitys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author github-kloping
 */
public class NetMain {

    public static String getNowIP2() {
        return getPublicIp();
    }

    public static String getPublicIp() {
        try {
            String jsonStr = Jsoup.connect("https://ip.cn/api/index?ip=&type=0")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36 Edg/97.0.1072.62")
                    .ignoreContentType(true).get().body().text();
            JSONObject jo = JSON.parseObject(jsonStr);
            return jo.getString("ip");
        } catch (IOException e) {
            e.printStackTrace();
            return "localhost";
        }
    }

    public static final String rootPath = "http://" + getNowIP2();

    //=================================
    public static final String mediaUrl = "/api/search/video?keyword=关键词&type=类型";
    public static final String _mediaUrl = "/api/search/video?keyword=海绵宝宝&type=bili";

    //=================================
    public static final String getPic = "/api/search/pic?keyword=关键词&num=数量&type=类型 ";
    public static final String _getPic = "/api/search/pic?keyword=原神&num=3&type=duit";

    //=================================
    public static final String parsePic = "/api/search/parseImgs?url=关键词&type=类型";
    public static final String _parsePic = "/api/search/parseImgs?url=https://v.kuaishouapp.com/s/MIaftqoZ&type=ks";

    //=================================
    public static final String getSongType = "/api/search/song?keyword=关键词&type=类型&n=7";
    public static final String _getSongType = "/api/search/song?keyword=清空&type=kugou";

    //=================================
    public static final String _getWeatherShort = "/api/shortWeather?address=合肥";
    public static final String getWeatherShort = "/api/shortWeather?address=地名";

    //=================================
    public static final String getWeatherDetail = "/api/weather?address=地名";
    public static final String _getWeatherDetail = "/api/weather?address=合肥";

    //====================================
    public static final String getVideo = "/api/getVideoByNameSt?keyword=片名&st=1";
    public static final String _getVideo = "/api/getVideoByNameSt?keyword=铠甲勇士刑天&st=1";
    //====================================
    //====================================
    //====================================
}
