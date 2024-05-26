package io.github.kloping.mywebsite.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.file.FileUtils;
import io.github.kloping.url.UrlUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author github.kloping
 */
@RestController
public class ApiTempController {


    @RequestMapping("/get-url-by-id")
    public void getUrlById(@RequestParam String id, HttpServletResponse response) throws IOException {
        String s1 = UrlUtils.getStringFromHttpUrl("https://api.linhun.vip/api/NetEaseCloud?apiKey=c01dd92d2a1245643840d43833bde8df&id=" + id);
        JSONObject d1 = JSON.parseObject(s1);
        Object o = d1.get("MusicLink");
        if (o instanceof JSONArray) {
            response.sendRedirect(getUrlByIdFromVip(id));
        } else response.sendRedirect(d1.getString("MusicLink"));
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
        String s1 = UrlUtils.getStringFromHttpUrl("https://api.linhun.vip/api/NetEaseCloud?apiKey=c01dd92d2a1245643840d43833bde8df&id=" + id);
        JSONObject d1 = JSON.parseObject(s1);
        response.sendRedirect(d1.getString("Picture"));
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

    @RequestMapping("/test")
    public String test() {
        return "{\n" +
                "  \"success\": true,\n" +
                "  \"message\": \"Message sent successfully\"\n" +
                "}";
    }
}
