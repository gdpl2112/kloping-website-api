package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.*;
import io.github.kloping.mywebsite.domain.bo.fcgPlaySingleSong.FcgPlaySingleSong;
import io.github.kloping.mywebsite.domain.bo.qqLyric.QQLyric;
import io.github.kloping.mywebsite.domain.bo.qqMusicSearchNewPlatform.QqMusicSearchNewPlatform;
import io.github.kloping.mywebsite.domain.bo.qqOneSong.QQOneSong;

import java.util.Map;

/**
 * @author github kloping
 * @version 1.0
 */
@HttpClient("")
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public interface QQMusic {
    /**
     * qq song detail
     *
     * @param p 跳过页数
     * @param n 单页数量
     * @param w keyword
     * @return
     */
    @GetPath("https://c.y.qq.com/soso/fcgi-bin/music_search_new_platform")
    @Callback("io.github.kloping.mywebsite.plugins.detail.All.doc1")
    QqMusicSearchNewPlatform qqDetail(
            @ParamName("cr")
            @DefaultValue("1")
                    String cr,
            @ParamName("aggr")
            @DefaultValue("1")
                    String aggr,
            @ParamName("flag_qc")
            @DefaultValue("0")
                    String flagQc,
            @ParamName("t")
            @DefaultValue("1")
                    String t,
            @ParamName("lossless")
            @DefaultValue("0")
                    String lossless,
            @ParamName("catZhida")
            @DefaultValue("json")
                    String catZhida,
            @ParamName("searchId")
            @DefaultValue("53806572956004615")
                    String sid,
            @ParamName("p")
            @DefaultValue("1")
                    Integer p,
            @ParamName("n")
                    Integer n,
            @ParamName("w")
                    String w);


    /**
     * 单曲详情
     *
     * @param format
     * @param data
     * @param headers
     * @return
     */
    @GetPath("https://u.y.qq.com/cgi-bin/musicu.fcg")
    QQOneSong oneDetail(
            @ParamName("data")
                    String data,
            @Headers Map<String, String> headers
    );

    /**
     * 获取歌词
     *
     * @param cv
     * @param ct
     * @param format
     * @param inCharset
     * @param outCharset
     * @param notice
     * @param platform
     * @param needNewCode
     * @param uin
     * @param g_tk_new_20200303
     * @param g_tk
     * @param loginUin
     * @param songmid           id
     * @param now               时间戳
     * @param headers           头
     * @return
     */
    @GetPath("https://c.y.qq.com/lyric/fcgi-bin/fcg_query_lyric_new.fcg")
    @Callback("io.github.kloping.mywebsite.plugins.detail.QQMusicDetail.filterBase64")
    QQLyric getLyric(
            @ParamName("cv")
            @DefaultValue("4747474")
                    String cv,
            @ParamName("ct")
            @DefaultValue("24")
                    String ct,
            @ParamName("format")
            @DefaultValue("json")
                    String format,
            @ParamName("inCharset")
            @DefaultValue("utf-8")
                    String inCharset,
            @ParamName("outCharset")
            @DefaultValue("utf-8")
                    String outCharset,
            @ParamName("notice")
            @DefaultValue("0")
                    String notice,
            @ParamName("platform")
            @DefaultValue("yqq.json")
                    String platform,
            @ParamName("needNewCode")
            @DefaultValue("1")
                    String needNewCode,
            @ParamName("uin")
            @DefaultValue("0")
                    String uin,
            @ParamName("g_tk_new_20200303")
            @DefaultValue("5381")
                    String g_tk_new_20200303,
            @ParamName("g_tk")
            @DefaultValue("5381")
                    String g_tk,
            @ParamName("loginUin")
            @DefaultValue("0")
                    String loginUin,
            @ParamName("songmid")
                    String songmid,
            @ParamName("_")
                    Long now,
            @Headers Map<String, String> headers
    );


    /**
     * q
     * @param sid
     * @param tpl
     * @param format
     * @param callback
     * @return
     */
    @GetPath("https://c.y.qq.com/v8/fcg-bin/fcg_play_single_song.fcg")
    @Callback("io.github.kloping.mywebsite.plugins.detail.All.doc1")
    FcgPlaySingleSong singleSong(
            @ParamName("songid")
                    String sid,
            @ParamName("tpl")
            @DefaultValue("yqq_song_detail")
                    String tpl,
            @ParamName("format")
            @DefaultValue("jsonp")
                    String format,
            @ParamName("callback")
            @DefaultValue("getOneSongInfoCallback")
                    String callback
    );
}
