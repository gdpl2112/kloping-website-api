package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.*;
import io.github.kloping.mywebsite.entitys.webApi.pcwiqiyi.Response;

/**
 * @author github.kloping
 */
@HttpClient("https://pcw-api.iqiyi.com/")
public interface Iqiyi0 {
    /**
     * 获取数据
     *
     * @param aid
     * @param page
     * @param size
     * @param callback
     * @return
     */
    @GetPath("albums/album/avlistinfo")
    @Callback("io.github.kloping.mywebsite.plugins.detail.All.doc0")
    Response method(
            @ParamName("aid")
                    String aid,
            @ParamName("page")
            @DefaultValue("1")
                    Integer page,
            @ParamName("size")
            @DefaultValue("2000")
                    Integer size,
            @ParamName("callback")
            @DefaultValue("1")
                    String callback
    );


}
