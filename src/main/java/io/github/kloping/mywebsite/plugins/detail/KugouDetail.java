package io.github.kloping.mywebsite.plugins.detail;

import com.alibaba.fastjson.JSONObject;
import io.github.kloping.mywebsite.entitys.medias.Song;
import io.github.kloping.mywebsite.entitys.medias.Songs;
import io.github.kloping.mywebsite.entitys.webApi.kugouDetail.Info;
import io.github.kloping.mywebsite.entitys.webApi.kugouDetail.KugouSongDetail;
import io.github.kloping.mywebsite.entitys.webApi.kugouSong.KugouSong;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import static io.github.kloping.mywebsite.plugins.Source.kugou;

/**
 * @author github-kloping
 * @version 1.0
 */
public class KugouDetail {
    public static String doc0(String callbackJson) {
        int i = callbackJson.indexOf("(");
        int i1 = callbackJson.lastIndexOf(")");
        String js = callbackJson.substring(i + 1, i1);
        return js;
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
            if (kugouSong == null || kugouSong.getData() == null) continue;
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

    public static final Entry<String, String> E0 = new AbstractMap.SimpleEntry<>("kg_mid", "a651cd68324a2442d88bbd74c6997e29");

    public static KugouSong getOne(String hash, String id) {
        KugouSong kugouSong = null;
        Document doc = null;
        Connection connection = null;
        String u0 = String.format("https://wwwapi.kugou.com/yy/index.php?r=play/getdata&callback=%s&hash=%s&album_id=%s&_=%s", "jq", hash, id, System.currentTimeMillis());
        connection = Jsoup.connect(u0).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.134 Safari/537.36 Edg/103.0.1264.77")
                .ignoreHttpErrors(true).ignoreContentType(true).cookie(E0.getKey(), E0.getValue());

        try {
            doc = connection.get();
            kugouSong = JSONObject.parseObject(doc0(doc.body().text()), KugouSong.class);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                doc = connection.proxy("123.57.42.227", 8889).get();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            kugouSong = JSONObject.parseObject(doc0(doc.body().text()), KugouSong.class);
        }
//        kugouSong = kugou.getSong(
//                null, null, E0.getValue(), null, null, null,
//                hash, id, System.currentTimeMillis(), E0
//        );
        return kugouSong;
    }
}
