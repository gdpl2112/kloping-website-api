package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.medias.Result;
import io.github.kloping.mywebsite.entitys.medias.Song;
import io.github.kloping.mywebsite.entitys.medias.Songs;
import io.github.kloping.mywebsite.services.ISearchPic;
import io.github.kloping.mywebsite.services.ISearchSong;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author github-kloping
 */
@RestController
@RequestMapping("/api/search")
public class ApiSearchController {

    @Qualifier("searchPicBaidu")
    @Autowired
    ISearchPic searchPicBaidu;

    @Qualifier("searchPicMany")
    @Autowired
    ISearchPic searchPicMany;

    @Qualifier("searchPicDuiT")
    @Autowired
    ISearchPic searchPicDt;

    @Qualifier("searchSongNetEase")
    @Autowired
    ISearchSong searchSongWy;

    @Qualifier("searchSongKugou")
    @Autowired
    ISearchSong searchSongKugou;

    @Qualifier("searchSongQQ")
    @Autowired
    ISearchSong searchSongQq;

    @RequestMapping("/pic")
    public Result searchPic(HttpServletRequest request, @RequestParam("keyword") String keyword, @RequestParam(required = false) Integer num, @RequestParam(required = false, value = "type") String type) {
        Result result = new Result();
        result.setTime(System.currentTimeMillis());
        result.setKeyword(keyword);
        type = type == null ? "baidu" : type;
        try {
            String[] strings = new String[1];
            num = num == null ? 16 : num > 480 ? 480 : num;
            switch (type) {
                case "baidu":
                    strings = searchPicMany.searchPics(keyword, num);
                    break;
                case "duit":
                    strings = searchPicDt.searchPics(keyword, num);
                    break;
                default:
                    result.setState(-1);
                    result.setNum(0);
                    result.setData(null);
            }
            result.setNum(strings.length);
            result.setData(strings);
            result.setState(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static final Map<String, Songs> SONGS_HASH_MAP = new HashMap<>();

    @RequestMapping("/song")
    public Songs searchSong(HttpServletRequest request, @RequestParam("keyword") String keyword
            , @RequestParam(required = false, value = "type") String type
            , @RequestParam(required = false, value = "n") String numStr
    ) {
        synchronized (SONGS_HASH_MAP) {
            if (type == null || type.isEmpty()) type = "kugou";
            int num = 2;
            try {
                num = Integer.parseInt(numStr.trim());
            } catch (Exception e) {
            }
            try {
                String vk = keyword + "," + type + "," + num;
                synchronized (SONGS_HASH_MAP) {
                    if (SONGS_HASH_MAP.containsKey(vk))
                        return SONGS_HASH_MAP.get(vk);
                    Songs songs = null;
                    switch (type.toLowerCase()) {
                        case "wy":
                            songs = searchSongWy.searchSong(keyword, num);
                            break;
                        case "kugou":
                            songs = searchSongKugou.searchSong(keyword, num);
                            break;
                        case "qq":
                            songs = searchSongQq.searchSong(keyword, num);
                            break;
                        default:
                            return new Songs(-1, 0, System.currentTimeMillis(), keyword, null, "err");
                    }
                    if (songs != null && songs.getState() != -1 && songs.getData().length > 0) {
                        SONGS_HASH_MAP.put(vk, songs);
                        return songs;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Songs(-1, 0, System.currentTimeMillis(), keyword, null, "err");
        }
    }

    private static final Map<String, String> HEADERS = new LinkedHashMap<>();

    static {
        HEADERS.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.78");
    }

    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("vipSong")
    public Songs vipSongs(HttpServletRequest request, @RequestParam("keyword") String keyword
            , @RequestParam(required = false, value = "n") String numStr
    ) throws ScriptException, IOException {
        synchronized (HEADERS) {
            keyword = keyword.trim();
            String out = restTemplate.getForObject(String.format("http://ovoa.cc/api/QQmusic.php?msg=%s&n=1&type=JSON", keyword), String.class);
            JSONObject jo = JSON.parseObject(out);
            JSONObject data = jo.getJSONObject("data");
            Song song = new Song();
            song.setId("");
            song.setLyric("");
            song.setSongUrl(data.getString("src"))
                    .setImgUrl(data.getString("cover"))
                    .setMedia_name(data.getString("songname"))
                    .setAuthor_name(data.getString("name"));
            Songs songs = new Songs(1, 1, System.currentTimeMillis(), keyword, new Song[]{song}, "qq");
            return songs;
        }
    }

    @RequestMapping("searchPic")
    public Object searchPic(@RequestParam("url") String url) throws Exception {
        JSONArray array = t0(url);
        return array;
    }

    public static final ScriptEngineManager SCRIPT_ENGINE_MANAGER = new ScriptEngineManager();

    public JSONArray t0(String source) throws Exception {
        Connection connection = new HttpConnection()
                .url("https://graph.baidu.com/upload?uptime=" + System.currentTimeMillis())
                .ignoreContentType(true).ignoreHttpErrors(true)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header("Origin", "https://graph.baidu.com")
                .header("X-Requested-With", "XMLHttpRequest")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67")
                .data("image", source)
                .data("image_source", "PC_UPLOAD_URL")
                .data("tn", "pc")
                .data("from", "pc");
        Connection.Response response = connection.method(Connection.Method.POST).execute();
        JSONObject jo = JSON.parseObject(response.body());
        jo = jo.getJSONObject("data");
        String url0 = jo.getString("url");
        System.out.println("url0: " + url0);
        Document doc0 = Jsoup.connect(url0).get();
        Element element = doc0.head().getElementsByTag("script").get(1);
        String js = element.html();

        ScriptEngine engine = SCRIPT_ENGINE_MANAGER.getEngineByName("javascript");
        engine.eval("var window={\"M\":{}};");
        engine.eval("var M={}");

        engine.eval(js);
        engine.eval("var out = JSON.stringify(window.cardData)");
        String jsonData = engine.get("out").toString();
        JSONArray array = JSON.parseArray(jsonData);

        JSONObject simipic = null;
        for (Object o : array) {
            JSONObject e0 = (JSONObject) o;
            String cardName = e0.getString("cardName");
            if (cardName.equals("simipic")) {
                simipic = e0;
                break;
            }
        }
        String url = simipic.getJSONObject("tplData").getString("firstUrl");

        connection = new HttpConnection()
                .url(url)
                .ignoreContentType(true).ignoreHttpErrors(true)
                .header("Accept", "*/*")
                .header("Connection", "Keep-Alive")
                .header("Referer", url0)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67")
                .method(Connection.Method.GET);
        JSONArray result = new JSONArray();
        try {
            JSONObject same = array.getJSONObject(1);
            for (Object o : array) {
                JSONObject e0 = (JSONObject) o;
                String cardName = e0.getString("cardName");
                if (cardName.equals("same")) {
                    same = e0;
                    break;
                }
            }
            result.addAll(same.getJSONObject("tplData").getJSONArray("list"));
        } catch (Exception e) {
            System.err.println(e.getMessage() + ";; search pic by pic of same data lose for " + source);
        }
        JSONObject jo0 = JSON.parseObject(connection.execute().body());
        result.addAll(jo0.getJSONObject("data").getJSONArray("list"));
        return result;
    }
}
