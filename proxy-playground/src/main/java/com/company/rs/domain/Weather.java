package com.company.rs.domain;

public interface Weather {
    String getWeather();
    void refreshData() throws InterruptedException;
}
