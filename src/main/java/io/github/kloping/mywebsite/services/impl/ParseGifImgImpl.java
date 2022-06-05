package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.services.IParseImg;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 */
@Service
public class ParseGifImgImpl implements IParseImg {
    @Override
    public String[] parse(String url) throws Exception {
        Connection connection = Jsoup.connect(url)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Linux; U; Android 12; zh-CN; PEGT10 Build/RKQ1.211103.002) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/78.0.3904.108 Quark/5.7.0.213 Mobile Safari/537.36");
        Document doc = connection.get();

        Element e0 = doc.getElementsByClass("swiper-wrapper").get(0);

        Elements elements = e0.getElementsByTag("img");

        Elements org_eles = new Elements();

        for (Element element : elements) {
            org_eles.add(element);
        }
        String[] strings = new String[org_eles.size()];
        int n = 0;
        for (Element element : org_eles) {
            String i0 = element.attr("src");
            if (!i0.startsWith("http")) {
                i0 = "https:" + i0;
            }
            strings[n++] = i0;
        }
        return strings;
    }
}
