package io.github.kloping.mywebsite.plugins.detail;

import com.alibaba.fastjson.JSON;
import io.github.kloping.mywebsite.entitys.medias.Song;
import io.github.kloping.mywebsite.entitys.medias.Songs;
import io.github.kloping.mywebsite.plugins.Source;
import io.github.kloping.mywebsite.plugins.entity.QqLyric.QQLyric;
import io.github.kloping.mywebsite.plugins.entity.qqDetail.QQMusicDataList;
import io.github.kloping.mywebsite.plugins.entity.qqDetail.QQSongDetail;
import io.github.kloping.mywebsite.plugins.entity.qqDetail.Singer;
import io.github.kloping.mywebsite.plugins.entity.qqOneSong.Data;
import io.github.kloping.mywebsite.plugins.entity.qqOneSong.QQOneSong;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.github.kloping.mywebsite.plugins.Source.qqMusic;

/**
 * @author github-kloping
 * @version 1.0
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class QQMusicDetail {

    public static final Map<String, String> HEADERS = new ConcurrentHashMap<>();
    public static final Map<String, String> HEADERS_LYRIC = new ConcurrentHashMap<>();

    static {
        HEADERS.put("Accept",
                "text/*,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");

        HEADERS_LYRIC.put("origin", "https://y.qq.com");
        HEADERS_LYRIC.put("referer", "https://y.qq.com/");
    }

    public static String getImgUrlDataByAlbumMid(String albummid) {
        return String.format("http://y.gtimg.cn/music/photo_new/T002R300x300M000%s.jpg", albummid);
    }

    public static String filterBase64(String json) {
        QQLyric qqLyric = JSON.toJavaObject(JSON.parseObject(json), QQLyric.class);
        qqLyric.setLyric(Source.decodeBySunMisc(qqLyric.getLyric()));
        return JSON.toJSONString(qqLyric);
    }

    public static String getUrlById(String id) {
        try {
            String jsonData = "{\"req_0\":{\"module\":\"vkey.GetVkeyServer\",\"method\":\"CgiGetVkey\",\"param\":{\"guid\":\"358840384\",\"songmid\":[\"" + id + "\"],\"songtype\":[0],\"uin\":\"1443481947\",\"loginflag\":1,\"platform\":\"20\"}},\"comm\":{\"uin\":\"18585073516\",\"format\":\"json\",\"ct\":24,\"cv\":0}}\n";
            jsonData = URLEncoder.encode(jsonData, "utf-8");
            QQOneSong qqOneSong = qqMusic.oneDetail(null, jsonData, HEADERS);
            Data data = qqOneSong.getReq_0().getData();
            String urlEnd = data.getMidurlinfo()[0].getPurl();
            return getT0(data, urlEnd);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Songs songs(String keyword, int num) {
        QQSongDetail songDetail = qqMusic.qqDetail(null, null, null, null, 0,
                num, keyword);
        List<Song> songs = new ArrayList<>();
        for (QQMusicDataList list : songDetail.getData().getSong().getList()) {
            Song song = new Song();
            QQOneSong qqOneSong = qqMusic.oneDetail(null, list, HEADERS);
            io.github.kloping.mywebsite.plugins.entity.qqOneSong.Data data = qqOneSong.getReq_0().getData();
            String urlEnd = data.getMidurlinfo()[0].getPurl();
            String lyric = qqMusic.getLyric(null, null, null, null, null, null, null, null, null, null, null, null,
                    list.getSongmid(),
                    System.currentTimeMillis(),
                    QQMusicDetail.HEADERS_LYRIC
            ).getLyric();
            song.setLyric(lyric)
                    .setSongUrl(getT0(data, urlEnd))
                    .setMedia_name(list.getSongname())
                    .setImgUrl(getImgUrlDataByAlbumMid(list.getAlbummid()))
                    .setAuthor_name(getSingersName(list.getSinger()))
                    .setId(list.getSongmid())
            ;
            songs.add(song);
        }
        return new Songs(0, songs.size(), System.currentTimeMillis(), keyword, songs.toArray(new Song[0]), "qq");
    }

    public static String getSingersName(Singer[] singer) {
        StringBuilder sb = new StringBuilder();
        for (Singer singer1 : singer) {
            sb.append(singer1.getName()).append(",");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    private static String getT0(Data data, String urlEnd) {
        for (String urlStr : data.getSip()) {
            String url = urlStr + urlEnd;
            if (Source.checkUrl(url)) {
                return url;
            }
        }
        return "";
    }
}
