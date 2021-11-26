package com.company.rs.domain;

public class WeatherForecast implements Weather {

    public WeatherForecast()  {
        try {
            refreshData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String getWeather(){
        return "Good weather";
    }

    public void refreshData() throws InterruptedException {
        System.out.println("Refreshing data");
        Thread.sleep(5000);
    }
}
