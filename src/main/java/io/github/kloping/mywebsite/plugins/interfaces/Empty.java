package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.*;
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
    Document empty(@PathValue String path);

    /**
     * aa
     *
     * @param path
     * @param headers
     * @return
     */
    @GetPath("")
    Document empty(@PathValue String path, @Headers Map<String, String> headers);

    /**
     * aa
     *
     * @param path
     * @param headers
     * @param cookie
     * @return
     */
    @GetPath("")
    Document empty(@PathValue String path, @Headers Map<String, String> headers, @CookieValue Map.Entry<String, String> cookie);
}
