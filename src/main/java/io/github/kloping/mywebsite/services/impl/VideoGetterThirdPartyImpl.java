package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.entitys.VideoAnimeDetail;
import io.github.kloping.mywebsite.entitys.VideoAnimeSource;
import io.github.kloping.mywebsite.plugins.Source;
import io.github.kloping.mywebsite.services.IVideoGetter;
import io.github.kloping.number.NumberUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author github.kloping
 */
@Service
public class VideoGetterThirdPartyImpl implements IVideoGetter {

    public static final Map<String, VideoAnimeSource[]> HIST = new HashMap<>();

    @Override
    public VideoAnimeSource[] search(String keyword) {
        if (HIST.containsKey(keyword)) return HIST.get(keyword);
        List<VideoAnimeSource> sources = new ArrayList<>();
        Document doc0 = Source.vopipi.doc(keyword);
        Element p0 = doc0.getElementsByClass("stui-vodlist clearfix").get(0);
        for (Element child : p0.children()) {
            VideoAnimeSource source = new VideoAnimeSource();
            Element st = child.getElementsByClass("pic-text text-right").get(0);
            Element desc = child.getElementsByClass("text text-overflow text-muted hidden-xs").get(0);
            Element na = child.getElementsByClass("stui-vodlist__thumb lazyload").get(0);
            source.setSt(Integer.valueOf(NumberUtils.findNumberFromString(st.text())));
            source.setDesc(desc.text());
            source.setFrom("tp");
            source.setName(na.attr("title"));
            source.setUrl(na.attr("href"));
            source.setImg(na.attr("data-original"));
            source.setKeyword(keyword);
            sources.add(source);
        }
        VideoAnimeSource[] ss = sources.toArray(new VideoAnimeSource[0]);
        HIST.put(keyword, ss);
        return ss;
    }

    @Override
    public VideoAnimeSource get(String keyword, String url) {
        List<VideoAnimeSource> sources = new ArrayList<>();
        Document doc0 = Source.vopipi.doc(keyword);
        Element p0 = doc0.getElementsByClass("stui-vodlist clearfix").get(0);
        for (Element child : p0.children()) {
            Element na = child.getElementsByClass("stui-vodlist__thumb lazyload").get(0);
            String url0 = na.attr("href");
            if (!url0.equals(url)) {
                continue;
            }
            VideoAnimeSource source = new VideoAnimeSource();
            Element st = child.getElementsByClass("pic-text text-right").get(0);
            Element desc = child.getElementsByClass("text text-overflow text-muted hidden-xs").get(0);
            source.setSt(Integer.valueOf(NumberUtils.findNumberFromString(st.text())));
            source.setDesc(desc.text());
            source.setFrom("tp");
            source.setName(na.attr("title"));
            source.setUrl(url0);
            source.setImg(na.attr("data-original"));
            source.setKeyword(keyword);
            sources.add(source);
            source.setDetails(getDetails(url0));
        }
        return null;
    }

    private VideoAnimeDetail[] getDetails(String url0) {
        List<VideoAnimeDetail> sources = new ArrayList<>();
        Document document = Source.vopipi.details(url0);
        Element e0 = document.getElementsByClass("item active").get(0);
        Element e1 = e0.getElementsByClass("play-content").get(0);
        for (Element element : e1.getElementsByTag("li")) {
            VideoAnimeDetail detail = new VideoAnimeDetail();
            Element a = element.getElementsByTag("a").get(0);
            detail.setDesc(a.text());
            detail.setName(a.text());
            try {
                detail.setOrder(Integer.valueOf(a.attr("data-id")));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            detail.setIsVip(true);
            detail.setPlayUrl(a.attr("data-url"));
            detail.setSource("tp");
            detail.setVid(url0);
            sources.add(detail);
        }
        return sources.toArray(new VideoAnimeDetail[0]);
    }
}
