package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.ApiDetailM;
import io.github.kloping.mywebsite.entitys.NetMain;
import io.github.kloping.mywebsite.entitys.medias.PositionM;
import io.github.kloping.mywebsite.entitys.medias.WeatherDetail;
import io.github.kloping.mywebsite.entitys.medias.WeatherM;
import io.github.kloping.mywebsite.services.IGetVideo;
import io.github.kloping.mywebsite.services.IShortTimeWeather;
import io.github.kloping.mywebsite.services.IWeather;
import io.github.kloping.mywebsite.services.IgetLngLat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@RestController
@RequestMapping("/api")
public class ApiShowController {

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
    public WeatherDetail Weather(HttpServletRequest request, String address) {
        try {
            WeatherDetail weatherDetail = weather.get(address);
            return weatherDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final List<ApiDetailM> list = new CopyOnWriteArrayList<>();

    static {
        list.add(new ApiDetailM()
                .setName("搜歌")
                .setState("success")
                .setDesc("通过歌名获取,歌的封面,作者,歌曲直链,歌词,id")
                .setAddress(NetMain.rootPath + NetMain.getSongType)
        );
        list.add(new ApiDetailM()
                .setName("搜图")
                .setState("success")
                .setDesc("通过关键词获取图片直链")
                .setAddress(NetMain.rootPath + NetMain.getPic)
        );
        list.add(new ApiDetailM()
                .setName("解析图片")
                .setState("success")
                .setDesc("通过快手,抖音 短链接 获取无水印图片")
                .setAddress(NetMain.rootPath + NetMain.parsePic)
        );
        list.add(new ApiDetailM()
                .setName("天气")
                .setState("success")
                .setDesc("通过地名 获取当地天气详情")
                .setAddress(NetMain.rootPath + NetMain.getWeatherDetail)
        );
        list.add(new ApiDetailM()
                .setName("短时天气")
                .setState("success")
                .setDesc("通过地名 获取当地短时天气")
                .setAddress(NetMain.rootPath + NetMain.getWeatherShort)
        );
        list.add(new ApiDetailM()
                .setName("短视频搜索 (不稳定)")
                .setState("debug")
                .setDesc("搜索 快手或 哔哩的短视频")
                .setAddress(NetMain.rootPath + NetMain.mediaUrl)
        );
    }

    @RequestMapping("/getApiList")
    public List<ApiDetailM> m1() {
        return list;
    }

    @RequestMapping("/getCloudPics")
    public List<String> getCP() {
        try {
            String baseUrl = "http://www.nsmc.org.cn/NSMC/datalist/fy2_color.txt";
            String Mn = "http://img.nsmc.org.cn/CLOUDIMAGE/FY2/WXCL/%s";
            byte[] bytes = io.github.kloping.url.UrlUtils.getBytesFromHttpUrl(baseUrl);
            String[] pics = new String(bytes, "utf-8").trim().split(",");
            System.out.println(Arrays.toString(pics).replaceAll(",", "\n"));
            for (int i = 0; i < pics.length; i++)
                pics[i] = String.format(Mn, pics[i].trim()).trim();
            return Arrays.asList(pics);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Autowired
    IGetVideo getVideo;

    @RequestMapping("/getVideoByNameSt")
    public String getM1(String keyword, int st) {
        try {
            return "获取中...\n<br>预计下载时间2分钟\n<br>若访问失败,请重新请求\n<br>路径将为:" + NetMain.rootPath + getVideo.getSearVideo(keyword, st).substring(7);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "获取失败";
    }
}