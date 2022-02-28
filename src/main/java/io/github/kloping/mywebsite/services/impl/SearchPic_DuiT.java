package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.services.ISearchPic;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SearchPic_DuiT implements ISearchPic {
    private static String baseUrl = "https://www.duitang.com/search/?kw=%s&type=feed";
    private static String baseUrl2 = "https://www.duitang.com/napi/blogv2/list/by_search/?kw=%s&after_id=%s&type=feed&include_fields=top_comments,is_root,source_link,item,buyable,root_id,status,like_count,like_id,sender,album,reply_count,favorite_blog_id&_type=&_=%s";

    @Override
    public String[] searchPics(String keyword, Integer num) throws Exception {
        String urlStr;
        Document document;
        Connection connection;
        Elements elements;
        urlStr = String.format(baseUrl, keyword);
        connection = Jsoup.connect(urlStr)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36 Edg/94.0.992.31")
                .cookie("js", "1")
                .cookie("sessionid", "31962e6b-31ab-4451-ba1e-e4c00f9e64d6");
        document = connection.get();
        elements = document.getElementsByTag("img");
        List<String> list = new CopyOnWriteArrayList<>();
        Iterator<Element> iterator = elements.iterator();
        while (list.size() < num) {
            if (iterator.hasNext()) {
                Element e = iterator.next();
                if (e.attr("alt").equals(keyword))
                    list.add(e.attr("src"));
            } else {
                urlStr = String.format(baseUrl2, keyword, list.size(), System.currentTimeMillis());
                connection = Jsoup.connect(urlStr)
                        .ignoreContentType(true)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36 Edg/94.0.992.31")
                        .cookie("js", "1")
                        .cookie("sessionid", "31962e6b-31ab-4451-ba1e-e4c00f9e64d6")
                        .header("Accept","text/plain, */*; q=0.01")
                ;
                document = connection.get();
                JSONObject jo = JSON.parseObject(document.body().text());
                Elements es = new Elements();
                JSONArray arr = jo.getJSONObject("data").getJSONArray("object_list");
                for(Object o:arr){
                    JSONObject jso = (JSONObject) o;
                    es.add(new Element("img").attr("alt",keyword).attr("src",jso.getJSONObject("photo").getString("path")));
                }
                iterator=es.iterator();
            }
        }

        return list.toArray(new String[0]);
    }
}
