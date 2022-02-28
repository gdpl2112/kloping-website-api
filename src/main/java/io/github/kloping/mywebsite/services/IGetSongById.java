package io.github.kloping.mywebsite.services;

import io.github.kloping.mywebsite.entitys.medias.Song;

/**
 * @author github kloping
 * @version 1.0
 */
public interface IGetSongById {
    /**
     * get song/url by id
     *
     * @param id
     * @return
     */
    String getUrl(String id);
}
