package io.github.kloping.mywebsite.plugins.detail;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author github.kloping
 */
public class BaiduImageDetail {
    public static String doc(Document document) {
        String jsonStr = document.body().text();
        JSONObject jsonObj = JSON.parseObject(jsonStr);
        JSONArray jsonArr = jsonObj.getJSONArray("data");
        List<String> list = new LinkedList<>();
        for (Object o : jsonArr) {
            if (o instanceof JSONObject) {
                JSONObject jo = (JSONObject) o;
                String u0 = jo.getString("middleURL");
                list.add(u0);
            }
        }
        return JSON.toJSONString(list.toArray(new String[0]));
    }

    public static final Map<String, String> HEADERS = new HashMap<>();

    static {
        HEADERS.put("Accept", "text/*,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
    }
}
