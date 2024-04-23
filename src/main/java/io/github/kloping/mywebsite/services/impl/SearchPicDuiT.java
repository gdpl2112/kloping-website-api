package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.entitys.webApi.duiTang.DuiTangResponse;
import io.github.kloping.mywebsite.services.ISearchPic;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import static io.github.kloping.mywebsite.plugins.PluginsSource.duiTangImage;

/**
 * @author github-kloping
 */
@Service
public class SearchPicDuiT implements ISearchPic {
    public static final Entry<String, String> ENTRY0 = new AbstractMap.SimpleEntry<>("js", "1");
    public static final Entry<String, String> ENTRY1 = new AbstractMap.SimpleEntry<>("sessionid", "31962e6b-31ab-4451-ba1e-e4c00f9e64d6");

    @Override
    public String[] searchPics(String keyword, Integer num) throws Exception {
        List<String> list = new LinkedList<>();
        int sn = 0;
        while (list.size() < num) {
            DuiTangResponse response = duiTangImage.method(keyword, sn);
            for (io.github.kloping.mywebsite.entitys.webApi.duiTang.Object_list objectList : response.getData().getObject_list()) {
                String i0 = objectList.getPhoto().getPath();
                list.add(i0);
                if (list.size() >= num) break;
            }
        }
        return list.toArray(new String[0]);
    }
}
