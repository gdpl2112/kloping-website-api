package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.medias.PositionM;
import io.github.kloping.mywebsite.services.IgetLngLat;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 */
@Service
public class getLngLatImpl implements IgetLngLat {
    @Override
    public PositionM get(String address) throws Exception {
        String urlStr = String.format(u1, address);
        Connection connection = Jsoup.connect(urlStr)
                .ignoreContentType(true)
                .header("Accept", "*/*")
                .header("Connection", "keep-alive")
                .header("Referer", "https://maplocation.sjfkai.com/")
                .header("Host", "api.map.baidu.com")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36 Edg/94.0.992.50");
        Document doc = connection.get();
        String t1 = doc.body().text();
        int i1 = t1.indexOf("({");
        int i2 = t1.lastIndexOf("})");
        String s2 = t1.substring(i1 + 1, i2 + 1);
        JSONObject jo = JSON.parseObject(s2);
        return jo.toJavaObject(PositionM.class);
    }

    private static String u1 = "https://api.map.baidu.com/geocoding/v3/?address=%s&output=json&ak=gQsCAgCrWsuN99ggSIjGn5nO&callback=showLocation1";

}
