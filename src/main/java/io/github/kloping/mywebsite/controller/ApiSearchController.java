package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.VideoAnimeSource;
import io.github.kloping.mywebsite.entitys.medias.Result;
import io.github.kloping.mywebsite.entitys.medias.Songs;
import io.github.kloping.mywebsite.services.*;
import io.github.kloping.mywebsite.services.impl.ParseGifImgImpl0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    private Map<String, Songs> hist_songs = new HashMap<>();

    @RequestMapping("/song")
    public synchronized Songs searchSong(HttpServletRequest request, @RequestParam("keyword") String keyword
            , @RequestParam(required = false, value = "type") String type
            , @RequestParam(required = false, value = "n") String numStr
    ) {
        if (type == null || type.isEmpty()) type = "kugou";
        int num = 7;
        try {
            num = Integer.parseInt(numStr.trim());
        } catch (Exception e) {
        }
        try {
            String vk = keyword + "," + type + "," + numStr;
            if (hist_songs.containsKey(vk)) return hist_songs.get(vk);
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
            if (songs != null)
                hist_songs.put(vk, songs);

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
}
