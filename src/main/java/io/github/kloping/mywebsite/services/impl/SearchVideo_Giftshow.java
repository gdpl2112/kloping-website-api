package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.ks.KsSearchEn;
import io.github.kloping.mywebsite.entitys.ks.Variables;
import io.github.kloping.mywebsite.entitys.ks.response.Feeds;
import io.github.kloping.mywebsite.entitys.ks.response.KsSearchResponse;
import io.github.kloping.mywebsite.entitys.medias.MediaSource;
import io.github.kloping.mywebsite.entitys.medias.VideoSource;
import io.github.kloping.mywebsite.services.ISearchVideo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchVideo_Giftshow implements ISearchVideo {

    private static final String baseUrl = "https://www.kuaishou.com/graphql";
    private static boolean re = false;

    public static Map<String, String> getCookieStore(String url) throws Exception {
        Connection connection;
        Document document;
        Map<String, String> store;
        Connection.Response response;
        connection = Jsoup.connect(url)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36 Edg/94.0.992.31")
                .header("Origin", "https://www.kuaishou.com")
                .header("Connection", "keep-alive")
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Site", "same-origin")
                .header("Host", "www.kuaishou.com")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Pragma", "no-cache")
                .header("accept", "*/*")
                .header("Sec-Fetch-Mode", "cors")
                .header("sec-ch-ua", "\"Microsoft Edge\";v=\"95\", \"Chromium\";v=\"95\", \";Not A Brand\";v=\"99\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("Cache-Control", "no-cache")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        document = connection.get();
        response = connection.response();
        store = response.cookies();
        store.forEach((k, v) -> {
            connection.cookie(k, v);
        });
        document = connection.get();
        response = connection.response();

        store = response.cookies();
        return store;
    }

    @Override
    public VideoSource search(String keyword) throws Exception {
        Map<String, String> map = getCookieStore("https://www.kuaishou.com/search/video?searchKey=" + keyword);
//        searchBefore(keyword, map);

        KsSearchEn en = new KsSearchEn();
        en.setQuery("query visionSearchPhoto($keyword: String, $pcursor: String, $searchSessionId: String, $page: String, $webPageArea: String) {\n  visionSearchPhoto(keyword: $keyword, pcursor: $pcursor, searchSessionId: $searchSessionId, page: $page, webPageArea: $webPageArea) {\n    result\n    llsid\n    webPageArea\n    feeds {\n      type\n      author {\n        id\n        name\n        following\n        headerUrl\n        headerUrls {\n          cdn\n          url\n          __typename\n        }\n        __typename\n      }\n      tags {\n        type\n        name\n        __typename\n      }\n      photo {\n        id\n        duration\n        caption\n        likeCount\n        realLikeCount\n        coverUrl\n        photoUrl\n        liked\n        timestamp\n        expTag\n        coverUrls {\n          cdn\n          url\n          __typename\n        }\n        photoUrls {\n          cdn\n          url\n          __typename\n        }\n        animatedCoverUrl\n        stereoType\n        videoRatio\n        __typename\n      }\n      canAddComment\n      currentPcursor\n      llsid\n      status\n      __typename\n    }\n    searchSessionId\n    pcursor\n    aladdinBanner {\n      imgUrl\n      link\n      __typename\n    }\n    __typename\n  }\n}\n");
        en.setOperationName("visionSearchPhoto");
        Variables variables = new Variables().setKeyword(keyword).setPage("search").setPcursor("");
        en.setVariables(variables);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(en));
        String enKey = Jsoup.connect("https://www.kuaishou.com/search/video?searchKey=" + keyword).ignoreContentType(true).get().baseUri();
        Connection connection = Jsoup.connect(baseUrl)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36 Edg/94.0.992.31")
                .header("Origin", "https://www.kuaishou.com")
                .header("Connection", "keep-alive")
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Site", "same-origin")
                .header("Host", "www.kuaishou.com")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Pragma", "no-cache")
                .header("accept", "*/*")
                .header("Sec-Fetch-Mode", "cors")
                .header("sec-ch-ua", "\"Microsoft Edge\";v=\"95\", \"Chromium\";v=\"95\", \";Not A Brand\";v=\"99\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("Cache-Control", "no-cache")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header("Referer", enKey)
//                .cookie("kpn", "KUAISHOU_VISION")
//                .cookie("kpf", "PC_WEB")
//                .cookie("clientid", "3")
                ;
        map.forEach((k, v) -> {
            System.err.println(k + "=" + v);
            connection.cookie(k, v);
        });
        for (String k : jsonObject.keySet()) {
            connection.data(k, jsonObject.get(k).toString());
        }

        Document doc = connection.proxy("112.123.40.232", 9999).post();
        String allJsonStr = doc.body().text();
        System.out.println(allJsonStr);
        KsSearchResponse response = JSON.parseObject(allJsonStr, KsSearchResponse.class);

        List<MediaSource> list = new ArrayList<>();
        for (Feeds feeds : response.getData().getVisionSearchPhoto().getFeeds()) {
            String mediaUrl = feeds.getPhoto().getPhotoUrl();
            String authorName = feeds.getAuthor().getName();
            String fromUrl = feeds.getAuthor().getId();
            Number likeCount = feeds.getPhoto().getRealLikeCount();
            String title = feeds.getPhoto().getCaption();
            String imgUrl = feeds.getPhoto().getCoverUrl();
            MediaSource source = new MediaSource();
            source.setAuthorName(authorName);
            source.setFromUrl("https://www.kuaishou.com/short-video/" + fromUrl);
            source.setLikeCount(likeCount);
            source.setSource("快手");
            source.setUrl(mediaUrl);
            source.setTitle(title);
            source.setImgUrl(imgUrl);
            list.add(source);
        }
        Collections.sort(list, new Comparator<MediaSource>() {
            @Override
            public int compare(MediaSource o1, MediaSource o2) {
                return o2.getLikeCount().intValue() - o1.getLikeCount().intValue();
            }
        });
        VideoSource videoSource = new VideoSource();
        videoSource.setData(list.toArray(new MediaSource[0]));
        videoSource.setKeyword(keyword);
        videoSource.setNum(list.size());
        videoSource.setTime(System.currentTimeMillis());
        videoSource.setSource("ks");
        videoSource.setState(1);
        return videoSource;
    }
}
