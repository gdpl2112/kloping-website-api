package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.plugins.PluginsSource;
import io.github.kloping.mywebsite.plugins.detail.BaiduImageDetail;
import io.github.kloping.mywebsite.services.ISearchPic;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author github-kloping
 */
@Service
public class SearchPicMany implements ISearchPic {
    @Override
    public String[] searchPics(String keyword, Integer num) throws Exception {
        List<String> list = new LinkedList<>();
        int pn = 0;
        int rn = 30;
        while (list.size() < num) {
            String[] ss = PluginsSource.baiduImage.images(null, null, null, null, null, keyword, rn, pn, BaiduImageDetail.HEADERS);
            for (String s : ss) {
                list.add(s);
                if (list.size() >= num) break;
            }
            pn++;
        }
        return list.toArray(new String[0]);
    }
}
