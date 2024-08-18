package io.github.kloping.mywebsite.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

/**
 * @author github.kloping
 */
@RestController
public class ApiTempController {

    @RequestMapping("/get-url-by-id")
    public void getUrlById(@RequestParam String id, HttpServletResponse response) throws IOException {
        String out = getDataFromId(id);
        JSONObject jd = JSON.parseObject(out);
        jd = jd.getJSONObject("data");
        response.sendRedirect(jd.getString("url"));
    }

    private Map.Entry<String, String> cache = null;

    private String getDataFromId(String id) throws IOException {
        if (cache != null) {
            if (cache.getKey().equals(id)) {
                String out = cache.getValue();
                cache = null;
                return out;
            }
        }
        String url = "https://music.163.com/song?id=" + id;
        String body = String.format("url=%s&musicType=lossless", url);
        String requ = "https://tools.qzxdp.cn/api/wyy_vip/parse";
        /*
        String year = String.valueOf(DateUtils.getYear());
        String month = String.valueOf(DateUtils.getMonth());
        String day = String.valueOf(DateUtils.getDay());
        if (month.length() == 1) month = "0" + month;
        if (day.length() == 1) day = "0" + day;
        final String end = "losslessmusiccn_v1";
        String md5o = year + month + day + url + end;
        String token = DigestUtils.md5DigestAsHex((md5o).getBytes());
        String body = String.format("{\"url\":\"%s\",\"level\":\"lossless\",\"type\":\"song\",\"token\":\"%s\"}", url, token);
        */
        Document doc0 = null;
        Connection.Response response = Jsoup.connect(requ).header("Accept", "application/json, text/javascript, */*; q=0.01")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6").header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").header("Origin", "https://tools.qzxdp.cn").header("Referer", "https://tools.qzxdp.cn/wyy_vip").header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67").header("X-Requested-With", "XMLHttpRequest").header("Connection", "Keep-Alive").header("Cookie", "PHPSESSID=0070d900e1ada65cd4918e7e41029d1c").requestBody(body).ignoreContentType(true).ignoreHttpErrors(true).method(Connection.Method.POST).execute();
        doc0 = response.parse();
        String out = doc0.body().wholeOwnText().trim();
        cache = new AbstractMap.SimpleEntry<>(id, out);
        return out;
    }

    @RequestMapping("/get-cover-by-id")
    public void getCoverUrlById(@RequestParam String id, HttpServletResponse response) throws IOException {
        String out = getDataFromId(id);
        JSONObject jd = JSON.parseObject(out);
        jd = jd.getJSONObject("data");
        response.sendRedirect(jd.getString("pic"));
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
