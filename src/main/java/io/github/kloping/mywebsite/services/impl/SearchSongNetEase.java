package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.domain.bo.medias.Songs;
import io.github.kloping.mywebsite.plugins.detail.NetEaseDetail;
import io.github.kloping.mywebsite.services.ISearchSong;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 */
@Service
public class SearchSongNetEase implements ISearchSong {

    @Override
    public Songs searchSong(String keyword, int num) {
        return NetEaseDetail.songs(keyword, num);
    }
}
