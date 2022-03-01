package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.*;

import java.util.Map;

/**
 * @author github.kloping
 */
@HttpClient("https://image.baidu.com")
public interface BaiduImage {
    /**
     * get images
     *
     * @param tn
     * @param ipn
     * @param fp
     * @param ie
     * @param oe
     * @param word
     * @param rn
     * @param pn
     * @param headers
     * @return
     */
    @GetPath("search/acjson")
    @Callback("io.github.kloping.mywebsite.plugins.detail.BaiduImageDetail.doc")
    @CookieFrom("this")
    String[] images(
            @ParamName("tn")
            @DefaultValue("resultjson_com")
                    String tn,
            @ParamName("ipn")
            @DefaultValue("rj")
                    String ipn,
            @ParamName("fp")
            @DefaultValue("result")
                    String fp,
            @ParamName("ie")
            @DefaultValue("utf-8")
                    String ie,
            @ParamName("oe")
            @DefaultValue("utf-8")
                    String oe,
            @ParamName("word")
                    String word,
            @ParamName("rn")
                    Integer rn,
            @ParamName("pn")
                    Integer pn,
            @Headers Map<String, String> headers
    );
}
