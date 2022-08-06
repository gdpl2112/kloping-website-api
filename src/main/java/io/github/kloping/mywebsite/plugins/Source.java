package io.github.kloping.mywebsite.plugins;

import io.github.kloping.MySpringTool.StarterApplication;
import io.github.kloping.MySpringTool.annotations.AutoStand;
import io.github.kloping.MySpringTool.annotations.CommentScan;
import io.github.kloping.MySpringTool.annotations.Schedule;
import io.github.kloping.mywebsite.plugins.detail.GetPvpNews;
import io.github.kloping.mywebsite.plugins.detail.PvpQq;
import io.github.kloping.mywebsite.plugins.detail.RunAll;
import io.github.kloping.mywebsite.plugins.interfaces.*;
import io.github.kloping.mywebsite.services.impl.VideoGetterIqiyiImpl;
import io.github.kloping.mywebsite.services.impl.VideoGetterTencentImpl;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.Map;

/**
 * @author github-kloping
 * @version 1.0
 */
@CommentScan(path = "io.github.kloping.mywebsite.plugins")
public class Source {

    public static void before() {
        StarterApplication.run(Source.class);
    }

    static {
        before();
    }

    @AutoStand
    public static KugouMusic kugou;

    @AutoStand
    public static DouyinPic0 douyinPic0;

    @AutoStand
    public static NetEaseMusic netEaseMusic;

    @AutoStand
    public static QQMusic qqMusic;

    @AutoStand
    public static BaiduMap baiduMap;

    @AutoStand
    public static BaiduImage baiduImage;

    @AutoStand
    public static DuiTangImage duiTangImage;

    @AutoStand
    public static Iqiyi iqiyi;

    @AutoStand
    public static Iqiyi0 iqiyi0;

    @AutoStand
    public static TencentVideo tencentVideo;

    @AutoStand
    public static TencentVideo0 tencentVideo0;

    @AutoStand
    public static Vopipi vopipi;

    @AutoStand
    public static Empty empty;

    @AutoStand
    public static GetPvpNews getPvpNews;

    @AutoStand
    public static PvpQq pvpQq;

    @AutoStand
    public static RunAll runAll;

    /**
     * sun.misc方式Base64编码
     *
     * @param str
     * @return
     */
    public static String encodeBySunMisc(String str) {
        try {
            return new BASE64Encoder().encode(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * sun.misc方式Base64解码
     *
     * @param str
     * @return
     */
    public static String decodeBySunMisc(String str) {
        try {
            byte[] result = new BASE64Decoder().decodeBuffer(str);
            return new String(result);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean checkUrl(String urlString) {
        URL url;
        try {
            url = new URL(urlString);
            return true;
        } catch (Exception e1) {
            url = null;
            return false;
        }
    }

    public static <K, V> Map.Entry<K, V> getEntry(K k, V v) {
        return new AbstractMap.SimpleEntry<>(k, v);
    }

    @Schedule("10:00:05,12:00:05,00:00:05,14:00:05")
    public static final void m0() {
        VideoGetterIqiyiImpl.HIST.clear();
        VideoGetterTencentImpl.HIST.clear();
    }
}
