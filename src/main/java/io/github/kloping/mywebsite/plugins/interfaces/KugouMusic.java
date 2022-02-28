package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.*;
import io.github.kloping.mywebsite.plugins.entity.kugouDetail.KugouSongDetail;
import io.github.kloping.mywebsite.plugins.entity.kugouSong.KugouSong;

/**
 * @author github kloping
 * @version 1.0
 */
@HttpClient("")
public interface KugouMusic {
    /**
     * @param showtype
     * @param highlight
     * @param tag_aggr
     * @param tagtype
     * @param plat
     * @param sver
     * @param correct
     * @param api_ver
     * @param version
     * @param area_code
     * @param tag
     * @param pagesize  页数量
     * @param page      第几页
     * @param keyword
     * @return
     */
    @GetPath("http://msearchcdn.kugou.com/api/v3/search/song")
    KugouSongDetail getDetail(
            @ParamName("showtype")
            @DefaultValue("14")
                    Integer showtype,
            @ParamName("highlight")
            @DefaultValue("em")
                    String highlight,
            @ParamName("tag_aggr")
            @DefaultValue("1")
                    String tag_aggr,
            @ParamName("tagtype")
            @DefaultValue("全部")
                    String tagtype,
            @ParamName("plat")
            @DefaultValue("0")
                    Integer plat,
            @ParamName("sver")
            @DefaultValue("5")
                    String sver,
            @ParamName("correct")
            @DefaultValue("1")
                    Integer correct,
            @ParamName("api_ver")
            @DefaultValue("1")
                    Integer api_ver,
            @ParamName("version")
            @DefaultValue("9108")
                    Integer version,
            @ParamName("area_code")
            @DefaultValue("1")
                    Integer area_code,
            @ParamName("tag")
            @DefaultValue("1")
                    String tag,
            @ParamName("pagesize")
            @DefaultValue("5")
                    Integer pagesize,
            @ParamName("page")
            @DefaultValue("0")
                    Integer page,
            @ParamName("keyword")
                    String keyword
    );

    /**
     * 单曲 详情
     *
     * @param r
     * @param callback
     * @param mid
     * @param platid
     * @param dfid
     * @param appid
     * @param hash
     * @param album_id
     * @param t0
     * @return
     */
    @GetPath("https://wwwapi.kugou.com/yy/index.php")
    @Callback("io.github.kloping.mywebsite.plugins.detail.KugouDetail.doc0")
    KugouSong getSong(
            @ParamName("r")
            @DefaultValue("play/getdata")
                    String r,
            @ParamName("callback")
            @DefaultValue("jQuery19106540684191386832_1631951369015")
                    String callback,
            @ParamName("mid")
            @DefaultValue("addaef6431178cfccf2780b9609cb133")
                    String mid,
            @ParamName("platid")
            @DefaultValue("4")
                    String platid,
            @ParamName("dfid")
            @DefaultValue("")
                    String dfid,
            @ParamName("appid")
            @DefaultValue("")
                    String appid,
            @ParamName("hash")
                    String hash,
            @ParamName("album_id")
                    String album_id,
            @ParamName("_")
                    Long t0
    );

}
