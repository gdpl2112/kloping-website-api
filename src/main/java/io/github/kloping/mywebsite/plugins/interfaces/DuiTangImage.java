package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.*;
import io.github.kloping.mywebsite.domain.bo.duiTang.DuiTangResponse;

/**
 * @author github.kloping
 */
@HttpClient("https://www.duitang.com/")
public interface DuiTangImage {
    /**
     * get response
     *
     * @param kw
     * @param afterId
     * @return
     */
    @GetPath("napi/blogv2/list/by_search//")
    @CookieFrom("https://www.duitang.com/")
    DuiTangResponse method(@ParamName("kw") String kw, @ParamName("after_id") @DefaultValue("0") Integer afterId);
}
