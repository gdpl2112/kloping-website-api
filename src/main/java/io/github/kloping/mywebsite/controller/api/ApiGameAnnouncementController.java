package io.github.kloping.mywebsite.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.file.FileUtils;
import io.github.kloping.judge.Judge;
import io.github.kloping.mywebsite.plugins.dto.pvpQqCom.Response0;
import io.github.kloping.mywebsite.plugins.dto.pvpSkin.Pcblzlby_c6;
import io.github.kloping.mywebsite.plugins.dto.pvpSkin.PvpSkin;
import io.github.kloping.url.UrlUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static io.github.kloping.mywebsite.plugins.PluginsSource.getPvpNews;
import static io.github.kloping.mywebsite.plugins.PluginsSource.pvpQq;

/**
 *
 * @author github.kloping
 */
@RestController
@RequestMapping("/api/get")
public class ApiGameAnnouncementController {
    @GetMapping("/pvpqq")
    public Object pvpqq(@RequestParam("n") Integer n) {
        Response0 r0 = getPvpNews.m1();
        Long newsId = r0.getData().getItems()[n].getINewsId().longValue();
        return getPvpNews.getNews(newsId);
    }

    private PvpSkin pvpSkin;
    @GetMapping("/pvpSkin")
    public Object pvpSkin(@RequestParam("n") Integer n) {
        if (pvpSkin == null) pvpSkin = pvpQq.pvpQq.getSkins();
        Integer i = n;
        i = i == null || i >= (pvpSkin.getPcblzlby_c6().length / 5) ? 0 : i;
        Pcblzlby_c6 c6 = pvpSkin.getPcblzlby_c6()[i];
        return "https:" + pvpQq.getSkinPic("https:" + c6.getPcblzlbyxqydz_c4());
    }

    private Map<String, Integer> hero2idMap = new HashMap<>();
    private String heroListTempPath = "./files/heroList.json";

    @GetMapping("/pvp-skin")
    public Object pvp_skin(@RequestParam("name") String name) throws Exception {
        Integer nid = null;
        if (hero2idMap.isEmpty()) {
            String jsonArr = FileUtils.getStringFromFile(heroListTempPath);
            if (Judge.isEmpty(jsonArr)) {
                jsonArr = UrlUtils.getStringFromHttpUrl("https://pvp.qq.com/web201605/js/herolist.json");
                FileUtils.putStringInFile(jsonArr, new File(heroListTempPath));
            }
            JSONArray array = JSON.parseArray(jsonArr);
            for (Object o : array) {
                JSONObject jo = (JSONObject) o;
                String cname = jo.getString("cname");
                Integer id = jo.getInteger("ename");
                hero2idMap.put(cname, id);
                if (name.equals(cname)) nid = id;
            }
        }
        if (nid == null) nid = hero2idMap.get(name);
        String url0 = String.format("https://pvp.qq.com/web201605/herodetail/%s.shtml", nid);
        Document doc0 = Jsoup.connect(url0).get();
        Elements es = doc0.getElementsByClass("pic-pf-list3");
        Element element = es.get(0);
        String data0 = element.attr("data-imgname");
        List<JSONObject> list = new LinkedList<>();
        int index = 1;
        for (String e : data0.split("\\|")) {
            String sname = e.substring(0, e.indexOf("&"));
            String surl = String.format("https://game.gtimg.cn/images/yxzj/img201606/skin/hero-info/%s/%s-bigskin-%s.jpg", nid, nid, index);
            index++;
            JSONObject jo = new JSONObject();
            jo.put("name", String.format("%s-%s", name, sname));
            jo.put("pic", surl);
            list.add(jo);
        }
        return list;
    }

    @Scheduled(cron = "8 15 10 * * ? *")
    public void tempDelete() {
        hero2idMap.clear();
        new File(heroListTempPath).delete();
    }
}
