package io.github.kloping.mywebsite.controller;

import io.github.kloping.clasz.ClassUtils;
import io.github.kloping.mywebsite.entitys.ApiDetail;
import io.github.kloping.mywebsite.entitys.ApiDetailM;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * @author github-kloping
 */
@RestController
public class ApiShowDetailController implements ApplicationRunner {

    public static final List<ApiDetailM> DETAIL_LIST = new LinkedList<>();

    static {
        DETAIL_LIST.add(new ApiDetail()
                .setName("搜歌")
                .setState("success")
                .setDesc("通过歌名获取,歌的封面,作者,歌曲直链")
                .setDetail("类型可为:kugou(酷狗音乐),wy(网易音乐),qq(QQ音乐)")
                .setSimpleUrl("/api/search/song?keyword=清空&type=kugou")
                .setAddress("/api/search/song?keyword=关键词&type=类型&n=7")
        );
        DETAIL_LIST.add(new ApiDetail()
                .setName("搜歌(VIP)")
                .setState("success")
                .setDesc("通过歌名获取歌曲直链")
                .setDetail("n数量,依靠其它网站,可能不太稳定")
                .setSimpleUrl("/api/search/vipSong?keyword=霜雪千年")
                .setAddress("/api/search/vipSong?keyword=关键词&n=7")
        );
        DETAIL_LIST.add(new ApiDetail()
                .setName("搜图")
                .setState("success")
                .setDesc("通过关键词获取图片直链")
                .setDetail("类型可为  baidu(百度) duit(堆糖) 也可为空")
                .setAddress("/api/search/pic?keyword=关键词&num=数量&type=类型")
                .setSimpleUrl("/api/search/pic?keyword=原神&num=3&type=duit")
        );
        DETAIL_LIST.add(new ApiDetail()
                .setName("解析图片")
                .setState("success")
                .setDesc("通过快手,抖音 短链接 获取无水印图片")
                .setDetail("类型 可为 ks(快手) dy(抖音)")
                .setAddress("/api/search/parseImgs?url=关键词&type=类型")
                .setSimpleUrl("/api/search/parseImgs?url=https://v.kuaishouapp.com/s/MIaftqoZ&type=ks")
        ); DETAIL_LIST.add(new ApiDetail()
                .setName("解析bgm")
                .setState("success")
                .setDesc("通过短链接获取bgm")
                .setDetail("类型可为 快手 抖音")
                .setAddress("/api/search/parseVoice?url=短链接")
                .setSimpleUrl("/api/search/parseVoice?url=https://v.douyin.com/r1GcyHN/")
        );
        DETAIL_LIST.add(new ApiDetail()
                .setName("天气")
                .setState("success")
                .setDesc("通过地名 获取当地天气详情")
                .setDetail(" 地名 例如: 北京")
                .setAddress("/api/get/weather?address=地名")
                .setSimpleUrl("/api/get/weather?address=合肥")

        );
        DETAIL_LIST.add(new ApiDetail()
                .setName("短时天气")
                .setState("success")
                .setDesc("通过地名 获取当地短时天气")
                .setAddress("/api/get/shortWeather?address=地名")
                .setSimpleUrl("/api/get/shortWeather?address=合肥")
                .setDetail("地名 例如: 北京")
        );
        DETAIL_LIST.add(new ApiDetail()
                .setName("视频搜索")
                .setState("debug")
                .setDesc(" 搜索腾讯爱奇艺视频")
                .setDetail("类型仅可为 iqiyi (爱奇艺) tencent(腾讯视频) all(合并) ,爱奇艺暂时关闭不可用")
                .setSimpleUrl("/api/search/video?keyword=斗罗大陆&type=all")
                .setAddress("/api/search/video?keyword=关键词&type=类型&url=获取集数用")
        );
    }

    @PostMapping("/apid")
    public ApiDetailM m1(HttpServletRequest request, String id) {
        try {
            int i = Integer.parseInt(id);
            if (i < DETAIL_LIST.size()) {
                ApiDetail apiDetail = (ApiDetail) DETAIL_LIST.get(i);
                ApiDetail ad0 = ClassUtils.copyAllField(apiDetail);
                String reu = request.getRequestURL().toString();
                reu = reu.substring(0, reu.lastIndexOf("/"));
                ad0.setAddress(reu + ad0.getAddress());
                return ad0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ERR;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Collections.sort(DETAIL_LIST);
    }

    private static final ApiDetail ERR = new ApiDetail().setAddress("请求错误").setDesc("错误").setName("错误 ");
}
