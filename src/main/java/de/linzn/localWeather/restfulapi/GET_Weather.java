/*
 * Copyright (C) 2020. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.localWeather.restfulapi;

import de.linzn.localWeather.LocalWeatherPlugin;
import de.linzn.localWeather.engine.ForeCastDay;
import de.linzn.localWeather.engine.WeatherContainer;
import de.linzn.restfulapi.api.jsonapi.IRequest;
import de.linzn.restfulapi.api.jsonapi.RequestData;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class GET_Weather implements IRequest {

    private LocalWeatherPlugin localWeatherPlugin;

    public GET_Weather(LocalWeatherPlugin localWeatherPlugin) {
        this.localWeatherPlugin = localWeatherPlugin;
    }

    @Override
    public Object proceedRequestData(RequestData requestData) {

        int weatherID = -1;
        String description = "N.A";
        double current = -1;
        double minTemp = -1;
        double maxTemp = -1;
        String location = "None";
        double humidity = 0;
        double pressure = 0;
        JSONObject forecast = null;


        WeatherContainer weatherContainer = this.localWeatherPlugin.getWeatherData();
        if (weatherContainer != null) {
            weatherID = weatherContainer.getICON();
            description = weatherContainer.getWeatherDescription();
            current = weatherContainer.getTemp();
            minTemp = weatherContainer.getTemp_min();
            maxTemp = weatherContainer.getTemp_max();
            location = weatherContainer.getLocation();
            pressure = weatherContainer.getPressure();
            humidity = weatherContainer.getHumidity();

            if (weatherContainer.hasForecast()) {
                forecast = new JSONObject();
                forecast.put("cnt", weatherContainer.getForecast().getCnt());
                JSONObject days = new JSONObject();
                for (int i = 0; i < weatherContainer.getForecast().getCnt(); i++) {
                    ForeCastDay foreCastDay = weatherContainer.getForecast().getForecast(i);
                    JSONObject day = new JSONObject();
                    day.put("maxTemp", foreCastDay.getMaxTemp());
                    day.put("minTemp", foreCastDay.getMinTemp());
                    day.put("speed", foreCastDay.getSpeed());
                    day.put("clouds", foreCastDay.getClouds());
                    day.put("description", foreCastDay.getDescription());
                    day.put("main", foreCastDay.getMain());
                    day.put("date", foreCastDay.getDate());
                    day.put("dayText", new SimpleDateFormat("EEEEE", Locale.GERMAN).format(foreCastDay.getDate()));
                    days.put("" + i, day);
                }
                forecast.put("forecasts", days);

            }
        }

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("weatherID", weatherID);
        jsonObject.put("description", description);
        jsonObject.put("currentTemp", current);
        jsonObject.put("minTemp", minTemp);
        jsonObject.put("maxTemp", maxTemp);
        jsonObject.put("location", location);
        jsonObject.put("pressure", pressure);
        jsonObject.put("humidity", humidity);

        if (forecast != null) {
            jsonObject.put("forecast", forecast);
        }


        return jsonObject;
    }

    @Override
    public Object genericData() {
        return proceedRequestData(null);
    }

    @Override
    public String name() {
        return "weather";
    }
}
