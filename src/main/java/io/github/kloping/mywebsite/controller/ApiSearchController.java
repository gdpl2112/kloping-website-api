package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.medias.Result;
import io.github.kloping.mywebsite.entitys.medias.Songs;
import io.github.kloping.mywebsite.entitys.medias.VideoSource;
import io.github.kloping.mywebsite.services.IParseImg;
import io.github.kloping.mywebsite.services.ISearchPic;
import io.github.kloping.mywebsite.services.ISearchSong;
import io.github.kloping.mywebsite.services.ISearchVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @author github-kloping
 */
@RestController
@RequestMapping("/api/search")
public class ApiSearchController {

    @Qualifier("searchPic_Baidu")
    @Autowired
    ISearchPic searchPic_Baidu;

    @Qualifier("searchPicMany")
    @Autowired
    ISearchPic searchPic_Many;
    @Qualifier("searchPic_DuiT")
    @Autowired
    ISearchPic searchPic_Dt;

    @Qualifier("searchSongNetEase")
    @Autowired
    ISearchSong searchSongWy;

    @Qualifier("searchSongKugou")
    @Autowired
    ISearchSong searchSongKugou;

    @Qualifier("searchSongQQ")
    @Autowired
    ISearchSong searchSongQQ;

    @Qualifier("searchVideo_Giftshow")
    @Autowired
    ISearchVideo searchVideo_giftshow;

    @Qualifier("searchVideo_Bili")
    @Autowired
    ISearchVideo searchVideo_bili;

    @Autowired
    @Qualifier("parseGifImgImpl")
    IParseImg parseImgKs;

    @Qualifier("parseDouyImgImpl")
    @Autowired
    IParseImg parseImgDy;

    @RequestMapping("/pic")
    public Result search_pic(HttpServletRequest request, String keyword, @RequestParam(required = false) Integer num, @RequestParam(required = false, value = "type") String type) {
        Result result = new Result();
        result.setTime(System.currentTimeMillis());
        result.setKeyword(keyword);
        type = type == null ? "baidu" : type;
        try {
            String[] strings = new String[1];
            num = num == null ? 16 : num > 480 ? 480 : num;
            switch (type) {
                case "baidu":
                    strings = searchPic_Many.searchPics(keyword, num);
                    break;
                case "duit":
                    strings = searchPic_Dt.searchPics(keyword, num);
                    break;
            }
            result.setNum(strings.length);
            result.setData(strings);
            result.setState(1);
        } catch (Exception e) {
            e.printStackTrace();
            result.setState(-1);
            result.setNum(0);
            result.setData(null);
        }
        return result;
    }

    @RequestMapping("/song")
    public Songs searchSong(HttpServletRequest request, @RequestParam("keyword") String keyword
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
            switch (type.toLowerCase()) {
                case "wy":
                    return searchSongWy.searchSong(keyword, num);
                case "kugou":
                    return searchSongKugou.searchSong(keyword, num);
                case "qq":
                    return searchSongQQ.searchSong(keyword, num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Songs(-1, 0, System.currentTimeMillis(), keyword, null, "err");
    }

    @RequestMapping("/video")
    public VideoSource searchVideo(HttpServletRequest request, String keyword, @RequestParam(required = false) String type) {
        if (type == null || type.isEmpty()) type = "ks";
        try {
            switch (type.toLowerCase()) {
                case "ks":
                    return searchVideo_giftshow.search(keyword);
                case "bili":
                case "bilibili":
                    return searchVideo_bili.search(keyword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new VideoSource(-1, keyword, null, System.currentTimeMillis(), type, -1);
    }

    @RequestMapping("/parseImgs")
    public List<String> parseImg(HttpServletRequest request, String url, @RequestParam(required = false) String type) {
        if (type == null) type = "ks";
        try {
            switch (type) {
                case "ks":
                    return Arrays.asList(parseImgKs.parse(url.trim()));
                case "dy":
                    return Arrays.asList(parseImgDy.parse(url.trim()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
