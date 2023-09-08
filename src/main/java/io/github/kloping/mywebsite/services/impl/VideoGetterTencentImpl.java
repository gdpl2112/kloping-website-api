package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.VideoAnimeDetail;
import io.github.kloping.mywebsite.entitys.VideoAnimeSource;
import io.github.kloping.mywebsite.entitys.webApi.tencentVideo.DataRequest;
import io.github.kloping.mywebsite.entitys.webApi.tencentVideo.DataResponse;
import io.github.kloping.mywebsite.entitys.webApi.tencentVideo.Item_datas;
import io.github.kloping.mywebsite.entitys.webApi.tencentVideo.Item_params;
import io.github.kloping.mywebsite.services.IVideoGetter;
import io.github.kloping.number.NumberUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;

import static io.github.kloping.mywebsite.plugins.PluginsSource.*;

/**
 * @author github.kloping
 */
@Service
public class VideoGetterTencentImpl implements IVideoGetter {

    public static final Map<String, VideoAnimeSource[]> HIST = new HashMap<>();

    @Override
    public VideoAnimeSource get(String keyword, String url) {
        VideoAnimeSource[] sss0 = search(keyword);
        VideoAnimeSource[] sources = new VideoAnimeSource[sss0.length];
        System.arraycopy(sss0, 0, sources, 0, sss0.length);
        for (VideoAnimeSource source : sources) {
            if (source.getUrl().trim().equals(url.trim())) {
                try {
                    source.setDetails(getDetail(source));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return source;
            }
        }
        return sources[0];
    }

    @Override
    public VideoAnimeSource[] search(String keyword) {
        if (HIST.containsKey(keyword)) return HIST.get(keyword);
        Document doc = tencentVideo.so(keyword);
        Element e0 = doc.getElementsByClass("mix_warp").get(0);
        List<VideoAnimeSource> sources = new ArrayList<>();
        for (Element infos : e0.getElementsByClass("_infos")) {
            try {
                String desc0 = infos.getElementsByClass("desc_text").text();
                String name = infos.getElementsByClass("content").get(0).text();
                String url = infos.getElementsByTag("a").get(0).attr("href");
                String img = infos.getElementsByTag("img").get(0).attr("src");
                VideoAnimeSource source = new VideoAnimeSource()
                        .setDesc(desc0).setFrom("tencent")
                        .setKeyword(keyword).setName(name)
                        .setUrl(url).setImg(filterProtocol(img));
                VideoAnimeDetail[] details = getDetail(source);
                source.setDetails(new VideoAnimeDetail[]{});
                source.setSt(details.length);
                sources.add(source);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Element e1 = doc.getElementsByClass("result_series_new").get(0);
//        for (Element element0 : e1.getElementsByTag("li")) {
//            try {
//                String desc0 = element0.getElementsByClass("figures_list").text();
//                Element a1 = element0.getElementsByTag("a").get(0);
//                String name = a1.attr("title");
//                String url = a1.attr("href");
//                String img = element0.getElementsByTag("img").get(0).attr("src");
//                VideoAnimeSource source = new VideoAnimeSource()
//                        .setDesc(desc0).setFrom("tencent")
//                        .setKeyword(keyword).setName(name)
//                        .setUrl(url).setImg(filterProtocol(img));
//                VideoAnimeDetail[] details = getDetail(source);
//                source.setDetails(details);
//                source.setSt(details.length);
//                sources.add(source);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        try {
            String h1 = e1.toString();
            int i1 = h1.indexOf("totalData: '");
            int i2 = h1.lastIndexOf(";\n" +
                    "\t\tsearchParam");
            h1 = h1.substring(i1 + 12, i2 - 1);
            h1 = URLDecoder.decode(h1);
            JSONObject jo = JSON.parseObject(h1);
            for (Object o : jo.getJSONArray("itemList")) {
                JSONObject jo1 = (JSONObject) o;
                jo1 = jo1.getJSONObject("videoInfo");
                String desc0 = jo1.getString("descrip");
                String name = jo1.getString("title");
                String img = jo1.getString("imgUrl");
                String url = jo1.getString("url");
                VideoAnimeSource source = new VideoAnimeSource()
                        .setDesc(desc0).setFrom("tencent")
                        .setKeyword(keyword).setName(name)
                        .setUrl(url).setImg(filterProtocol(img));
                VideoAnimeDetail[] details = getDetail(source);
                source.setDetails(new VideoAnimeDetail[]{});
                source.setSt(details.length);
                sources.add(source);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        VideoAnimeSource[] ss = sources.toArray(new VideoAnimeSource[0]);
        HIST.put(keyword, ss);
        return ss;
    }

    public static final Map<String, String> HEADERS = new HashMap<>();
    public static final Entry<String, String> COOKIES = new AbstractMap.SimpleEntry<>("vversion_name", "8.2.95");

    static {
        HEADERS.put("origin", "https://v.qq.com");
        HEADERS.put("referer", "https://v.qq.com/");
        HEADERS.put("content-type", "application/json");
    }

    private VideoAnimeDetail[] getDetail(VideoAnimeSource source) throws Exception {
        List<VideoAnimeDetail> details = new LinkedList<>();
        Document doc0 = empty.empty(source.url);
        Element e0 = doc0.getElementsByTag("head").get(0);
        Elements es = e0.getElementsByTag("script");
        e0 = es.get(es.size() - 1);
        String s0 = e0.html();
        int i1 = s0.indexOf("{");
        int i2 = s0.lastIndexOf("}");
        s0 = s0.substring(i1, i2 + 1);
        JSONObject jo0 = JSON.parseObject(s0);
        String vid = jo0.getJSONObject("global").getJSONObject("coverInfo").getJSONArray("video_ids").get(0).toString();
        String cid = null;
        cid = source.url.substring(source.url.lastIndexOf("/") + 1, source.url.lastIndexOf("."));
        boolean next = true;
        int num = 0;
        while (next) {
            next = false;
            DataRequest dataRequest = new DataRequest();
            dataRequest.setHas_cache(1);
            dataRequest.getPage_params()
                    .setCid(cid)
                    .setVid(vid)
                    .setId_type("1")
                    .setLid("")
                    .setPage_context("")
                    .setPage_id("vsite_episode_list")
                    .setPage_num("" + (num == 0 ? "" : num))
                    .setPage_size("100")
                    .setPage_type("detail_operation")
                    .setReq_from("web");
            Map<String, String> h0 = new HashMap<>();
            h0.putAll(HEADERS);
            String data = dataRequest.toString();
            h0.put("content-length", String.valueOf(data.getBytes(StandardCharsets.UTF_8).length));
            DataResponse response = tencentVideo0.method(null, null, dataRequest, h0, COOKIES);
            try {
                if (response.getData().getModule_list_datas()[0].getModule_datas()[0].getItem_data_lists().getItem_datas().length == 100)
                    next = true;
                Item_datas[] datas = response.getData().getModule_list_datas()
                        [0].getModule_datas()[0].getItem_data_lists().getItem_datas();
                for (Item_datas d0 : datas) {
                    Item_params params = d0.getItem_params();
                    int order = 0;
                    try {
                        order = Integer.valueOf(NumberUtils.findNumberFromString(params.getC_title_output()));
                    } catch (NumberFormatException e) {
                    }
                    VideoAnimeDetail detail = new VideoAnimeDetail()
                            .setName(params.getUnion_title()).setDesc(params.getPlay_title())
                            .setVid(params.getVid()).setSource("tencent")
                            .setOrder(order)
                            .setPlayUrl(String.format(FORMAT_URL, params.getCid(), params.getVid()))
                            .setIsVip(!params.getImgtag_all().isEmpty());
                    vid = params.getVid();
                    details.add(detail);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            num++;
        }
        return details.toArray(new VideoAnimeDetail[0]);
    }

    public static final String FORMAT_URL = "https://v.qq.com/x/cover/%s/%s.html";

    private String filterProtocol(String img) {
        if (img.startsWith("//"))
            return "https:" + img;
        return null;
    }

}
