package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.*;
import io.github.kloping.mywebsite.entitys.webApi.qqLyric.QQLyric;
import io.github.kloping.mywebsite.entitys.webApi.qqDetail.QQMusicDataList;
import io.github.kloping.mywebsite.entitys.webApi.qqDetail.QQSongDetail;
import io.github.kloping.mywebsite.entitys.webApi.qqOneSong.QQOneSong;

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
     * @param cr
     * @param aggr
     * @param flagQc
     * @param format
     * @param p      跳过页数
     * @param n      单页数量
     * @param w      keyword
     * @return
     */
    @GetPath("https://c.y.qq.com/soso/fcgi-bin/client_search_cp")
    QQSongDetail qqDetail(
            @ParamName("cr")
            @DefaultValue("1")
                    String cr,
            @ParamName("aggr")
            @DefaultValue("1")
                    String aggr,
            @ParamName("flag_qc")
            @DefaultValue("0")
                    String flagQc,
            @ParamName("format")
            @DefaultValue("json")
                    String format,
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
            @ParamName("format")
            @DefaultValue("json")
                    String format,
            @ParamName("data")
                    QQMusicDataList data,
            @Headers Map<String, String> headers
    );

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
            @ParamName("format")
            @DefaultValue("json")
                    String format,
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


}
