package io.github.kloping.mywebsite.plugins.detail;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.douyin.DouYinPicData;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.github.kloping.mywebsite.plugins.Source.empty;

/**
 * @author github-kloping
 * @version 1.0
 */
public class DouyinPic0Detail {
    public static final Map<String, String> HEADERS = new LinkedHashMap<>();

    static {
        HEADERS.put("accept", "text/html");
        HEADERS.put("cookie", "__ac_nonce=063b6b30100698253d133; __ac_signature=_02B4Z6wo00f01yzes2gAAIDAeVw498UwYvss.rfAAKiL7a; __ac_referer=__ac_blank");
    }

    public static String[] getPics(String url) throws IOException {
//        DouyPic douyPic = null;
//        String s = getRedirectUrl(url);
//        s = getRedirectUrl(s);
//        int i1 = s.indexOf("/share/video/");
//        int i2 = s.indexOf("/?");
//        String id = s.substring(i1 + 13, i2);
//        douyPic = douyinPic0.pic(id);

        Document doc = empty.empty(url, HEADERS);
        Element e0 = doc.getElementsByTag("script").get(0);
        String text = e0.data();
        text = URLDecoder.decode(text);
        JSONObject jo = JSON.parseObject(text);
        jo.put("one",jo.get("1"));
        jo.put("thirty",jo.get("30"));
        jo.remove("1");
        jo.remove("30");
        DouYinPicData douyPic = jo.toJavaObject(DouYinPicData.class);
        io.github.kloping.mywebsite.entitys.douyin.Images[]
                images = douyPic.getThirty().getAweme().getDetail().getImages();
        String[] sss = new String[images.length];
        for (int i = 0; i < images.length; i++) {
            sss[i] = images[i].getUrlList()[images[i].getUrlList().length - 1];
        }
        return sss;
    }
}
