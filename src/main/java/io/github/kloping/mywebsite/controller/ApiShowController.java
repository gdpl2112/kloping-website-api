package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.ApiDetailM;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @author github-kloping
 */
@RestController
@RequestMapping("/api")
public class ApiShowController {


    public static final List<ApiDetailM> LIST = new CopyOnWriteArrayList<>();

    static {
        LIST.add(new ApiDetailM()
                .setName("搜歌")
                .setState("success")
                .setDesc("通过歌名获取,歌的封面,作者,歌曲直链,歌词,id")
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
    }

    @RequestMapping("/getApiList")
    public List<ApiDetailM> m1() {
        return LIST;
    }

    @RequestMapping("/getCloudPics")
    public List<String> getCloudPic() {
        try {
            String baseUrl = "http://www.nsmc.org.cn/NSMC/datalist/fy2_color.txt";
            String mn = "http://img.nsmc.org.cn/CLOUDIMAGE/FY2/WXCL/%s";
            byte[] bytes = io.github.kloping.url.UrlUtils.getBytesFromHttpUrl(baseUrl);
            String[] pics = new String(bytes, "utf-8").trim().split(",");
            System.out.println(Arrays.toString(pics).replaceAll(",", "\n"));
            for (int i = 0; i < pics.length; i++)
                pics[i] = String.format(mn, pics[i].trim()).trim();
            return Arrays.asList(pics);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}