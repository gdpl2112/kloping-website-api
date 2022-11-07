package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.ks.RequestData;
import io.github.kloping.mywebsite.entitys.webApi.ks.DataResponse;
import io.github.kloping.mywebsite.services.IParseImg;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author github-kloping
 */
@Service
public class ParseGifImgImpl implements IParseImg {
    @Override
    public String[] parse(String url) throws Exception {
        DataResponse response = getDataResponse(url);
        List<String> list = new ArrayList<>();
        for (String u0 : response.getAtlas().getList()) {
            try {
                if (!u0.startsWith("http")) {
                    u0 = "https://p1.a.yximgs.com" + u0;
                }
                list.add(u0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list.toArray(new String[0]);
    }

    public DataResponse getDataResponse(String url) throws IOException {
        Connection connection = Jsoup.connect(url)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Linux; U; Android 12; zh-cn; PEGT10 Build/RKQ1.211103.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/90.0.4430.61 Mobile Safari/537.36 HeyTapBrowser/40.7.39.2");

        Document doc = connection.get();
        DataResponse response = getFromDoc(doc, connection.response().cookies());
        return response;
    }


    public static DataResponse getFromDoc(Document d, Map<String, String> cookieStore) throws IOException {
        Map<String, String> args = new HashMap<>();
        for (String s : d.location().substring(d.location().indexOf("?") + 1).split("&")) {
            String[] ss = s.split("=");
            args.put(ss[0], ss.length == 1 ? "" : ss[1]);
        }

        io.github.kloping.mywebsite.entitys.ks.RequestData requestData = new RequestData();
        try {
            for (Field declaredField : requestData.getClass().getDeclaredFields()) {
                String n0 = declaredField.getName();
                if (!args.containsKey(n0))
                    continue;
                declaredField.setAccessible(true);
                declaredField.set(requestData, args.get(n0));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        String data0 = JSON.toJSONString(requestData);

        Document document = Jsoup.connect("https://kph8gvfz.m.chenzhongtech.com/rest/wd/photo/info?kpn=NEBULA&captchaToken=")
                .ignoreHttpErrors(true).ignoreContentType(true)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-CN,zh;q=0.9")
                .header("Connection", "keep-alive")
                .header("Content-type", "application/json")
                .header("Origin", "https://kph8gvfz.m.chenzhongtech.com")
                .header("Referer", d.location())
                .header("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.4.4; en-US; XT1022 Build/KXC21.5-40) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.7.0.636 U3/0.8.0 Mobile Safari/534.30")
                .header("Content-length", String.valueOf(data0.length()))
                .cookies(cookieStore)
                .requestBody(data0)
                .post();

        String json0 = document.body().text();
        JSONObject jo = JSON.parseObject(json0);
        return jo.toJavaObject(DataResponse.class);
    }
}
