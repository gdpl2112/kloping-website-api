package io.github.kloping.mywebsite.plugins.interfaces;

import com.alibaba.fastjson.JSONObject;
import io.github.kloping.MySpringTool.annotations.http.Headers;
import io.github.kloping.MySpringTool.annotations.http.HttpClient;
import io.github.kloping.MySpringTool.annotations.http.RequestBody;
import io.github.kloping.MySpringTool.annotations.http.RequestPath;
import org.jsoup.Connection;

import java.util.Map;

/**
 * @author github.kloping
 */
@HttpClient("https://api.hamm.cn")
public interface Hamm {
    /**
     * search
     *
     * @param header
     * @param body
     * @return
     */
    @RequestPath(value = "/song/search", method = Connection.Method.POST)
    JSONObject search(@Headers Map<String, String> header, @RequestBody String body);
}
