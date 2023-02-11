package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.ApiDetail;
import io.github.kloping.mywebsite.entitys.pvpQqCom.Response0;
import io.github.kloping.mywebsite.entitys.pvpSkin.Pcblzlby_c6;
import io.github.kloping.mywebsite.entitys.pvpSkin.PvpSkin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.github.kloping.mywebsite.plugins.Source.getPvpNews;
import static io.github.kloping.mywebsite.plugins.Source.pvpQq;

/**
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

    @GetMapping("/pvpSkin")
    public Object pvpSkin(@RequestParam("n") Integer n) {
        PvpSkin pvpSkin = pvpQq.pvpQq.getSkins();
        Integer i = n;
        i = i == null || i >= (pvpSkin.getPcblzlby_c6().length / 5) ? 0 : i;
        Pcblzlby_c6 c6 = pvpSkin.getPcblzlby_c6()[i];
        return "https:" + pvpQq.getSkinPic("https:" + c6.getPcblzlbyxqydz_c4());
    }
}
