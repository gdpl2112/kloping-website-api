package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.*;
import io.github.kloping.mywebsite.entitys.medias.PositionM;

import java.util.Map;

/**
 * @author github.kloping
 */
@HttpClient("https://api.map.baidu.com")
public interface BaiduMap {
    /**
     * 获取 positionM
     *
     * @param headers
     * @param address
     * @param output
     * @param ak
     * @param callback
     * @return
     */
    @GetPath("/geocoding/v3/")
    @Callback("io.github.kloping.mywebsite.plugins.detail.BaiduMapDetail.doc")
    PositionM get(
            @Headers Map<String, String> headers,
            @ParamName("address")
                    String address,
            @ParamName("output")
            @DefaultValue("json")
                    String output,
            @ParamName("ak")
            @DefaultValue("gQsCAgCrWsuN99ggSIjGn5nO")
                    String ak,
            @ParamName("callback")
            @DefaultValue("showLocation1")
                    String callback);

}
