package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.services.IParseImg;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ParseGifImgImpl implements IParseImg {
    @Override
    public String[] parse(String url) throws Exception {
        Connection connection = Jsoup.connect(url)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36 Edg/94.0.992.50");
        Document doc = connection.get();

        Elements elements = doc.getElementsByTag("img");

        Elements org_eles = new Elements();

        for (Element element : elements) {
            String classStr = element.attr("class");
            if (classStr.equals("long-mode-item"))
                org_eles.add(element);
        }
        String[] strings = new String[org_eles.size()];
        int n = 0;
        for (Element element : org_eles) {
            strings[n++] = element.attr("src");
        }
        return strings;
    }
}
