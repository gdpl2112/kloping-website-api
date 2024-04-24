package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.domain.bo.duiTang.DuiTangResponse;
import io.github.kloping.mywebsite.domain.bo.duiTang.Object_list;
import io.github.kloping.mywebsite.services.ISearchPic;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

import static io.github.kloping.mywebsite.plugins.PluginsSource.duiTangImage;

/**
 * @author github-kloping
 */
@Service
public class SearchPicDuiT implements ISearchPic {
    @Override
    public String[] searchPics(String keyword, Integer num)  {
        List<String> list = new LinkedList<>();
        int sn = 0;
        while (list.size() < num) {
            DuiTangResponse response = duiTangImage.method(keyword, sn);
            for (Object_list objectList : response.getData().getObject_list()) {
                String i0 = objectList.getPhoto().getPath();
                list.add(i0);
                if (list.size() >= num) break;
            }
        }
        return list.toArray(new String[0]);
    }
}
