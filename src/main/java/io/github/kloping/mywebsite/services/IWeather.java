package io.github.kloping.mywebsite.services;

import io.github.kloping.mywebsite.domain.bo.medias.WeatherDetail;

public interface IWeather {
    WeatherDetail get(String keyword) throws Exception;
}
