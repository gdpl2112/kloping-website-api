package io.github.kloping.mywebsite.services;

import io.github.kloping.mywebsite.entitys.medias.position.PositionInfo;

/**
 * @author github-kloping
 */
public interface IgetLngLat {
    PositionInfo get(String address) throws Exception;
}
