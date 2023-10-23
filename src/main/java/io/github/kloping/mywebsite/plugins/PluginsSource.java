package io.github.kloping.mywebsite.plugins;

import io.github.kloping.MySpringTool.StarterApplication;
import io.github.kloping.MySpringTool.annotations.AutoStand;
import io.github.kloping.MySpringTool.annotations.CommentScan;
import io.github.kloping.MySpringTool.annotations.Schedule;
import io.github.kloping.mywebsite.plugins.detail.GetPvpNews;
import io.github.kloping.mywebsite.plugins.detail.PvpQq;
import io.github.kloping.mywebsite.plugins.detail.RunAll;
import io.github.kloping.mywebsite.plugins.interfaces.*;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.Base64;
import java.util.Map;

/**
 * @author github-kloping
 * @version 1.0
 */
@CommentScan(path = "io.github.kloping.mywebsite.plugins")
public class PluginsSource {

    public static void before() {
        StarterApplication.SCAN_LOADER = PluginsSource.class.getClassLoader();
        StarterApplication.run(PluginsSource.class);
    }

    @AutoStand
    public static KugouMusic kugou;
    @AutoStand
    public static NetEaseMusic netEaseMusic;

    @AutoStand
    public static QQMusic qqMusic;

    @AutoStand
    public static BaiduImage baiduImage;

    @AutoStand
    public static DuiTangImage duiTangImage;

    @AutoStand
    public static MyHkw myHkw;

    @AutoStand
    public static IBaiduShitu iBaiduShitu;

    @AutoStand
    public static Daidr daidr;

    @AutoStand
    public static Hamm hamm;

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
            return Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
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
        byte[] result = Base64.getDecoder().decode(str);
        return new String(result);
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
    }
}
