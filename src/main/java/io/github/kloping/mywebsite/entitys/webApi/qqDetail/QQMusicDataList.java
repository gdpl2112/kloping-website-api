package io.github.kloping.mywebsite.entitys.webApi.qqDetail;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QQMusicDataList {
    private Preview preview;
    private String songname_hilight;
    private Number belongCD;
    private Number newStatus;
    private Singer[] singer;
    private String albumname_hilight;
    private String lyric_hilight;
    private Number nt;
    private String songmid;
    private Number pure;
    private Number type;
    private Number chinesesinger;
    @JSONField(name = "switch")
    private Number switch_;
    private String albumname;
    private String vid;
    private Number stream;
    private Number tag;
    private Number ver;
    private Number isonly;
    private String docid;
    private String albummid;
    private Number albumid;
    private Number msgid;
    private Pay pay;
    private Number size128;
    private Number sizeflac;
    private Number sizeogg;
    private String songname;
    private Number size320;
    private String strMediaMid;
    private String media_mid;
    private Number t;
    private String lyric;
    private Number sizeape;
    private Number pubtime;
    private Number interval;
    private Number alertid;
    private Number cdIdx;
    private Number songid;

    public Preview getPreview() {
        return this.preview;
    }

    public QQMusicDataList setPreview(Preview preview) {
        this.preview = preview;
        return this;
    }

    public String getSongname_hilight() {
        return this.songname_hilight;
    }

    public QQMusicDataList setSongname_hilight(String songname_hilight) {
        this.songname_hilight = songname_hilight;
        return this;
    }

    public Number getBelongCD() {
        return this.belongCD;
    }

    public QQMusicDataList setBelongCD(Number belongCD) {
        this.belongCD = belongCD;
        return this;
    }

    public Number getNewStatus() {
        return this.newStatus;
    }

    public QQMusicDataList setNewStatus(Number newStatus) {
        this.newStatus = newStatus;
        return this;
    }

    public Singer[] getSinger() {
        return this.singer;
    }

    public QQMusicDataList setSinger(Singer[] singer) {
        this.singer = singer;
        return this;
    }

    public String getAlbumname_hilight() {
        return this.albumname_hilight;
    }

    public QQMusicDataList setAlbumname_hilight(String albumname_hilight) {
        this.albumname_hilight = albumname_hilight;
        return this;
    }

    public String getSongmid() {
        return this.songmid;
    }

    public QQMusicDataList setSongmid(String songmid) {
        this.songmid = songmid;
        return this;
    }

    public Number getNt() {
        return this.nt;
    }

    public QQMusicDataList setNt(Number nt) {
        this.nt = nt;
        return this;
    }

    public String getLyric_hilight() {
        return this.lyric_hilight;
    }

    public QQMusicDataList setLyric_hilight(String lyric_hilight) {
        this.lyric_hilight = lyric_hilight;
        return this;
    }

    public Number getPure() {
        return this.pure;
    }

    public QQMusicDataList setPure(Number pure) {
        this.pure = pure;
        return this;
    }

    public Number getType() {
        return this.type;
    }

    public QQMusicDataList setType(Number type) {
        this.type = type;
        return this;
    }

    public Number getChinesesinger() {
        return this.chinesesinger;
    }

    public QQMusicDataList setChinesesinger(Number chinesesinger) {
        this.chinesesinger = chinesesinger;
        return this;
    }

    public Number getSwitch() {
        return this.switch_;
    }

    public QQMusicDataList setSwitch(Number switch_) {
        this.switch_ = switch_;
        return this;
    }

    public String getAlbumname() {
        return this.albumname;
    }

    public QQMusicDataList setAlbumname(String albumname) {
        this.albumname = albumname;
        return this;
    }

    public String getVid() {
        return this.vid;
    }

    public QQMusicDataList setVid(String vid) {
        this.vid = vid;
        return this;
    }

    public Number getStream() {
        return this.stream;
    }

    public QQMusicDataList setStream(Number stream) {
        this.stream = stream;
        return this;
    }

    public Number getTag() {
        return this.tag;
    }

    public QQMusicDataList setTag(Number tag) {
        this.tag = tag;
        return this;
    }

    public Number getVer() {
        return this.ver;
    }

    public QQMusicDataList setVer(Number ver) {
        this.ver = ver;
        return this;
    }

    public Number getIsonly() {
        return this.isonly;
    }

    public QQMusicDataList setIsonly(Number isonly) {
        this.isonly = isonly;
        return this;
    }

    public String getDocid() {
        return this.docid;
    }

    public QQMusicDataList setDocid(String docid) {
        this.docid = docid;
        return this;
    }

    public String getAlbummid() {
        return this.albummid;
    }

    public QQMusicDataList setAlbummid(String albummid) {
        this.albummid = albummid;
        return this;
    }

    public Number getAlbumid() {
        return this.albumid;
    }

    public QQMusicDataList setAlbumid(Number albumid) {
        this.albumid = albumid;
        return this;
    }

    public Number getMsgid() {
        return this.msgid;
    }

    public QQMusicDataList setMsgid(Number msgid) {
        this.msgid = msgid;
        return this;
    }

    public Pay getPay() {
        return this.pay;
    }

    public QQMusicDataList setPay(Pay pay) {
        this.pay = pay;
        return this;
    }

    public Number getSize128() {
        return this.size128;
    }

    public QQMusicDataList setSize128(Number size128) {
        this.size128 = size128;
        return this;
    }

    public Number getSizeflac() {
        return this.sizeflac;
    }

    public QQMusicDataList setSizeflac(Number sizeflac) {
        this.sizeflac = sizeflac;
        return this;
    }

    public Number getSizeogg() {
        return this.sizeogg;
    }

    public QQMusicDataList setSizeogg(Number sizeogg) {
        this.sizeogg = sizeogg;
        return this;
    }

    public String getSongname() {
        return this.songname;
    }

    public QQMusicDataList setSongname(String songname) {
        this.songname = songname;
        return this;
    }

    public Number getSize320() {
        return this.size320;
    }

    public QQMusicDataList setSize320(Number size320) {
        this.size320 = size320;
        return this;
    }

    public String getStrMediaMid() {
        return this.strMediaMid;
    }

    public QQMusicDataList setStrMediaMid(String strMediaMid) {
        this.strMediaMid = strMediaMid;
        return this;
    }

    public String getMedia_mid() {
        return this.media_mid;
    }

    public QQMusicDataList setMedia_mid(String media_mid) {
        this.media_mid = media_mid;
        return this;
    }

    public Number getT() {
        return this.t;
    }

    public QQMusicDataList setT(Number t) {
        this.t = t;
        return this;
    }

    public String getLyric() {
        return this.lyric;
    }

    public QQMusicDataList setLyric(String lyric) {
        this.lyric = lyric;
        return this;
    }

    public Number getSizeape() {
        return this.sizeape;
    }

    public QQMusicDataList setSizeape(Number sizeape) {
        this.sizeape = sizeape;
        return this;
    }

    public Number getPubtime() {
        return this.pubtime;
    }

    public QQMusicDataList setPubtime(Number pubtime) {
        this.pubtime = pubtime;
        return this;
    }

    public Number getInterval() {
        return this.interval;
    }

    public QQMusicDataList setInterval(Number interval) {
        this.interval = interval;
        return this;
    }

    public Number getAlertid() {
        return this.alertid;
    }

    public QQMusicDataList setAlertid(Number alertid) {
        this.alertid = alertid;
        return this;
    }

    public Number getCdIdx() {
        return this.cdIdx;
    }

    public QQMusicDataList setCdIdx(Number cdIdx) {
        this.cdIdx = cdIdx;
        return this;
    }

    public Number getSongid() {
        return this.songid;
    }

    public QQMusicDataList setSongid(Number songid) {
        this.songid = songid;
        return this;
    }

    @Override
    public String toString() {
        String jsonData = "{\"req_0\":{\"module\":\"vkey.GetVkeyServer\",\"method\":\"CgiGetVkey\",\"param\":{\"guid\":\"358840384\",\"songmid\":[\"" + media_mid + "\"],\"songtype\":[0],\"uin\":\"1443481947\",\"loginflag\":1,\"platform\":\"20\"}},\"comm\":{\"uin\":\"18585073516\",\"format\":\"json\",\"ct\":24,\"cv\":0}}";

        try {
            return URLEncoder.encode(jsonData, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}