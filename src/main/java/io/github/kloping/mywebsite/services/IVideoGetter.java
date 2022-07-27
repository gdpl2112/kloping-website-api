package io.github.kloping.mywebsite.services;

import io.github.kloping.mywebsite.entitys.VideoAnimeSource;

/**
 * @author github.kloping
 */
public interface IVideoGetter {
    /**
     * get video source
     *
     * @param keyword
     * @return
     */
    VideoAnimeSource[] search(String keyword);

    /**
     * 获取详情
     *
     * @param keyword
     * @param url
     * @return
     */
    default VideoAnimeSource get(String keyword, String url) {
        return null;
    }
}
