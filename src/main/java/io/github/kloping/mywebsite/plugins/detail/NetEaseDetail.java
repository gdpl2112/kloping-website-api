package io.github.kloping.mywebsite.plugins.detail;

import io.github.kloping.mywebsite.entitys.medias.Song;
import io.github.kloping.mywebsite.entitys.medias.Songs;
import io.github.kloping.mywebsite.plugins.entity.NetEaseSongs.Artists;
import io.github.kloping.mywebsite.plugins.entity.NetEaseSongs.NetEaseSongs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import static io.github.kloping.mywebsite.plugins.Source.getEntry;
import static io.github.kloping.mywebsite.plugins.Source.netEaseMusic;

/**
 * @author github-kloping
 * @version 1.0
 */
public class NetEaseDetail {
    public static Entry<String, String> OFFSET_ENTRY = getEntry("offset", "0");
    public static Entry<String, String> TYPE_ENTRY = getEntry("type", "1");
    public static Entry<String, String> NMTID_ENTRY = getEntry("NMTID", "00OD6v28qj6pKz_UkWbhj4K8i7ro-8AAAF7zh3LOA");

    public static Songs songs(String keyword, Integer num) {
        NetEaseSongs netEaseSongs = netEaseMusic.songs(
                getEntry("s", keyword.trim()),
                OFFSET_ENTRY,
                getEntry("limit", num.toString()),
                TYPE_ENTRY,
                NMTID_ENTRY
        );
        List<Song> songs = new ArrayList<>();
        for (io.github.kloping.mywebsite.plugins.entity.NetEaseSongs.Songs song : netEaseSongs.getResult().getSongs()) {
            songs.add(new Song()
                    .setSongUrl("http://music.163.com/song/media/outer/url?id=" + song.getId() + ".mp3")
                    .setLyric(netEaseMusic.getLyric(song.getId().intValue()).getLyric())
                    .setAuthor_name(getArtistsName(song.getArtists()))
                    .setImgUrl(song.getAlbum().getPicUrl())
                    .setMedia_name(song.getName())
                    .setId(song.getId().toString())
            );
        }
        return new Songs(0, netEaseSongs.getResult().getSongCount().intValue(), System.currentTimeMillis(),
                keyword, songs.toArray(new Song[0]), "wy");
    }

    private static String getArtistsName(Artists[] artists) {
        StringBuilder sb = new StringBuilder();
        for (Artists artist : artists) {
            sb.append(artist.getName()).append(",");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }
}
