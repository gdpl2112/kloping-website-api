package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.entitys.VideoAnimeDetail;
import io.github.kloping.mywebsite.entitys.VideoAnimeSource;
import io.github.kloping.mywebsite.services.IVideoGetter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static io.github.kloping.mywebsite.plugins.PluginsSource.empty;

/**
 * @author github.kloping
 */
@Service
public class VideoGetterIqiyiImpl implements IVideoGetter {
    @Override
    public VideoAnimeSource[] search(String keyword) {
//        if (HIST.containsKey(keyword)) return HIST.get(keyword);
//        Document document = iqiyi.so(keyword);
//        Element layoutMain = document.getElementsByClass("layout-main").get(0);
//        Element element = layoutMain.children().get(2);
//        element = Jsoup.parse(element.toString().replaceAll("data-v-[a-z0-9]+", ""));
//        element = element.getElementsByTag("body").get(0).child(0);
//        Elements elements = element.getElementsByClass("item-type");
//        List<VideoAnimeSource> sources = new LinkedList<>();
//        for (Element e0 : elements) {
//            try {
//                Element e1 = e0.parent().parent().parent();
//                sources.add(parse(e1, keyword));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        VideoAnimeSource[] ss = sources.toArray(new VideoAnimeSource[0]);
//        HIST.put(keyword, ss);
//        return ss;
        return new VideoAnimeSource[0];
    }

    public static final Map<String, VideoAnimeSource[]> HIST = new HashMap<>();

    private VideoAnimeSource parse(Element e0, String keyword) throws Exception {
        VideoAnimeSource source = new VideoAnimeSource()
                .setDesc(e0.getElementsByClass("multiple").get(0).getElementsByTag("span").get(0).attr("title"))
                .setFrom("iqiyi").setKeyword(keyword);
        Element a = e0.getElementsByClass("qy-mod-link-wrap").get(0).getElementsByTag("a").get(0);
        source.setName(a.attr("title")).setUrl(filterProtocol(a.attr("href")));
        String img = e0.getElementsByTag("picture").get(0).getElementsByTag("img").get(0).attr("src");
        source.setImg(filterProtocol(img));
        VideoAnimeDetail[] details = getDetail(source);
        source.setSt(details.length);
        source.setDetails(details);
        return source;
    }

    private VideoAnimeDetail[] getDetail(VideoAnimeSource source) throws Exception {
        List<VideoAnimeDetail> details = new LinkedList<>();
        String url = source.getUrl();
        Document document = empty.empty(url);
        String vid = url.substring(url.indexOf("v_") + 2, url.indexOf(".html"));
//        for (Epsodelist epsodelist : iqiyi0.method(aid, 1, 2000, null).getData().getEpsodelist()) {
//            details.add(new VideoAnimeDetail()
//                    .setVid(epsodelist.getVid())
//                    .setPlayUrl(epsodelist.getPlayUrl())
//                    .setIsVip(epsodelist.getPayMark().intValue() > 0)
//                    .setSource("iqiyi")
//                    .setDesc(epsodelist.getSubtitle())
//                    .setName(epsodelist.getName())
//                    .setOrder(epsodelist.getOrder().intValue())
//            );
//        }
//        return details.toArray(new VideoAnimeDetail[0]);
        return new VideoAnimeDetail[0];
    }

    private String filterProtocol(String img) {
        if (img.startsWith("//"))
            return "https:" + img;
        return null;
    }

}
