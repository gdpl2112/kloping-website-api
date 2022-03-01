package io.github.kloping.mywebsite.services.impl;

import io.github.kloping.mywebsite.entitys.medias.PositionM;
import io.github.kloping.mywebsite.plugins.detail.BaiduMapDetail;
import io.github.kloping.mywebsite.services.IgetLngLat;
import org.springframework.stereotype.Service;

import static io.github.kloping.mywebsite.plugins.Source.baiduMap;

/**
 * @author github-kloping
 */
@Service
public class GetLngLatImpl implements IgetLngLat {
    @Override
    public PositionM get(String address) throws Exception {
        return baiduMap.get(BaiduMapDetail.HEADERS, address, null, null, null);
    }
}
