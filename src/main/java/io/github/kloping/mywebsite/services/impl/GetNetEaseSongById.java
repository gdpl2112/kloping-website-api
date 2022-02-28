package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.services.IGetSongById;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 * @version 1.0
 */
@Service
public class GetNetEaseSongById implements IGetSongById {
    @Override
    public String getUrl(String id) {
        return "http://music.163.com/song/media/outer/url?id=" + id + ".mp3\"";
    }
}
