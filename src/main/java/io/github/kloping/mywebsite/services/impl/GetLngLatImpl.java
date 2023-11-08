package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.medias.position.PositionInfo;
import io.github.kloping.mywebsite.services.IgetLngLat;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 */
@Service
public class GetLngLatImpl implements IgetLngLat {

    public static final String BASEU1 = "https://apis.map.qq.com/jsapi?qt=geoc&addr=%s&key=UGMBZ-CINWR-DDRW5-W52AK-D3ENK-ZEBRC&output=jsonp&pf=jsapi&ref=jsapi&cb=qq.maps._svcb3.geocoder0";

    @Override
    public PositionInfo get(String address) throws Exception {
        String urlStr = String.format(BASEU1, address);
        Connection connection = Jsoup.connect(urlStr).ignoreContentType(true).header("Accept", "*/*")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36 Edg/94.0.992.50");

        Document document = connection.get();
        String jsonStr = document.body().text();
        int r = jsonStr.indexOf("{");
        int e = jsonStr.lastIndexOf("}");
        jsonStr = jsonStr.substring(r, e + 1);

        JSONObject jo = JSON.parseObject(jsonStr);
        return jo.toJavaObject(PositionInfo.class);
    }
}
