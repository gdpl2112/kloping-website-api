package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.services.IParseImg;
import io.github.kloping.mywebsite.services.impl.ParseGifImgImpl0;
import io.github.kloping.mywebsite.services.impl.ParseKsVoiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

/**
 * @author github.kloping
 */
@RestController
public class ApiParseController {

    ParseGifImgImpl0 ksImg = new ParseGifImgImpl0();
    ParseKsVoiceImpl ksVoice = new ParseKsVoiceImpl();

    @Qualifier("parseDouyImgImpl")
    @Autowired
    IParseImg parseImgDy;

    @RequestMapping("/parseImgs")
    public List<String> parseImg(HttpServletRequest request, String url, @RequestParam(required = false) String type) {
        if (type == null) {
            if (url.contains("douyin")) type = "dy";
            else if (url.contains("kuaishou")) type = "ks";
        }
        try {
            switch (type) {
                case "ks":
                    return Arrays.asList(ksImg.parse(url.trim()));
                case "dy":
                    return Arrays.asList(parseImgDy.parse(url.trim()));
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/parseVoice")
    public Object parseVoice(String url) throws IOException {
        if (url.contains("kuaishou")) {
            return "https://p4.a.yximgs.com" + ksVoice.getDataResponse(url).getAtlas().getMusic();
        } else if (url.contains("douyin")) {
            Document doc0 = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 Edg/107.0.1418.35")
                    .get();
            String all = doc0.html();
            String u0 = null;
            int i = all.indexOf("%7B%22src%22%3A%22");
            all = all.substring(i + "%7B%22src%22%3A%22".length());
            u0 = all.substring(0, all.indexOf("%22"));
            u0 = URLDecoder.decode(u0);
            return "http:" + u0;
        }
        return "暂不支持该类型的网站的解析";
    }

    @RequestMapping("/parseVideo")
    public Object parseVideo(String url) {
        return null;
    }
}
