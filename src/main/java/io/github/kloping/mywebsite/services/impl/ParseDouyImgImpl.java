package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.plugins.detail.DouyinPic0Detail;
import io.github.kloping.mywebsite.plugins.interfaces.DouyinPic0;
import io.github.kloping.mywebsite.services.IParseImg;
import org.springframework.stereotype.Service;

/**
 * @author github-kloping
 */
@Service
public class ParseDouyImgImpl implements IParseImg {
    @Override
    public String[] parse(String url) throws Exception {
        url = url.trim();
        return DouyinPic0Detail.getPics(url);
    }
}
