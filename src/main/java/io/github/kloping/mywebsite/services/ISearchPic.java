package io.github.kloping.mywebsite.services;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

public interface ISearchPic {
    String[] searchPics(String keyword,@Nullable Integer num) throws Exception;
}
