package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.entitys.VideoAnimeDetail;
import io.github.kloping.mywebsite.entitys.VideoAnimeSource;
import io.github.kloping.mywebsite.entitys.webApi.tencentVideo.DataRequest;
import io.github.kloping.mywebsite.entitys.webApi.tencentVideo.DataResponse;
import io.github.kloping.mywebsite.entitys.webApi.tencentVideo.Item_datas;
import io.github.kloping.mywebsite.entitys.webApi.tencentVideo.Item_params;
import io.github.kloping.mywebsite.services.IVideoGetter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;

import static io.github.kloping.mywebsite.plugins.Source.*;

/**
 * @author github.kloping
 */
@Service
public class VideoGetterTencentImpl implements IVideoGetter {

    public static final Map<String, VideoAnimeSource[]> HIST = new HashMap<>();

    @Override
    public VideoAnimeSource[] search(String keyword) {
        if (HIST.containsKey(keyword)) return HIST.get(keyword);
        Document doc = tencentVideo.so(keyword);
        Element e0 = doc.getElementsByClass("mix_warp").get(0);
        List<VideoAnimeSource> sources = new ArrayList<>();
        for (Element infos : e0.getElementsByClass("_infos")) {
            String desc0 = infos.getElementsByClass("desc_text").text();
            String name = infos.getElementsByClass("content").get(0).text();
            String url = infos.getElementsByTag("a").get(0).attr("href");
            String img = infos.getElementsByTag("img").get(0).attr("src");
            VideoAnimeSource source = new VideoAnimeSource()
                    .setDesc(desc0).setFrom("tencent")
                    .setKeyword(keyword).setName(name)
                    .setUrl(url).setImg(filterProtocol(img));
            VideoAnimeDetail[] details = getDetail(source);
            source.setDetails(details);
            source.setSt(details.length);
            sources.add(source);
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

    private VideoAnimeDetail[] getDetail(VideoAnimeSource source) {
        List<VideoAnimeDetail> details = new LinkedList<>();
        Document doc0 = empty.empty(source.url);
        String s0 = doc0.getElementsByTag("link").get(1).attr("href");
        String vid = null;
        String cid = null;
        int i = s0.indexOf("cover/");
        s0 = s0.substring(i + 6, s0.length() - 5);
        String[] ss = s0.split("/");
        cid = ss[0];
        vid = ss[1];
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
                    VideoAnimeDetail detail = new VideoAnimeDetail()
                            .setName(params.getUnion_title()).setDesc(params.getPlay_title())
                            .setVid(params.getVid()).setSource("tencent")
                            .setOrder(Integer.valueOf(params.getC_title_output()))
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
