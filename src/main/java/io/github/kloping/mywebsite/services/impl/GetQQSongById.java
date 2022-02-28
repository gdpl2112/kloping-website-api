package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.plugins.detail.QQMusicDetail;
import io.github.kloping.mywebsite.services.IGetSongById;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 * @version 1.0
 */
@Service
public class GetQQSongById implements IGetSongById {
    @Override
    public String getUrl(String id) {
        return QQMusicDetail.getUrlById(id);
    }
}
