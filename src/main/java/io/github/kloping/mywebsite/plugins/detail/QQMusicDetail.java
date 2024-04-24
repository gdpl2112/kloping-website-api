package io.github.kloping.mywebsite.plugins.detail;

import com.alibaba.fastjson.JSON;
import io.github.kloping.mywebsite.domain.bo.medias.Song;
import io.github.kloping.mywebsite.domain.bo.medias.Songs;
import io.github.kloping.mywebsite.domain.bo.fcgPlaySingleSong.FcgPlaySingleSong;
import io.github.kloping.mywebsite.domain.bo.qqDetail.QQMusicDataList;
import io.github.kloping.mywebsite.domain.bo.qqDetail.Singer;
import io.github.kloping.mywebsite.domain.bo.qqLyric.QQLyric;
import io.github.kloping.mywebsite.domain.bo.qqMusicSearchNewPlatform.QqMusicSearchNewPlatform;
import io.github.kloping.mywebsite.domain.bo.qqOneSong.Data;
import io.github.kloping.mywebsite.domain.bo.qqOneSong.QQOneSong;
import io.github.kloping.mywebsite.plugins.PluginsSource;
import org.apache.tomcat.util.codec.binary.Base64;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.github.kloping.mywebsite.plugins.PluginsSource.qqMusic;

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

    public static String filterBase64(String json) {
        QQLyric qqLyric = JSON.toJavaObject(JSON.parseObject(json), QQLyric.class);
        qqLyric.setLyric(PluginsSource.decodeBySunMisc(qqLyric.getLyric()));
        return JSON.toJSONString(qqLyric);
    }

    public static String getUrlById(String id) {
        try {
            String jsonData = "{\"req_0\":{\"module\":\"vkey.GetVkeyServer\",\"method\":\"CgiGetVkey\",\"param\":{\"guid\":\"358840384\",\"songmid\":[\"" + id + "\"],\"songtype\":[0],\"uin\":\"1443481947\",\"loginflag\":1,\"platform\":\"20\"}},\"comm\":{\"uin\":\"18585073516\",\"format\":\"json\",\"ct\":24,\"cv\":0}}\n";
            jsonData = URLEncoder.encode(jsonData, "utf-8");
            QQOneSong qqOneSong = qqMusic.oneDetail(jsonData, HEADERS);
            Data data = qqOneSong.getReq_0().getData();
            String urlEnd = data.getMidurlinfo()[0].getPurl();
            return getT0(data, urlEnd);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Songs songs(String keyword, int num) {
        List<Song> songs = new ArrayList<>();
        QqMusicSearchNewPlatform qm = qqMusic.qqDetail(null, null, null, null, null, null, null,
                0,
                num,
                keyword
        );
        for (io.github.kloping.mywebsite.domain.bo.qqMusicSearchNewPlatform.List list : qm.getData().getSong().getList()) {
            String[] ss = list.getF().split("\\|");
            Song song = new Song();
            FcgPlaySingleSong singleSong = qqMusic.singleSong(ss[0], null, null, null);
            QQMusicDataList dl0 = new QQMusicDataList();
            String mid = singleSong.getData()[0].getMid();
            dl0.setMedia_mid(mid);
            QQOneSong qqOneSong = qqMusic.oneDetail(dl0.toString(), HEADERS);
            Data data = qqOneSong.getReq_0().getData();
            String urlEnd = "http://dl.stream.qqmusic.qq.com/" + data.getMidurlinfo()[0].getPurl();
            String lyric = "";
            try {
                lyric = qqMusic.getLyric(null, null, null, null, null, null, null, null, null, null, null, null,
                        mid,
                        System.currentTimeMillis(),
                        QQMusicDetail.HEADERS_LYRIC).getLyric();
                lyric = new String(Base64.decodeBase64URLSafe(lyric));
            } catch (Exception e) {
                System.err.println("get lyric error");
            }
            song.setLyric(lyric)
                    .setSongUrl(urlEnd)
                    .setMedia_name(ss[1])
                    .setImgUrl(String.format("http://imgcache.qq.com/music/photo/album_300/76/300_albumpic_%s_0.jpg", ss[4]))
                    .setAuthor_name(ss[3])
                    .setId(mid);
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
            if (PluginsSource.checkUrl(url)) {
                return url;
            }
        }
        return "";
    }
}
