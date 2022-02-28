package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.plugins.detail.KugouDetail;
import io.github.kloping.mywebsite.services.IGetSongById;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 * @version 1.0
 */
@Service
public class GetKugouSongById implements IGetSongById {
    @Override
    public String getUrl(String id) {
        return KugouDetail.getUrlById(id);
    }
}
