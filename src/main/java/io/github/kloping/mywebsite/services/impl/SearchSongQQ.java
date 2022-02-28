package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.entitys.medias.Songs;
import io.github.kloping.mywebsite.plugins.detail.QQMusicDetail;
import io.github.kloping.mywebsite.services.ISearchSong;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 */
@Service
public class SearchSongQQ implements ISearchSong {
    @Override
    public Songs searchSong(String keyword) throws Exception {
        return searchSong(keyword, 7);
    }

    @Override
    public Songs searchSong(String keyword, int num) throws Exception {
        return QQMusicDetail.songs(keyword, num);
    }
}
