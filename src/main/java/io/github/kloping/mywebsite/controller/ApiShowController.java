package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.ApiDetailM;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * @author github-kloping
 */
@RestController
@RequestMapping("/api")
public class ApiShowController implements ApplicationRunner {


    public static final List<ApiDetailM> LIST = new LinkedList<>();

    static {
        LIST.add(new ApiDetailM()
                .setName("搜歌")
                .setState("success")
                .setDesc("通过歌名获取,歌的封面,作者,歌曲直链,歌词,id")
                .setAddress("")
        );
        LIST.add(new ApiDetailM()
                .setName("搜歌(VIP)")
                .setState("success")
                .setDesc("通过歌名歌曲直链")
                .setAddress("")
        );
        LIST.add(new ApiDetailM()
                .setName("搜图")
                .setState("success")
                .setDesc("通过关键词获取图片直链")
                .setAddress("")
        );
        LIST.add(new ApiDetailM()
                .setName("解析图片")
                .setState("success")
                .setDesc("通过快手,抖音 短链接 获取无水印图片")
                .setAddress("")
        );
        LIST.add(new ApiDetailM()
                .setName("解析bgm")
                .setState("success")
                .setDesc("通过快手,抖音图集作品 短链接 获取bgm")
                .setAddress("")
        );
        LIST.add(new ApiDetailM()
                .setName("天气")
                .setState("success")
                .setDesc("通过地名 获取当地天气详情")
                .setAddress("")
        );
        LIST.add(new ApiDetailM()
                .setName("短时天气")
                .setState("success")
                .setDesc("通过地名 获取当地短时天气")
                .setAddress("")
        );
        LIST.add(new ApiDetailM()
                .setName("视频搜索")
                .setState("success")
                .setDesc("搜索腾讯爱奇艺视频")
                .setAddress("")
        );
        LIST.add(new ApiDetailM()
                .setName("原神信息查询")
                .setState("success")
                .setDesc("根据uid查询原神玩家信息")
                .setAddress("")
        );
    }

    @RequestMapping("/getApiList")
    public List<ApiDetailM> m1() {
        return LIST;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Collections.sort(LIST);
    }
}