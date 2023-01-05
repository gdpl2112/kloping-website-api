package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.PathValue;
import io.github.kloping.MySpringTool.annotations.http.CookieValue;
import io.github.kloping.MySpringTool.annotations.http.GetPath;
import io.github.kloping.MySpringTool.annotations.http.Headers;
import io.github.kloping.MySpringTool.annotations.http.HttpClient;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * @author github.kloping
 */
@HttpClient("")
public interface Empty {
    /**
     * path value
     *
     * @param path
     * @return
     */
    @GetPath("")
    public Document empty(@PathValue String path);

    /**
     * aa
     *
     * @param path
     * @param headers
     * @return
     */
    @GetPath("")
    public Document empty(@PathValue String path, @Headers Map<String, String> headers);

    /**
     * aa
     *
     * @param path
     * @param headers
     * @param cookie
     * @return
     */
    @GetPath("")
    public Document empty(@PathValue String path, @Headers Map<String, String> headers, @CookieValue Map.Entry<String, String> cookie);
}
