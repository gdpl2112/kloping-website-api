package io.github.kloping.mywebsite.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author github.kloping
 */
@RestController
@RequestMapping("/api/cre")
public class ApiCreeperController {

    public static final String REGX = "(https?|http|ftp|file):\\/\\/[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
    public static final Pattern PATTERN = Pattern.compile(REGX);

    public static final String KS_LINK = "v.kuaishou.com";

    @RequestMapping("/jxvv")
    public Object jxvv(@RequestParam String url) throws IOException {
        if (!url.matches(REGX)) {
            Matcher matcher = PATTERN.matcher(url);
            if (matcher.find()) url = matcher.group();
        }
        if (url.contains(KS_LINK)) {
            Connection connection = new HttpConnection().url(url);
            connection.header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67");
            Document doc0 = connection.get();
            Map<String, Object> argsMap = new HashMap<>();
            String outData;
//          outData = getDataFromWeb0(doc0, argsMap);
            outData = getDataFromWeb1(doc0);
            if (outData != null) return outData;
        }
        return "{\"result\": -1}";
    }

    private String getDataFromWeb1(Document doc0) {
        Elements elements = doc0.body().getElementsByTag("script");
        for (Element element : elements) {
            try {
                String data = element.data();
                int start = data.indexOf("{");
                int end = data.lastIndexOf("}") + 1;
                if (start <= 0 && end <= 0) continue;
                data = data.substring(start, end);
                JSONObject jo = JSON.parseObject(data);
                for (Object value : jo.values()) {
                    JSONObject v0 = (JSONObject) value;
                    if (v0.containsKey("fid")) {
                        return v0.toString();
                    }
                    for (String s : v0.keySet()) {
                        if (s.startsWith("VisionVideoDetailPhoto")) return v0.toString();
                    }
                }
            } catch (Exception e) {
                System.err.print("continue...");
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Nullable
    private static String getDataFromWeb0(Document doc0, Map<String, Object> argsMap) throws IOException {

        String url = doc0.location();
        int index = url.indexOf("?");
        if (index > 0) {
            url = url.substring(index + 1);
            for (String arg : url.split("&")) {
                String[] kvs = arg.split("=");
                argsMap.put(kvs[0], kvs[1]);
            }
            JSONObject reference = JSON.parseObject("{\"fid\": \"1594993299\",\"shareToken\": \"X-3GwNT63firZ17y\",\"shareObjectId\": \"5188991314621742263\",\"shareMethod\": \"TOKEN\",\"shareId\": \"17695659181273\",\"shareResourceType\": \"PHOTO_OTHER\",\"shareChannel\": \"share_copylink\",\"kpn\": \"NEBULA\",\"subBiz\": \"BROWSE_SLIDE_PHOTO\",\"env\": \"SHARE_VIEWER_ENV_TX_TRICK\",\"h5Domain\": \"kphm5nf3.m.chenzhongtech.com\",\"photoId\": \"3xa7scwmmezphd2\",\"isLongVideo\": false}");
            JSONObject data = new JSONObject();
            for (String key : reference.keySet()) {
                Object value = argsMap.getOrDefault(key, reference.get(key));
                data.put(key, value);
            }
            Map<String, String> cookies = doc0.connection().response().cookies();
            Connection connection = new HttpConnection();

            doc0 = connection.url("https://kphm5nf3.m.chenzhongtech.com/rest/wd/photo/info?kpn=NEBULA&captchaToken=&")
                    .userAgent("AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67")
                    .header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Accept-Language", "zh-CN,zh;q=0.9")
                    .header("Connection", "keep-alive")
                    .header("Content-Type", "application/json")
                    .header("Host", "kphm5nf3.m.chenzhongtech.com")
                    .header("Origin", "https://kphm5nf3.m.chenzhongtech.com")
                    .header("Referer", url)
                    .cookies(cookies).ignoreContentType(true)
                    .requestBody(data.toString())
                    .post();

            return doc0.body().wholeOwnText();
        }
        return null;
    }
}
