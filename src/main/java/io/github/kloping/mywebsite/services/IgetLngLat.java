package io.github.kloping.mywebsite.services;

import io.github.kloping.mywebsite.entitys.medias.PositionM;

/**
 * @author github-kloping
 */
public interface IgetLngLat {
    PositionM get(String address) throws Exception;
}
