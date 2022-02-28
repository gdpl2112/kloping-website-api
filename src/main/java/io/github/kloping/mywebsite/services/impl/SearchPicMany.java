package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.services.ISearchPic;
import com.hrs.plugins.SearchBaiduPicNum;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 */
@Service
public class SearchPicMany implements ISearchPic {
    @Override
    public String[] searchPics(String keyword, Integer num) throws Exception {
        return SearchBaiduPicNum.searchPic(keyword, num);
    }
}
