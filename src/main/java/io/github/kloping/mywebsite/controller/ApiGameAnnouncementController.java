package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.ApiDetail;
import io.github.kloping.mywebsite.entitys.ApiDetailM;
import io.github.kloping.mywebsite.entitys.pvpQqCom.Response0;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.github.kloping.mywebsite.plugins.Source.getPvpNews;

/**
 * @author github.kloping
 */
@RestController
@RequestMapping("/api/get")
public class ApiGameAnnouncementController {
    static {
        ApiShowController.LIST.add(new ApiDetailM()
                .setName("王者公告")
                .setState("success")
                .setDesc("获取王者荣耀官网公告")
        );
        //=============
        ApiShowDetailController.LIST.add(
                new ApiDetail()
                        .setName("王者公告")
                        .setState("success")
                        .setDesc("获取王者荣耀官网公告")
                        .setAddress("/api/get/pvpqq?n=排序")
                        .setDetail("获取王者荣耀官网公告 n = 0 时 则获取最新公告")
                        .setSimpleUrl("/api/get/pvpqq?n=0")
        );
    }

    @GetMapping("/pvpqq")
    public Object pvpqq(@RequestParam("n") Integer n) {
        Response0 r0 = getPvpNews.m1();
        Long newsId = r0.getData().getItems()[n].getINewsId().longValue();
        return getPvpNews.getNews(newsId);
    }
}
