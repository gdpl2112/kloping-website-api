package io.github.kloping.mywebsite.services;

import io.github.kloping.mywebsite.entitys.medias.VideoSource;

public interface ISearchVideo {
    VideoSource search(String keyword) throws Exception;
}
