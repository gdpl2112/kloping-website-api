package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.GetPath;
import io.github.kloping.MySpringTool.annotations.http.HttpClient;
import io.github.kloping.MySpringTool.annotations.http.ParamName;
import org.jsoup.nodes.Document;

/**
 * @author github.kloping
 */
@HttpClient("https://v.qq.com/")
public interface TencentVideo {
    /**
     * search
     * https://v.qq.com/x/search/?q=%E6%96%97%E7%BD%97%E5%A4%A7%E9%99%86     * @param name
     *
     * @return
     */
    @GetPath("x/search/")
    Document so(@ParamName("q") String name);
}
