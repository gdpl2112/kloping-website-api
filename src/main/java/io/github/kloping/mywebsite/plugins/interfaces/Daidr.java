package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.GetPath;
import io.github.kloping.MySpringTool.annotations.http.Headers;
import io.github.kloping.MySpringTool.annotations.http.HttpClient;
import io.github.kloping.MySpringTool.annotations.http.ParamName;
import io.github.kloping.mywebsite.entitys.yuanShen.YuanShenPlayerInfo;

import java.util.Map;

/**
 * @author github.kloping
 */
@HttpClient("https://api.daidr.me/")
public interface Daidr {
    /**
     * info
     *
     * @param uid
     * @param i
     * @param headers
     * @return
     */
    @GetPath("apis/genshinUserinfo")
    YuanShenPlayerInfo info(@ParamName("uid") String uid, @ParamName("server") Integer i, @Headers Map<String, String> headers);
}
