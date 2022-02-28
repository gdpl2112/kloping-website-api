package io.github.kloping.mywebsite.services;

import io.github.kloping.mywebsite.entitys.medias.WeatherDetail;

public interface IWeather {
    WeatherDetail get(String keyword) throws Exception;
}
