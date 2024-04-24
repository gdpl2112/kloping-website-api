package io.github.kloping.mywebsite.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.domain.bo.medias.Song;
import io.github.kloping.mywebsite.domain.bo.medias.Songs;
import io.github.kloping.mywebsite.services.ISearchSong;
import io.github.kloping.url.UrlUtils;
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
//        return QQMusicDetail.songs(keyword, num);
        String out = UrlUtils.getStringFromHttpUrl("https://api.linhun.vip/api/qqyy?name=" + keyword + "&y=1&n=" + num + "&apiKey=5ff26395f76d3e12b694e1875e37a40a");
        JSONObject jo0 = JSON.parseObject(out);
        Song song = new Song();
        song.setId("");
        song.setLyric("");
        song.setSongUrl(jo0.getString("mp3"))
                .setImgUrl(jo0.getString("img"))
                .setMedia_name(jo0.getString("name"))
                .setAuthor_name(jo0.getString("author"));
        return new Songs(0, 1, System.currentTimeMillis(), keyword, new Song[]{song}, "qq");
    }
}
