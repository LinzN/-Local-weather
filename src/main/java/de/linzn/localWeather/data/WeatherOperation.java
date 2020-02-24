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

package de.linzn.localWeather.data;


import de.azcore.azcoreRuntime.taskManagment.operations.TaskOperation;
import de.linzn.localWeather.LocalWeatherPlugin;
import de.linzn.localWeather.engine.WeatherEngine;

public class WeatherOperation {

    public static TaskOperation wetter_current_operation = object -> {
        String location = object.getStringSetting("weather.location");
        String key = LocalWeatherPlugin.localWeatherPlugin.getDefaultConfig().getString("weather.apiKey");
        return new WeatherEngine(key).parseWeather(location);
    };
}
