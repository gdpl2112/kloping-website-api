package io.github.kloping.mywebsite.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.date.DateUtils;
import io.github.kloping.file.FileUtils;
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
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author github.kloping
 */
@RestController
public class ApiTempController {

    private Map<String, String> datamap = new HashMap<>();

    @RequestMapping("/get-url-by-id")
    public void getUrlById(@RequestParam String id, HttpServletResponse response) throws IOException {
        String out = getDataFromId(id);
        JSONObject jd = JSON.parseObject(out);
        jd = jd.getJSONObject("url_info");
        response.sendRedirect(jd.getString("url"));
    }

    private String getDataFromId(String id) throws IOException {
        if (datamap.containsKey(id)) return datamap.get(id);
        String year = String.valueOf(DateUtils.getYear());
        String month = String.valueOf(DateUtils.getMonth());
        String day = String.valueOf(DateUtils.getDay());
        if (month.length() == 1) month = "0" + month;
        if (day.length() == 1) day = "0" + day;
        String url = "https://music.163.com/song?id=" + id;
        final String end = "losslessmusiccn_v1";
        String md5o = year + month + day + url + end;
        String token = DigestUtils.md5DigestAsHex((md5o).getBytes());
        String body = String.format("{\"url\":\"%s\",\"level\":\"lossless\",\"type\":\"song\",\"token\":\"%s\"}", url, token);
        Document doc0 = Jsoup.connect("https://api.toubiec.cn/api/music_v1.php")
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header("Content-Length", String.valueOf(body.length()))
                .header("Content-Type", "application/json")
                .header("Origin", "https://api.toubiec.cn")
                .header("Referer", "https://api.toubiec.cn/wyapi.html")
                .header("Timestamp", String.valueOf(System.currentTimeMillis()))
                .header("Token", token)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67 ")
                .requestBody(body).ignoreContentType(true).ignoreHttpErrors(true).post();
        String out = doc0.wholeText();
        datamap.put(id, out);
        return out;
    }

    private String getUrlByIdFromVip(String id) throws IOException {
        Document doc0 = Jsoup.connect("https://tools.qzxdp.cn/api/wyy_vip/parse")
                .ignoreContentType(true)
                .header("Accept", "application/json, text/javascript, */*; q=0.01")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Host", "tools.qzxdp.cn")
                .header("Origin", "https://tools.qzxdp.cn")
                .header("Referer", "https://tools.qzxdp.cn/wyy_vip")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67")
                .header("X-Requested-With", "XMLHttpRequest")
                .data("url", "https://music.163.com/#/song?id=" + id)
                .data("musicType", "lossless").post();
        JSONObject jo = JSON.parseObject(doc0.body().text());
        return jo.getJSONObject("data").getString("url");
    }

    @RequestMapping("/get-cover-by-id")
    public void getCoverUrlById(@RequestParam String id, HttpServletResponse response) throws IOException {
        String out = getDataFromId(id);
        JSONObject jd = JSON.parseObject(out);
        jd = jd.getJSONObject("song_info");
        response.sendRedirect(jd.getString("cover"));
    }

    private File sortSongs = new File("./files/sort-sons.json");
    RestTemplate template = new RestTemplate();

    @RequestMapping("/get-music")
    public Object getMusic(HttpServletRequest request) {
        if (!sortSongs.exists()) {
            String json = template.getForObject("http://localhost/api/get/163host", String.class);
//                    UrlUtils.getStringFromHttpUrl("https://api.wer.plus/api/wytop?t=4");
            JSONArray dar = JSON.parseArray(json);
            JSONArray array = new JSONArray();
            for (Object data : dar) {
                try {
                    JSONObject o0 = new JSONObject();
                    JSONObject d0 = (JSONObject) data;
                    o0.put("name", d0.getString("name"));
                    o0.put("artist", d0.getString("artist"));
                    o0.put("url", "/get-url-by-id?id=" + d0.getString("id"));
                    o0.put("cover", "/get-cover-by-id?id=" + d0.getString("id"));
                    array.add(o0);
                    if (array.size() >= 100) {
                        FileUtils.putStringInFile(array.toString(), sortSongs);
                        break;
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    continue;
                }
            }
        }
        return FileUtils.getStringFromFile(sortSongs.getAbsolutePath());
//        return FileUtils.getStringFromFile("./files/songs.json");
    }

    @Scheduled(cron = "52 1 0 * * ?")
    public void delTemp() {
        sortSongs.delete();
    }

    @Scheduled(fixedRate = 1800000)
    public void m30() {
        datamap.clear();
    }
    @RequestMapping("/test")
    public String test() {
        return "{\n" +
                "  \"success\": true,\n" +
                "  \"message\": \"Message sent successfully\"\n" +
                "}";
    }
}
