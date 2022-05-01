package io.github.kloping.mywebsite.plugins.detail;

import io.github.kloping.mywebsite.entitys.medias.Song;
import io.github.kloping.mywebsite.entitys.medias.Songs;
import io.github.kloping.mywebsite.entitys.webApi.kugouDetail.Info;
import io.github.kloping.mywebsite.entitys.webApi.kugouDetail.KugouSongDetail;
import io.github.kloping.mywebsite.entitys.webApi.kugouSong.KugouSong;

import java.util.ArrayList;
import java.util.List;

import static io.github.kloping.mywebsite.plugins.Source.kugou;

/**
 * @author github-kloping
 * @version 1.0
 */
public class KugouDetail {
    public static String doc0(String callbackJson) {
        int i = callbackJson.indexOf("(");
        int i1 = callbackJson.lastIndexOf(")");
        return callbackJson.substring(i + 1, i1);
    }

    public static KugouSongDetail search(Integer pageSize, Integer page, String keyword) {
        return kugou.getDetail(null, null, null, null, null, null,
                null, null, null, null, null,
                pageSize, page, keyword);
    }

    public static Songs songs(KugouSongDetail detail) {
        List<Song> list = new ArrayList<>();
        Info[] infos = detail.getData().getInfo();
        for (Info info : infos) {
            KugouSong kugouSong = getOne(info.getHash(), info.getAlbum_id());
            Song song = new Song()
                    .setSongUrl(kugouSong.getData().getPlay_url())
                    .setImgUrl(kugouSong.getData().getImg())
                    .setMedia_name(kugouSong.getData().getSong_name())
                    .setAuthor_name(kugouSong.getData().getAuthor_name())
                    .setLyric(kugouSong.getData().getLyrics())
                    .setId(kugouSong.getData().getHash() + "-" + info.getAlbum_id());
            list.add(song);
        }
        Songs songs = new Songs(0, detail.getData().getInfo().length, System.currentTimeMillis(), "", list.toArray(new Song[0]), "kugou");
        return songs;
    }

    public static String getUrlById(String id) {
        String[] ss = id.split("-");
        return getOne(ss[0], ss.length > 1 ? ss[1] : "").getData().getPlay_url();
    }

    public static KugouSong getOne(String hash, String id) {
        KugouSong kugouSong = kugou.getSong(null, null, null, null, null, null, hash, id, System.currentTimeMillis());
        System.out.println(kugouSong);
        return kugouSong;
    }
}
