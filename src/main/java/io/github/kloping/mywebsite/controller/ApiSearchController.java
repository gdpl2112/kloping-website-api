package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.VideoAnimeSource;
import io.github.kloping.mywebsite.entitys.medias.Result;
import io.github.kloping.mywebsite.entitys.medias.Song;
import io.github.kloping.mywebsite.entitys.medias.Songs;
import io.github.kloping.mywebsite.plugins.Source;
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
        HEADERS.put("accept", "application/json, text/plain, */*");
        HEADERS.put("accept-encoding", "gzip, deflate, br");
        HEADERS.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        HEADERS.put("content-type", "application/json");
        HEADERS.put("origin", "https://music.hamm.cn");
        HEADERS.put("referer", "https://music.hamm.cn/");
        HEADERS.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.78");
    }

    @RequestMapping("vipSong")
    public Songs vipSongs(HttpServletRequest request, @RequestParam("keyword") String keyword
            , @RequestParam(required = false, value = "n") String numStr
    ) throws ScriptException, IOException {
        synchronized (HEADERS) {
            int num = 2;
            try {
                num = Integer.parseInt(numStr.trim());
            } catch (Exception e) {
            }
            Songs songs = new Songs();
            songs.setKeyword(keyword);
            songs.setState(0);
            songs.setType("normal");
            songs.setTime(System.currentTimeMillis());
            songs.setNum(num);
            List<Song> s0 = new LinkedList<>();
            JSONObject body = new JSONObject();
            body.put("page", "1");
            body.put("keyword", keyword);

            JSONObject data = Source.hamm.search(HEADERS, body.toJSONString());

            if (data.getInteger("code") == 200) {
                for (Object e0 : data.getJSONObject("data").getJSONArray("list")) {
                    JSONObject d0 = (JSONObject) e0;
                    Song song = new Song();
                    song.setId(d0.get("mid").toString());
                    song.setAuthor_name(d0.getString("artist"));
                    song.setMedia_name(d0.getString("album"));
                    song.setImgUrl(d0.getString("pic"));
                    song.setLyric("vip歌曲暂不提供歌词");
                    song.setSongUrl(UtilsController.getRedirectUrl(
                            "https://api.hamm.cn/song/play?mid=" + song.getId(),"https://music.hamm.cn/"));
                    s0.add(song);
                    if (s0.size() >= num)
                        break;
                }
            } else {
                System.err.println("获取vip失败=>" + keyword);
            }
            songs.setData(s0.toArray(new Song[s0.size()]));
            return songs;
        }
    }

}
