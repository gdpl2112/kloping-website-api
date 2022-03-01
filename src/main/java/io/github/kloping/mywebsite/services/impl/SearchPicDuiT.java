package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.entitys.webApi.duiTang.DuiTangResponse;
import io.github.kloping.mywebsite.services.ISearchPic;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import static io.github.kloping.mywebsite.plugins.Source.duiTangImage;
import static io.github.kloping.mywebsite.plugins.Source.getEntry;

/**
 * @author github-kloping
 */
@Service
public class SearchPicDuiT implements ISearchPic {
    public static final Entry<String, String> ENTRY0 = getEntry("js", "1");
    public static final Entry<String, String> ENTRY1 = getEntry("sessionid", "31962e6b-31ab-4451-ba1e-e4c00f9e64d6");

    @Override
    public String[] searchPics(String keyword, Integer num) throws Exception {
        List<String> list = new LinkedList<>();
        int sn = 0;
        while (list.size() < num) {
            DuiTangResponse response = duiTangImage.method(keyword, sn);
            for (io.github.kloping.mywebsite.entitys.webApi.duiTang.Object_list objectList : response.getData().getObject_list()) {
                String i0 = objectList.getPhoto().getPath();
                list.add(i0);
                if (list.size() >= num) break;
            }
        }
        return list.toArray(new String[0]);
//        String urlStr;
//        Document document;
//        Connection connection;
//        Elements elements;
//        urlStr = String.format(baseUrl, keyword);
//        connection = Jsoup.connect(urlStr)
//                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36 Edg/94.0.992.31")
//                .cookie("js", "1")
//                .cookie("sessionid", "31962e6b-31ab-4451-ba1e-e4c00f9e64d6");
//        document = connection.get();
//        elements = document.getElementsByTag("img");
//        List<String> list = new CopyOnWriteArrayList<>();
//        Iterator<Element> iterator = elements.iterator();
//        while (list.size() < num) {
//            if (iterator.hasNext()) {
//                Element e = iterator.next();
//                if (e.attr("alt").equals(keyword)) {
//                    list.add(e.attr("src"));
//                }
//            } else {
//                urlStr = String.format(baseUrl2, keyword, list.size(), System.currentTimeMillis());
//                connection = Jsoup.connect(urlStr)
//                        .ignoreContentType(true)
//                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36 Edg/94.0.992.31")
//                        .cookie("js", "1")
//                        .cookie("sessionid", "31962e6b-31ab-4451-ba1e-e4c00f9e64d6")
//                        .header("Accept","text/plain, */*; q=0.01")
//                ;
//                document = connection.get();
//                JSONObject jo = JSON.parseObject(document.body().text());
//                Elements es = new Elements();
//                JSONArray arr = jo.getJSONObject("data").getJSONArray("object_list");
//                for(Object o:arr){
//                    JSONObject jso = (JSONObject) o;
//                    es.add(new Element("img").attr("alt",keyword).attr("src",jso.getJSONObject("photo").getString("path")));
//                }
//                iterator=es.iterator();
//            }
//        }
//
//        return list.toArray(new String[0]);
    }
}
