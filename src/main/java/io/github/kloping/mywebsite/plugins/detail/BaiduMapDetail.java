package io.github.kloping.mywebsite.plugins.detail;

import java.util.HashMap;
import java.util.Map;

/**
 * @author github.kloping
 */
public class BaiduMapDetail {
    public static final Map<String, String> HEADERS = new HashMap<>();

    static {
        HEADERS.put("Accept", "*/*");
        HEADERS.put("Connection", "keep-alive");
        HEADERS.put("Referer", "https://maplocation.sjfkai.com/");
        HEADERS.put("Host", "api.map.baidu.com");
        HEADERS.put("Accept-Encoding", "gzip, deflate, br");
        HEADERS.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
    }

    public static String doc(String t1) {
        int i1 = t1.indexOf("({");
        int i2 = t1.lastIndexOf("})");
        String s2 = t1.substring(i1 + 1, i2 + 1);
        return s2;
    }
}
