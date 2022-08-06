package io.github.kloping.mywebsite.controller;

import io.github.kloping.mywebsite.entitys.ApiDetail;
import io.github.kloping.mywebsite.entitys.ApiDetailM;
import io.github.kloping.mywebsite.entitys.idiom.M;
import io.github.kloping.mywebsite.entitys.medias.PositionM;
import io.github.kloping.mywebsite.entitys.medias.WeatherDetail;
import io.github.kloping.mywebsite.entitys.medias.WeatherM;
import io.github.kloping.mywebsite.services.IGetSongById;
import io.github.kloping.mywebsite.services.IShortTimeWeather;
import io.github.kloping.mywebsite.services.IWeather;
import io.github.kloping.mywebsite.services.IgetLngLat;
import io.github.kloping.mywebsite.services.impl.Idiom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static io.github.kloping.mywebsite.services.impl.Idiom.idiom;

/**
 * @author github-kloping
 */
@RestController
@RequestMapping("/api/get")
public class ApiIdiomController {
    static {
        ApiShowController.LIST.add(new ApiDetailM()
                .setName("查词语")
                .setState("success")
                .setDesc("判断是否是词语(可能不完全")
        );
        ApiShowController.LIST.add(new ApiDetailM()
                .setName("获取词语")
                .setState("success")
                .setDesc("获取随机一个四字词语")
        );
        //=============
        ApiShowDetailController.LIST.add(
                new ApiDetail()
                        .setName("查词语")
                        .setState("success")
                        .setDesc("判断是否是词语(可能不完全")
                        .setAddress("/api/get/idiom?word=词语")
                        .setDetail("判断是否是词语(可能不完全 参数 word ")
                        .setSimpleUrl("/api/get/idiom?word=为所欲为")
        );
        ApiShowDetailController.LIST.add(
                new ApiDetail()
                        .setName("查词语")
                        .setState("success")
                        .setDesc("获取随机一个四字词语")
                        .setAddress("/api/get/idiom/random")
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

}
