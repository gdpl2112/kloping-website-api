package io.github.kloping.mywebsite.services;

import io.github.kloping.mywebsite.entitys.medias.Songs;

/**
 * @author github-kloping
 */
public interface ISearchSong {
    /**
     * search songs
     *
     * @param keyword
     * @return
     * @throws Exception
     */
    Songs searchSong(String keyword) throws Exception;

    /**
     * search songs
     *
     * @param keyword
     * @param num
     * @return
     * @throws Exception
     */
    Songs searchSong(String keyword, int num) throws Exception;
}

