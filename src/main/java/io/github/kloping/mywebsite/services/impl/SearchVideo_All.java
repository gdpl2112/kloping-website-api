package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import io.github.kloping.mywebsite.entitys.getVideo.VMain;
import io.github.kloping.mywebsite.services.IGetVideo;
import io.github.kloping.string.StringUtils;
import io.github.kloping.video.M3U8Utils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.File;

import static io.github.kloping.mywebsite.utils.MyUtils.threads;

@Service
public class SearchVideo_All implements IGetVideo {

    public static final String searVideo = "https://a1.m1907.cn/api/v/?z=0814d5a17095bfc3591ccc6d13e19f58&jx=%s&s1ig=11399&g=vod1.bd";

    @Override
    public String getSearVideo(String keyword, int st) throws Exception {
        String uuid = "./files/" + keyword + "/" + st + ".mp4";
        File file = new File(uuid);
        if (file.exists() && file.length() > 10) return uuid;
        Connection connection = Jsoup.connect(String.format(searVideo, keyword))
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36 Edg/95.0.1020.40");

        Document document = connection.get();
//        Document document = connection.proxy("59.124.224.180", 4378).get();
        String jsonStr = document.body().text();
        VMain vMain = JSON.parseObject(jsonStr, VMain.class);
        String m3u8Url = vMain.getData()[0].getSource().getEps()[st - 1].getUrl();
        connection = Jsoup.connect(m3u8Url).ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36 Edg/95.0.1020.40");

        document = connection.get();
        String text = document.body().text();
        String[] m3u8Str = null;
        m3u8Str = text.split(" ");
        m3u8Str = StringUtils.filterStartWith(m3u8Str, "#");
        while (m3u8Str.length == 1) {
            String n1 = "https://vod1.bdzybf1.com" + m3u8Str[0];
            System.out.print("connect " + n1);
            connection = Jsoup.connect(n1).ignoreContentType(true)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36 Edg/95.0.1020.40");
            document = connection.get();
            text = document.body().text();
            System.out.println("Out =>" + text);
            m3u8Str = text.split(" ");
            m3u8Str = StringUtils.filterStartWith(m3u8Str, "#");
        }
        try {
            new File(uuid).getParentFile().mkdirs();
            new File(uuid).createNewFile();
            M3U8Utils m3 = new M3U8Utils(m3u8Str, new File(uuid));
            threads.execute(m3::run);
            return uuid;
        } catch (Exception e) {
            e.printStackTrace();
            new File(uuid).deleteOnExit();
        }
        return "err";
    }
}
