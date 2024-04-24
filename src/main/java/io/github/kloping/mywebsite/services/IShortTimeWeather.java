package io.github.kloping.mywebsite.services;

import io.github.kloping.mywebsite.domain.bo.medias.WeatherM;

public interface IShortTimeWeather {
    WeatherM getWeather(String lng, String lat);
}
