package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.*;
import io.github.kloping.mywebsite.entitys.webApi.netEaseSongs.NetEaseSongLyric;
import io.github.kloping.mywebsite.entitys.webApi.netEaseSongs.NetEaseSongs;

import java.util.Map;

/**
 * @author github kloping
 * @version 1.0
 */
@HttpClient("http://music.163.com")
public interface NetEaseMusic {
    /**
     * get songs
     *
     * @param s
     * @param offset
     * @param limit
     * @param type
     * @param cookie0
     * @return
     */
    @PostPath("api/search/pc")
    NetEaseSongs songs(
            @RequestData Map.Entry<String, String> s,
            @RequestData Map.Entry<String, String> offset,
            @RequestData Map.Entry<String, String> limit,
            @RequestData Map.Entry<String, String> type,
            @CookieValue Map.Entry<String, String> cookie0
    );

    /**
     * get lyric
     *
     * @param id
     * @return
     */
    @GetPath("/api/song/media")
    NetEaseSongLyric getLyric(@ParamName("id") Integer id);
}
