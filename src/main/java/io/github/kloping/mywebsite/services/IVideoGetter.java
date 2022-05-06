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
}
