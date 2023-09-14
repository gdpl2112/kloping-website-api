package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.VideoAnimeSource;
import io.github.kloping.mywebsite.entitys.baiduShitu.BaiduShitu;
import io.github.kloping.mywebsite.entitys.baiduShitu.response.BaiduShituResponse;
import io.github.kloping.mywebsite.entitys.baiduShitu.response.DataList;
import io.github.kloping.mywebsite.entitys.medias.Result;
import io.github.kloping.mywebsite.entitys.medias.Song;
import io.github.kloping.mywebsite.entitys.medias.Songs;
import io.github.kloping.mywebsite.plugins.PluginsSource;
import io.github.kloping.mywebsite.plugins.detail.BaiduShituDetail;
import io.github.kloping.mywebsite.services.*;
import io.github.kloping.mywebsite.services.impl.ParseGifImgImpl;
import io.github.kloping.mywebsite.services.impl.ParseGifImgImpl0;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

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

    @Qualifier("searchVideoBili")
    @Autowired
    ISearchVideo searchVideoBili;

    @Qualifier("parseDouyImgImpl")
    @Autowired
    IParseImg parseImgDy;

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

    ParseGifImgImpl0 parseImgKs0 = new ParseGifImgImpl0();
    ParseGifImgImpl parseImgKs = new ParseGifImgImpl();

    @RequestMapping("/parseImgs")
    public List<String> parseImg(HttpServletRequest request, String url, @RequestParam(required = false) String type) {
        if (type == null) type = "ks";
        try {
            switch (type) {
                case "ks":
                    return Arrays.asList(parseImgKs0.parse(url.trim()));
                case "dy":
                    return Arrays.asList(parseImgDy.parse(url.trim()));
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/parseVoice")
    public Object parseVoice(String url) throws IOException {
        if (url.contains("kuaishou")) {
            return "https://p4.a.yximgs.com" + parseImgKs.getDataResponse(url).getAtlas().getMusic();
        } else if (url.contains("douyin")) {
            Document doc0 = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 Edg/107.0.1418.35")
                    .get();
            String all = doc0.html();
            String u0 = null;
            int i = all.indexOf("%7B%22src%22%3A%22");
            all = all.substring(i + "%7B%22src%22%3A%22".length());
            u0 = all.substring(0, all.indexOf("%22"));
            u0 = URLDecoder.decode(u0);
            return "http:" + u0;
        }
        return "暂不支持该类型的网站的解析";
    }

    @Autowired
    @Qualifier("videoGetterIqiyiImpl")
    IVideoGetter getter0;

    @Autowired
    @Qualifier("videoGetterTencentImpl")
    IVideoGetter getter1;

    @Autowired
    @Qualifier("videoGetterThirdPartyImpl")
    IVideoGetter getter2;

    @RequestMapping("/video")
    public Object searchVideo(@RequestParam("keyword") String keyword,
                              @RequestParam(required = false, value = "type") String type,
                              @RequestParam(required = false, value = "url") String url
    ) {
        keyword = keyword.trim();
        if (url != null && !url.isEmpty()) {
            switch (type.trim()) {
                case "iqiyi":
                    return getter0.get(keyword, url);
                case "tencent":
                    return getter1.get(keyword, url);
                case "all":
                    List<VideoAnimeSource> sources = new LinkedList<>();
                    sources.addAll(Arrays.asList(getter0.get(keyword, url)));
                    sources.addAll(Arrays.asList(getter1.get(keyword, url)));
                    return sources.toArray(new VideoAnimeSource[0]);
                default:
                    return null;
            }
        } else {
            switch (type.trim()) {
                case "iqiyi":
                    return getter0.search(keyword);
                case "tencent":
                    return getter1.search(keyword);
                case "all":
                    List<VideoAnimeSource> sources = new LinkedList<>();
                    sources.addAll(Arrays.asList(getter0.search(keyword)));
                    sources.addAll(Arrays.asList(getter1.search(keyword)));
                    return sources.toArray(new VideoAnimeSource[0]);
                default:
                    return null;
            }
        }
    }

    private static final Map<String, String> HEADERS = new LinkedHashMap<>();

    static {
        HEADERS.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.78");
    }

    public static final Map<String, Songs> VSS_MAP = new HashMap<>();

    @RequestMapping("vipSong")
    public Songs vipSongs(HttpServletRequest request, @RequestParam("keyword") String keyword
            , @RequestParam(required = false, value = "n") String numStr
    ) throws ScriptException, IOException {
        synchronized (HEADERS) {
            if (VSS_MAP.containsKey(keyword)) {
                return VSS_MAP.get(keyword);
            }
            Songs songs = new Songs();
            Document doc = Jsoup.
                    connect("https://zj.v.api.aa1.cn/api/qqmusic/?songName=" + keyword + "&singerName=&playlistId=&pageNum=1&pageSize=2&type=qq")
                    .headers(HEADERS)
                    .ignoreContentType(true).ignoreHttpErrors(true).get();
            String text = doc.body().text();
            JSONObject jo = JSON.parseObject(text);
            List<Song> list = new ArrayList<>();
            for (Object o : jo.getJSONArray("list")) {
                JSONObject j1 = (JSONObject) o;
                Song song = new Song();
                song.setSongUrl(j1.getString("url"));
                song.setImgUrl(j1.getString("cover"));
                song.setLyric("");
                song.setMedia_name(j1.getString("name"));
                song.setAuthor_name(j1.getString("singer"));
                song.setId("0");
                list.add(song);
            }
            songs.setType("qq");
            songs.setTime(System.currentTimeMillis());
            songs.setState(200);
            songs.setNum(list.size());
            songs.setKeyword(keyword);
            songs.setData(list.toArray(new Song[0]));
            VSS_MAP.put(keyword, songs);
            return songs;
        }
    }

    @RequestMapping("searchPic")
    public Object searchPic(@RequestParam("url") String url) {
        JSONArray array = new JSONArray();
        BaiduShitu baiduShitu = BaiduShituDetail.get(url);
        BaiduShituResponse response = PluginsSource.iBaiduShitu.response(baiduShitu.getData().getSign());
        Iterator<DataList> iterator = Arrays.asList(response.getData().getList()).iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            array.add(o);
        }
        return array;
    }
}
