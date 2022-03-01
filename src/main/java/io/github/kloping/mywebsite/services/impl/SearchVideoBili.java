package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.medias.MediaSource;
import io.github.kloping.mywebsite.entitys.medias.VideoSource;
import io.github.kloping.mywebsite.services.ISearchVideo;
import io.github.kloping.mywebsite.utils.MyUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchVideoBili implements ISearchVideo {

    private static final String baseUrlB = "https://search.bilibili.com/all?keyword=%s&from_source=webtop_search&spm_id_from=333.851";

    @Override
    public VideoSource search(String keyword) throws Exception {
        String urlStr = String.format(baseUrlB, keyword);
        Connection connection = Jsoup.connect(urlStr)
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36 Edg/94.0.992.31")
                .cookie("innersign", "1")
                .cookie("blackside_state", "1")
                .cookie("_uuid", "FA9F7029-056F-BAE2-A5E2-6D0AAB002F0B54564infoc")
                .cookie("bsource", "search_bing")
                .cookie("sid", "6ik8li56")
                .cookie("buvid_fp_plain", "CB8D36BD-BA6D-1F9E-134E-6BD30E3B802E21762infoc")
                .cookie("CURRENT_FNVAL", "976")
                .cookie("buvid_fp", "53EF8AF8-D170-48CB-BF79-16EF25630839148811infoc")
                .cookie("arrange", "matrix")
                .cookie("buvid3", "53EF8AF8-D170-48CB-BF79-16EF25630839148811infoc")
                .cookie("fingerprint", "2199e2fab66f4d0b93cbc53b479fdd37")
                .cookie("rpdid", "|(k|kmuYJlm~0J'uYk~)u|kk~");
        Document document = connection.get();
        Elements elements = document.getElementsByTag("script");
        Element element = elements.get(7);
        String jsonStr = element.toString();
        jsonStr = jsonStr.substring(jsonStr.indexOf("{"), jsonStr.lastIndexOf("};") + 1);
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        JSONArray arrFa = jsonObject
                .getJSONObject("flow")
                .getJSONObject("getMixinFlowList-jump-from_source-webtop_search-from_spmid-333.851-keyword-" + keyword + "-platform-pc")
                .getJSONArray("result").getJSONObject(10).getJSONArray("data");
        final int size = arrFa.size();
        CountDownLatch cdl = new CountDownLatch(size);
        List<MediaSource> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int finalI = i;
            MyUtils.threads.execute(() -> {
                JSONObject jo = arrFa.getJSONObject(finalI);
                String authorName = jo.getString("author");
                String title = jo.getString("description");
                String[] ss = new String[0];
                try {
                    ss = getIUrlFromBvId(jo.getString("bvid"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String mediaUrl = ss[0];
                String imgUrl = ss[1];
                String likeCount = jo.get("favorites").toString();
                MediaSource source = new MediaSource();
                source.setFromUrl("http://www.bilibili.com/" + jo.getString("bvid"));
                source.setAuthorName(authorName);
                source.setLikeCount(Long.valueOf(likeCount));
                source.setUrl(mediaUrl);
                source.setTitle(title);
                source.setImgUrl(imgUrl);
                source.setSource("哔哩哔哩");
                list.add(source);
                cdl.countDown();
            });
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Collections.sort(list, new Comparator<MediaSource>() {
            @Override
            public int compare(MediaSource o1, MediaSource o2) {
                return o2.getLikeCount().intValue() - o1.getLikeCount().intValue();
            }
        });
        //===============
        VideoSource videoSource = new VideoSource();
        videoSource.setData(list.toArray(new MediaSource[0]));
        videoSource.setKeyword(keyword);
        videoSource.setNum(list.size());
        videoSource.setTime(System.currentTimeMillis());
        videoSource.setSource("bili");
        videoSource.setState(1);
        return videoSource;
    }

    private static String[] getIUrlFromBvId(String id) throws Exception {
        Connection connection = Jsoup.connect("https://m.bilibili.com/video/" + id)
                .userAgent("Mozilla/5.0 (Linux; Android 11; PEGT10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.96 Mobile Safari/537.36")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        Document document = connection.get();
        Elements element = document
                .getElementsByClass("m-video-player")
                .get(0)
                .getElementsByTag("script");
        String jsonStr = element.toString();
        Matcher matcher = p.matcher(jsonStr);
        matcher.find();
        String end1 = matcher.group();
        end1 = end1.substring(1, end1.length() - 3);
        matcher = p2.matcher(jsonStr);
        matcher.find();
        String end2 = matcher.group();
        end2 = "https:" + end2.substring(14, end2.length() - 3);
        return new String[]{end1, end2};
    }

    private static final Pattern p = Pattern.compile("'https://.+[^']");
    private static final Pattern p2 = Pattern.compile("readyPoster: '//.+[^']");

}
