package io.github.kloping.mywebsite.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.date.DateUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.AbstractMap;
import java.util.Map;

/**
 * @author github.kloping
 */
@RestController
public class ApiTempController {

    private Map.Entry<String, String> cache = null;

    //https://api.toubiec.cn/wyapi.html

    private String getDataFromId(String id) throws Exception {
        if (cache != null) {
            if (cache.getKey().equals(id)) {
                String out = cache.getValue();
                cache = null;
                return out;
            }
        }
        String url = "https://music.163.com/#/song?id=" + id;
        String requ = "https://api.toubiec.cn/api/music_v1.php";
        /*String year = String.valueOf(DateUtils.getYear());
        String month = String.valueOf(DateUtils.getMonth());
        String day = String.valueOf(DateUtils.getDay());
        if (month.length() == 1) month = "0" + month;
        if (day.length() == 1) day = "0" + day;
        final String end = "losslessmusiccn_v1";
        String md5o = year + month + day + url + end;
        String token = DigestUtils.md5DigestAsHex((md5o).getBytes());
        */
        String token = getToken();
        String body = String.format("{\"url\":\"%s\",\"level\":\"standard\",\"type\":\"song\",\"token\":\"%s\"}", url, token);

        Document doc0 = null;
        Connection.Response response = Jsoup.connect(requ).header("Accept", "application/json, text/javascript, */*; q=0.01")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67")
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .header("Host", "api.toubiec.cn")
                .header("Origin", "https://api.toubiec.cn")
                .header("Referer", "https://api.toubiec.cn/wyapi.html")
                .header("Timestamp", String.valueOf(System.currentTimeMillis()))
                .header("Token", token)
                .requestBody(body)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .method(Connection.Method.POST).execute();
        doc0 = response.parse();
        String out = doc0.body().wholeOwnText().trim();
        cache = new AbstractMap.SimpleEntry<>(id, out);
        return out;
    }

    public static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    private String getToken() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.toubiec.cn/api/get-token.php"))
                .POST(HttpRequest.BodyPublishers.ofString("{\"type\":\"tokens\"}"))
                .setHeader("Accept", "application/json, text/plain, */*")
                .setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .setHeader("Cache-Control", "no-cache")
                .setHeader("Content-Type", "application/json")
                .setHeader("Origin", "https://api.toubiec.cn")
                .setHeader("Referer", "https://api.toubiec.cn/wyapi.html")
                .setHeader("Timestamp", String.valueOf(System.currentTimeMillis()))
                .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67")
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject json = JSON.parseObject(response.body());
        return json.getString("token");
    }

    @RequestMapping("/get-url-by-id")
    public void getUrlById(@RequestParam String id, HttpServletResponse response) throws Exception {
        String out = getDataFromId(id);
        JSONObject jd = JSON.parseObject(out);
        jd = jd.getJSONObject("url_info");
        response.sendRedirect(jd.getString("url"));
    }

    @RequestMapping("/get-cover-by-id")
    public void getCoverUrlById(@RequestParam String id, HttpServletResponse response) throws Exception {
        String out = getDataFromId(id);
        JSONObject jd = JSON.parseObject(out);
        jd = jd.getJSONObject("song_info");
        response.sendRedirect(jd.getString("cover"));
    }

    private final RestTemplate template = new RestTemplate();
    private final JSONArray array = new JSONArray();

    @RequestMapping("/get-music")
    public Object getMusic(HttpServletRequest request) {
        if (array.isEmpty()) {
            String json = template.getForObject("http://localhost/api/get/163host", String.class);
            JSONArray dar = JSON.parseArray(json);
            for (Object data : dar) {
                JSONObject o0 = new JSONObject();
                JSONObject d0 = (JSONObject) data;
                o0.put("name", d0.getString("name"));
                o0.put("artist", d0.getString("artist"));
                o0.put("url", "/get-url-by-id?id=" + d0.getString("id"));
                o0.put("cover", "/get-cover-by-id?id=" + d0.getString("id"));
                array.add(o0);
                if (array.size() >= 100) {
                    break;
                }
            }
        }
        return array;
    }

    @Scheduled(cron = "52 1 0 * * ?")
    public void delTemp() {
        array.clear();
    }

    @RequestMapping("/test")
    public String test() {
        return "{\n" +
                "  \"success\": true,\n" +
                "  \"message\": \"Message sent successfully\"\n" +
                "}";
    }
}
