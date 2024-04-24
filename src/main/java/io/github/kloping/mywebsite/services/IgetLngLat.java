package io.github.kloping.mywebsite.services;

import io.github.kloping.mywebsite.domain.bo.position.PositionInfo;

/**
 * @author github-kloping
 */
public interface IgetLngLat {
    PositionInfo get(String address) throws Exception;
}
