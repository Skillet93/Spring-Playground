package com.company.rs;

import com.company.rs.domain.Weather;
import com.company.rs.domain.WeatherForecast;
import com.company.rs.domain.WeatherForecastProxy;

import java.util.Random;
import java.util.function.Supplier;

public class WeatherCheck {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Classic approach \n");
        execute(WeatherForecast::new);
        System.out.println("Proxy \n");
        execute(WeatherForecastProxy::new);
    }

    private static void execute(Supplier<Weather> forecastInstance) throws InterruptedException {
        long begin = System.currentTimeMillis();


        for (int n = 0; n < 15; n++) {
            Weather weather = forecastInstance.get();
            int number = new Random().nextInt(10);
            if (number < 2) {
                System.out.println("Random update");
                weather.refreshData();
            }
            System.out.println("We have a: " + weather.getWeather());
            System.out.println("Execution #" + n + " just finished");
            System.out.println();
        }

        long end = System.currentTimeMillis();
        System.out.println("The execution took: " + (end - begin) + " [ms]");
    }
}

