package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.ApiDetail;
import io.github.kloping.mywebsite.entitys.ApiDetailM;
import io.github.kloping.mywebsite.entitys.NetMain;
import io.github.kloping.mywebsite.entitys.idiom.M;
import io.github.kloping.mywebsite.services.IGetSongById;
import io.github.kloping.mywebsite.services.impl.Idiom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static io.github.kloping.mywebsite.services.impl.Idiom.idiom;

/**
 * @author github-kloping
 */
@RestController
@RequestMapping("/api/get")
public class Api2Controller {
    static {
        ApiShowController.list.add(new ApiDetailM()
                .setName("查词语")
                .setState("success")
                .setDesc("判断是否是词语(可能不完全")
                .setAddress(NetMain.rootPath + "/api/get/idiom")
        );
        ApiShowController.list.add(new ApiDetailM()
                .setName("获取词语")
                .setState("success")
                .setDesc("获取随机一个四字词语")
                .setAddress(NetMain.rootPath + "/idiom/random")
        );
        //=============
        ApiShowDetailController.list.add(
                new ApiDetail()
                        .setName("查词语")
                        .setState("success")
                        .setDesc("判断是否是词语(可能不完全")
                        .setAddress(NetMain.rootPath + "/api/get/idiom?word=词语")
                        .setDetail("判断是否是词语(可能不完全 参数 word ")
                        .setSimpleUrl("/api/get/idiom?word=为所欲为")
        );
        ApiShowDetailController.list.add(
                new ApiDetail()
                        .setName("查词语")
                        .setState("success")
                        .setDesc("获取随机一个四字词语")
                        .setAddress(NetMain.rootPath + "/api/get/idiom/random")
                        .setDetail("获取随机一个四字词语")
                        .setSimpleUrl("/api/get/idiom/random")
        );
    }

    @RequestMapping("/idiom")
    public M p1(String word) {
        word = word.trim();
        char c1 = word.charAt(0);
        if (idiom.containsKey(c1)) {
            if (idiom.get(c1).contains(word)) {
                String[] strings = new String[4];
                int i = 0;
                for (char c : word.toCharArray()) {
                    strings[i++] = Idiom.getCharPinYin(c);
                }
                return new M().setState(1).setWord(word).setPinyin(strings);
            }
        }
        return new M().setState(-1).setPinyin(null).setWord(word);
    }

    @RequestMapping("/idiom/random")
    public String p2() {
        return Idiom.INSTANCE.getRandom();
    }

    @Autowired
    @Qualifier("getKugouSongById")
    IGetSongById kugouSongGet;

    @Autowired
    @Qualifier("getQQSongById")
    IGetSongById qqSongGet;

    @Autowired
    @Qualifier("getNetEaseSongById")
    IGetSongById wySongGet;

    @RequestMapping("/subi")
    public String p3(@RequestParam("id") String id, @RequestParam("type") String type) {
        switch (type.trim()) {
            case "kugou":
                return kugouSongGet.getUrl(id);
            case "qq":
                return qqSongGet.getUrl(id);
            case "wy":
                return wySongGet.getUrl(id);
            default:
                return "{\"status\":-1}";
        }
    }

}
