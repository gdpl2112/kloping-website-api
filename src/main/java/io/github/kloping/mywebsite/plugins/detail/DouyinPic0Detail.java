package io.github.kloping.mywebsite.plugins.detail;

import io.github.kloping.mywebsite.plugins.entity.douyPic.DouyPic;

import java.io.IOException;

import static io.github.kloping.mywebsite.controller.UtilsController.getRedirectUrl;
import static io.github.kloping.mywebsite.plugins.Source.douyinPic0;

/**
 * @author github-kloping
 * @version 1.0
 */
public class DouyinPic0Detail {
    public static String[] getPics(String url) throws IOException {
        String s = getRedirectUrl(url);
        int i1 = s.indexOf("/share/video/");
        int i2 = s.indexOf("/?");
        String id = s.substring(i1 + 13, i2);
        DouyPic douyPic = douyinPic0.pic(id);
        io.github.kloping.mywebsite.plugins.entity.douyPic.Images[] images = douyPic.getItem_list()[0].getImages();
        String[] sss = new String[images.length];
        for (int i = 0; i < images.length; i++) {
            sss[i] = images[i].getUrl_list()[images[i].getUrl_list().length - 1];
        }
        return sss;
    }
}
