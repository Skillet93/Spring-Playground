package com.company.rs.domain;

public class WeatherForecastProxy implements Weather {
    private Weather weather;

    @Override
    public String getWeather() {
        if (weather == null) {
            weather = new WeatherForecast();
        }
        return weather.getWeather();
    }

    public void refreshData() throws InterruptedException {
        if (weather == null) {
            weather = new WeatherForecast();
        } else {
            weather.refreshData();
        }

    }

}
