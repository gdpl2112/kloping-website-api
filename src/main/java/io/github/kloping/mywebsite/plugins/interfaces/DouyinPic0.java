package io.github.kloping.mywebsite.plugins.interfaces;

import io.github.kloping.MySpringTool.annotations.http.*;
import io.github.kloping.mywebsite.entitys.webApi.douyPic.DouyPic;

/**
 * @author github kloping
 * @version 1.0
 */
@HttpClient("https://www.iesdouyin.com/")
public interface DouyinPic0 {

    /**
     * 获取抖音图片实例
     *
     * @param itemIds
     * @return
     */
    @GetPath("web/api/v2/aweme/iteminfo/")
    DouyPic pic(@ParamName("item_ids") String itemIds);
}
