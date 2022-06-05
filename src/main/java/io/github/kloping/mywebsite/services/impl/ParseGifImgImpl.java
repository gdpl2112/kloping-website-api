package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.webApi.ks.data0.DataRespon;
import io.github.kloping.mywebsite.entitys.webApi.ks.data0.Feeds;
import io.github.kloping.mywebsite.services.IParseImg;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author github-kloping
 */
@Service
public class ParseGifImgImpl implements IParseImg {
    @Override
    public String[] parse(String url) throws Exception {
        Connection connection = Jsoup.connect(url)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Linux; U; Android 12; zh-cn; PEGT10 Build/RKQ1.211103.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/90.0.4430.61 Mobile Safari/537.36 HeyTapBrowser/40.7.39.2");

        Document doc = connection.get();
        DataRespon respon = getFromDoc(doc);
        List<String> list = new ArrayList<>();
        for (Feeds feed : respon.getFeeds()) {
            try {
                list.add(feed.getCoverUrls()[0].getUrl());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list.toArray(new String[0]);
    }

    public static final String FORMAT = "{\"count\":39,\"requestType\":1,\"forwardUserId\":\"307600798\",\"photoId\":\"%s\",\"expTag\":\"\",\"sourceFrom\":1}\n";

    public static DataRespon getFromDoc(Document d) throws IOException {
        String lo = d.location();
        lo = lo.substring(0, lo.indexOf("?"));
        lo = lo.substring(lo.lastIndexOf("/") + 1);
        String data0 = String.format(FORMAT, lo);
        Document document = Jsoup.connect("https://kphm5nf3.m.chenzhongtech.com/rest/wd/feed/recommend")
                .ignoreHttpErrors(true).ignoreContentType(true)
                .header("Accept", "*/*")
                .header("content-type", "application/json").header("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.4.4; en-US; XT1022 Build/KXC21.5-40) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.7.0.636 U3/0.8.0 Mobile Safari/534.30")
                .header("content-length", String.valueOf(FORMAT.length()))
                .requestBody(data0)
                .post();
        JSONObject jo = JSON.parseObject(document.body().text());
        return jo.toJavaObject(DataRespon.class);
    }
}
