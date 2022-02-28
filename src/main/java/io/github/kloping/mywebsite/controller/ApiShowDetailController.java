package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.ApiDetail;
import io.github.kloping.mywebsite.entitys.ApiDetailM;
import io.github.kloping.mywebsite.entitys.NetMain;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @author github-kloping
 */
@RestController
public class ApiShowDetailController {

    public static final List<ApiDetailM> list = new CopyOnWriteArrayList<>();

    static {
        list.add(new ApiDetail()
                .setName("搜歌")
                .setState("success")
                .setDesc("通过歌名获取,歌的封面,作者,歌曲直链")
                .setAddress(NetMain.rootPath + NetMain.getSongType)
                .setDetail("类型可为:kugou(酷狗音乐),wy(网易音乐),qq(QQ音乐)")
                .setSimpleUrl(NetMain._getSongType)
        );
        list.add(new ApiDetail()
                .setName("搜图")
                .setState("success")
                .setDesc("通过关键词获取图片直链")
                .setAddress(NetMain.rootPath + NetMain.getPic)
                .setDetail("类型可为  baidu(百度) duit(堆糖) 也可为空")
                .setSimpleUrl(NetMain._getPic)
        );
        list.add(new ApiDetail()
                .setName("解析图片")
                .setState("success")
                .setDesc("通过快手,抖音 短链接 获取无水印图片")
                .setAddress(NetMain.rootPath + NetMain.parsePic)
                .setDetail("类型 可为 ks(快手) dy(抖音)")
                .setSimpleUrl(NetMain._parsePic)
        );
        list.add(new ApiDetail()
                .setName("天气")
                .setState("success")
                .setDesc("通过地名 获取当地天气详情")
                .setAddress(NetMain.rootPath + NetMain.getWeatherDetail)
                .setDetail(" 地名 例如: 北京")
                .setSimpleUrl(NetMain._getWeatherDetail)
        );
        list.add(new ApiDetail()
                .setName("短时天气")
                .setState("success")
                .setDesc("通过地名 获取当地短时天气")
                .setAddress(NetMain.rootPath + NetMain.getWeatherShort)
                .setDetail(" 地名 例如: 北京")
                .setSimpleUrl(NetMain._getWeatherShort)
        );
        list.add(new ApiDetail()
                .setName("短视频搜索 (不稳定)")
                .setState("debug")
                .setDesc("搜索 快手或 哔哩的短视频")
                .setAddress(NetMain.rootPath + NetMain.mediaUrl)
                .setDetail("类型仅可为 ks (快手) bili(哔哩)")
                .setSimpleUrl(NetMain._mediaUrl)
        );
        /*list.add(new ApiDetail()
                .setName("视频 搜索 (不稳定)")
                .setState("debug")
                .setDesc("搜索 电视剧 动画 视频")
                .setAddress(rootPath + getVideo)
                .setDetail("st 参数 表示为 第几集 调用后返回链接 下载完成后访问即可")
                .setSimpleUrl(_getVideo)
        );*/
    }

    @PostMapping("/apid")
    public ApiDetailM m1(String id) {
        try {
            int i = Integer.parseInt(id);
            if (i < list.size()) return list.get(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return err;
    }

    private static final ApiDetail err = new ApiDetail().setAddress("请求错误").setDesc("错误").setName("错误 ");
}
