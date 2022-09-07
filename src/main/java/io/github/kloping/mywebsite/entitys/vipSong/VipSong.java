package io.github.kloping.mywebsite.entitys.vipSong;

public class VipSong {
    private String pic_id;
    private String lyric_id;
    private String[] artist;
    private String album;
    private String name;
    private String id;
    private String url_id;
    private String source;

    public String getPic_id() {
        return this.pic_id;
    }

    public VipSong setPic_id(String pic_id) {
        this.pic_id = pic_id;
        return this;
    }

    public String getLyric_id() {
        return this.lyric_id;
    }

    public VipSong setLyric_id(String lyric_id) {
        this.lyric_id = lyric_id;
        return this;
    }

    public String[] getArtist() {
        return this.artist;
    }

    public VipSong setArtist(String[] artist) {
        this.artist = artist;
        return this;
    }

    public String getAlbum() {
        return this.album;
    }

    public VipSong setAlbum(String album) {
        this.album = album;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public VipSong setName(String name) {
        this.name = name;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public VipSong setId(String id) {
        this.id = id;
        return this;
    }

    public String getUrl_id() {
        return this.url_id;
    }

    public VipSong setUrl_id(String url_id) {
        this.url_id = url_id;
        return this;
    }

    public String getSource() {
        return this.source;
    }

    public VipSong setSource(String source) {
        this.source = source;
        return this;
    }

    public String getAllArtists() {
        return this.artist[0].replaceAll(",", ";");
    }

    public String getType() {
        switch (source) {
            case "tencent":
                return "qq";
            case "kugou":
                return "kg";
            case "netease":
                return "wy";
            default:
                return "";
        }
    }
}