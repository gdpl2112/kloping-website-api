package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.PathValue;
import io.github.kloping.MySpringTool.annotations.http.GetPath;
import io.github.kloping.MySpringTool.annotations.http.HttpClient;
import io.github.kloping.MySpringTool.annotations.http.PostPath;
import io.github.kloping.MySpringTool.annotations.http.RequestBody;
import org.jsoup.nodes.Document;

/**
 * @author github.kloping
 */
@HttpClient("http://www.dwh99.cn:8000")
public interface Dwh99 {
    /**
     * post
     *
     * @param body
     * @return
     */
    @PostPath("/MuS/search")
    Document search(@RequestBody String body);

    /**
     * path
     *
     * @param path
     * @return
     */
    @GetPath("{path}")
    Document path(@PathValue("path") String path);
}
