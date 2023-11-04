package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSONArray;
import io.github.kloping.mywebsite.entitys.baiduShitu.BaiduShitu;
import io.github.kloping.mywebsite.entitys.baiduShitu.response.BaiduShituResponse;
import io.github.kloping.mywebsite.entitys.baiduShitu.response.DataList;
import io.github.kloping.mywebsite.entitys.medias.Result;
import io.github.kloping.mywebsite.entitys.medias.Song;
import io.github.kloping.mywebsite.entitys.medias.Songs;
import io.github.kloping.mywebsite.plugins.PluginsSource;
import io.github.kloping.mywebsite.plugins.detail.BaiduShituDetail;
import io.github.kloping.mywebsite.services.ISearchPic;
import io.github.kloping.mywebsite.services.ISearchSong;
import io.github.kloping.url.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @RequestMapping("vipSong")
    public Songs vipSongs(HttpServletRequest request, @RequestParam("keyword") String keyword
            , @RequestParam(required = false, value = "n") String numStr
    ) throws ScriptException, IOException {
        synchronized (HEADERS) {
            keyword = keyword.trim();
            String out = UrlUtils.getStringFromHttpUrl("https://xiaoapi.cn/API/yy.php?type=qq&msg=" + keyword + "&n=1");
            String[] outs = out.split("\n");
            Song song = new Song();
            song.setId("");
            song.setLyric("");
            song.setSongUrl(outs[3].substring(5))
                    .setImgUrl(outs[0].substring(3))
                    .setMedia_name(outs[2].substring(3))
                    .setAuthor_name(outs[1].substring(3));
            Songs songs = new Songs(1, 1, System.currentTimeMillis(), keyword, new Song[]{song}, "qq");
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
