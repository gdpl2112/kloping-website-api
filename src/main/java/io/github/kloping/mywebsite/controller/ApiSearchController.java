package io.github.kloping.mywebsite.controller;

import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.VideoAnimeSource;
import io.github.kloping.mywebsite.entitys.medias.Result;
import io.github.kloping.mywebsite.entitys.medias.Song;
import io.github.kloping.mywebsite.entitys.medias.Songs;
import io.github.kloping.mywebsite.entitys.vipSong.VipSong;
import io.github.kloping.mywebsite.services.*;
import io.github.kloping.mywebsite.services.impl.ParseGifImgImpl0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

import static io.github.kloping.mywebsite.plugins.Source.myHkw;

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
        if (type == null || type.isEmpty()) type = "kugou";
        int num = 2;
        try {
            num = Integer.parseInt(numStr.trim());
        } catch (Exception e) {
        }
        try {
            String vk = keyword + "," + type + "," + num;
            synchronized (ApiSearchController.class) {
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

    IParseImg parseImgKs = new ParseGifImgImpl0();

    @RequestMapping("/parseImgs")
    public List<String> parseImg(HttpServletRequest request, String url, @RequestParam(required = false) String type) {
        if (type == null) type = "ks";
        try {
            switch (type) {
                case "ks":
                    return Arrays.asList(parseImgKs.parse(url.trim()));
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
    public VideoAnimeSource[] searchVideo(@RequestParam("keyword") String keyword,
                                          @RequestParam(required = false, value = "type") String type,
                                          @RequestParam(required = false, value = "url") String url
    ) {
        keyword = keyword.trim();
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
            case "tp":
                if (url == null || url.isEmpty()) {
                    return getter2.search(keyword);
                } else {
                    return new VideoAnimeSource[]{getter2.get(keyword, url)};
                }
            default:
                return null;
        }
    }

    @RequestMapping("vipSong")
    public Songs vipSongs(HttpServletRequest request, @RequestParam("keyword") String keyword
            , @RequestParam(required = false, value = "type") String type
            , @RequestParam(required = false, value = "n") String numStr
    ) {
        Songs songs = new Songs();
        int num = 5;
        try {
            num = Integer.parseInt(numStr.trim());
        } catch (Exception e) {
        }
        VipSong[] ss = myHkw.songs(null, null, num, type, 1, keyword, System.currentTimeMillis());
        songs.setKeyword(keyword);
        songs.setState(0);
        songs.setType(type);
        songs.setTime(System.currentTimeMillis());
        songs.setNum(num);
        List<Song> s0 = new LinkedList<>();
        for (VipSong s : ss) {
            String surl = null;
            String img = null;
            try {
                surl = UtilsController.getRedirectUrl("https://myhkw.cn/api/musicUrl?songId=" + s.getUrl_id() + "&type=" + s.getType() + "&id=155782152289");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                img = UtilsController.getRedirectUrl(String.format("https://myhkw.cn/api/musicPic?picId=%s&type=%s&size=%s", s.getPic_id(), s.getType(), "big"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            JSONObject jo = myHkw.lyric(null, s.getType(), s.getId(), s.getLyric_id(), System.currentTimeMillis());

            String lyric = "";
            if (jo.containsKey("txt")){
                lyric = jo.get("txt").toString();
            }else if (jo.containsKey("lyric")){
                lyric = jo.get("lyric").toString();
            }
            s0.add(
                    new Song().setId(s.getId())
                            .setAuthor_name(s.getAllArtists())
                            .setMedia_name(s.getName())
                            .setLyric(lyric)
                            .setSongUrl(surl)
                            .setImgUrl(img)
            );
        }
        songs.setData(s0.toArray(new Song[s0.size()]));
        return songs;
    }
}
