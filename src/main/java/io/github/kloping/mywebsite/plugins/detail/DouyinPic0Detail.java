package io.github.kloping.mywebsite.plugins.detail;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author github-kloping
 * @version 1.0
 */
public class DouyinPic0Detail {
    public static final String AWEME = "aweme";
    public static final Map<String, String> HEADERS = new LinkedHashMap<>();

    static {
        HEADERS.put("Accept", "text/html");
        HEADERS.put("Cookie", "passport_csrf_token=d6f12dea0669c0a05b2231f0726388de; passport_csrf_token_default=d6f12dea0669c0a05b2231f0726388de; msToken=WGFUmzoGiAnpucQk0PpRr-rcVtofjaRFVzo9rfI3QOwq92veGUsyxDQnZ6rehVEIJIVrt7xpbmVbPzc97rLbqYrHgmblK81wTH2OHcMub29voi7nYyGWaw6FirTDuFkw; __ac_nonce=063ca4b2c00cfbb63b160; __ac_signature=_02B4Z6wo00f0199Ya.gAAIDAitrgZ4lSzb.feG9AAJQlRCuzDfuW-PO0GalLYs59AfMb9EePG5V5YV8oy26aTfuX.RTEyLVedHNX2VbL.u3neW2rgGWlHZ57i-TW7YGMa-wN7aOvYTAgu1tC14; __ac_referer=__ac_blank");
    }

    public static String[] getPics(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Document doc = null;
        Request.Builder builder = new Request.Builder().url(url);
        HEADERS.forEach((k, v) -> {
            builder.addHeader(k, v);
        });
        Request getRequest = builder.build();
        try {
            Response response = client.newCall(getRequest).execute();
            String html0 = response.body().string();
            doc = Jsoup.parse(html0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element e0 = doc.getElementsByTag("script").get(0);
        String text = e0.data();
        text = URLDecoder.decode(text);
        JSONObject jo = JSON.parseObject(text);
        AtomicReference<String> k0 = new AtomicReference<>();
        jo.forEach((k, v) -> {
            if (v instanceof JSONObject) {
                JSONObject j0 = (JSONObject) v;
                if (j0.containsKey(AWEME)) {
                    k0.set(k);
                }
            }
        });
        JSONArray images = jo.getJSONObject(k0.get()).getJSONObject(AWEME)
                .getJSONObject("detail").getJSONArray("images");
        String[] sss = new String[images.size()];
        for (int i = 0; i < images.size(); i++) {
            JSONObject j1 = images.getJSONObject(i);
            JSONArray arr = j1.getJSONArray("urlList");
            sss[i] = arr.get(arr.size() - 1).toString();
        }
        return sss;
    }
}
