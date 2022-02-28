package io.github.kloping.mywebsite.plugins;

import io.github.kloping.MySpringTool.StarterApplication;
import io.github.kloping.MySpringTool.annotations.AutoStand;
import io.github.kloping.MySpringTool.annotations.CommentScan;
import io.github.kloping.mywebsite.plugins.interfaces.DouyinPic0;
import io.github.kloping.mywebsite.plugins.interfaces.KugouMusic;
import io.github.kloping.mywebsite.plugins.interfaces.NetEaseMusic;
import io.github.kloping.mywebsite.plugins.interfaces.QQMusic;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
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
        return new Map.Entry<K, V>() {
            K k_ = k;
            V v_ = v;

            @Override
            public K getKey() {
                return k_;
            }

            @Override
            public V getValue() {
                return v_;
            }

            @Override
            public V setValue(V value) {
                V v1 = v_;
                v_ = value;
                return v1;
            }
        };
    }
}
