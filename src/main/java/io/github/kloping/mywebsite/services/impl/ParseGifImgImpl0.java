package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.services.IParseImg;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author github-kloping
 */
@Service
public class ParseGifImgImpl0 implements IParseImg {

    public ParseGifImgImpl gifImg = new ParseGifImgImpl();

    @Override
    public String[] parse(String url) throws Exception {
        Connection connection = Jsoup.connect(url)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Linux; U; Android 12; zh-cn; PEGT10 Build/RKQ1.211103.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/90.0.4430.61 Mobile Safari/537.36 HeyTapBrowser/40.7.39.2");

        Document doc = connection.get();
        List<String> response = getFromDoc(doc);
        if (response.isEmpty()) return gifImg.parse(url);
        List<String> list = new ArrayList<>();
        for (String u0 : response) {
            try {
                if (u0.startsWith("//")) {
                    u0 = "https:" + u0;
                }
                list.add(u0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list.toArray(new String[0]);
    }

    public static List<String> getFromDoc(Document d) throws Exception {
        List<String> list = new ArrayList<>();
        Elements elements = d.getElementsByClass("swiper-wrapper");
        if (elements.size() > 0) {
            for (Element element : elements.get(0).getElementsByTag("img")) {
                String src = element.attr("src");
                list.add(src);
            }
        }
        return list;
    }
}
