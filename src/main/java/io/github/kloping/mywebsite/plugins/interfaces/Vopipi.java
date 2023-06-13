package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.GetPath;
import io.github.kloping.MySpringTool.annotations.http.HttpClient;
import io.github.kloping.MySpringTool.annotations.http.PathValue;
import org.jsoup.nodes.Document;

/**
 * @author github.kloping
 */
@HttpClient("http://v.vopipi.cn/")
public interface Vopipi {
    /**
     * doc search
     * @param keyword
     * @return
     */
    @GetPath("movies/search-{keyword}.html")
    Document doc(@PathValue("keyword") String keyword);

    /**
     * path
     *
     * @param path
     * @return
     */
    @GetPath("{path}")
    Document details(@PathValue("path") String path);
}
