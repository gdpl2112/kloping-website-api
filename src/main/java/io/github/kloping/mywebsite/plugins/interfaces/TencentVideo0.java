package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.*;
import io.github.kloping.mywebsite.entitys.webApi.tencentVideo.DataResponse;

import java.util.Map;

/**
 * @author github.kloping
 */
@HttpClient("https://pbaccess.video.qq.com")
public interface TencentVideo0 {
    /**
     * get data
     * https://pbaccess.video.qq.com/trpc.universal_backend_service.page_server_rpc.PageServer/GetPageData?video_appid=3000010&vplatform=2
     *
     * @param video_appid
     * @param vplatform
     * @param request
     * @param headers
     * @param cookie
     * @return
     */
    @PostPath("/trpc.universal_backend_service.page_server_rpc.PageServer/GetPageData")
    DataResponse method(
            @ParamName("video_appid")
            @DefaultValue("3000010")
                    String video_appid,
            @ParamName("vplatform")
            @DefaultValue("2")
                    String vplatform,
            @RequestBody(type = RequestBody.type.toString)
                    Object request,
            @Headers Map<String, String> headers,
            @CookieValue Map.Entry<String, String> cookie
    );
}
