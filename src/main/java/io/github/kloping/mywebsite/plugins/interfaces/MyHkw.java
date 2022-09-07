package io.github.kloping.mywebsite.plugins.interfaces;

/**
 * @author github.kloping
 */

import com.alibaba.fastjson.JSONObject;
import io.github.kloping.MySpringTool.annotations.http.*;
import io.github.kloping.mywebsite.entitys.vipSong.VipSong;

/**
 * @author github.kloping
 */
@HttpClient("https://myhkw.cn")
public interface MyHkw {
    /**
     * callback=jQuery111309717157480077416_1662507806712
     * * &
     * * types=search
     * * &
     * * count=20
     * * &
     * * source=tencent
     * * &
     * * pages=1
     * * &
     * * name=%E9%9C%9C%E9%9B%AA%E5%8D%83%E5%B9%B4
     * * &
     * * _=1662507806714
     * *
     *
     * @param callback
     * @param type
     * @param count
     * @param source
     * @param pages
     * @param name
     * @param _t
     * @return
     */
    @GetPath("/api/web")
    @Callback("io.github.kloping.mywebsite.plugins.detail.All.doc1")
    VipSong[] songs(
            @ParamName("callback")
            @DefaultValue("jQuery111309717157480077416_16625078067125")
                    String callback,
            @ParamName("types")
            @DefaultValue("search")
                    String type,
            @ParamName("count")
            @DefaultValue("20")
                    Integer count,
            @ParamName("source")
                    String source,
            @ParamName("pages")
                    Integer pages,
            @ParamName("name")
                    String name,
            @ParamName("_")
                    Long _t);


    /**
     * get lyric
     *
     * @param callback
     * @param type
     * @param songId
     * @param id
     * @param _t
     * @return
     */
    @GetPath("/api/musicLyric")
    @Callback("io.github.kloping.mywebsite.plugins.detail.All.doc1")
    JSONObject lyric(
            @ParamName("callback")
            @DefaultValue("jQuery111306097725972736946_1662512762634")
                    String callback,
            @ParamName("type")
                    String type,
            @ParamName("songId")
                    String songId,
            @ParamName("id")
                    String id,
            @ParamName("_")
                    Long _t
    );
}
