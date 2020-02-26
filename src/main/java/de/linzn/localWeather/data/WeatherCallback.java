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

import de.azcore.azcoreRuntime.AppLogger;
import de.azcore.azcoreRuntime.taskManagment.AbstractCallback;
import de.azcore.azcoreRuntime.taskManagment.CallbackTime;
import de.azcore.azcoreRuntime.taskManagment.operations.OperationRegister;
import de.azcore.azcoreRuntime.taskManagment.operations.OperationSettings;
import de.azcore.azcoreRuntime.taskManagment.operations.TaskOperation;
import de.azcore.azcoreRuntime.utils.Color;
import de.linzn.localWeather.LocalWeatherPlugin;
import de.linzn.localWeather.engine.WeatherContainer;
import de.linzn.localWeather.engine.WeatherEngine;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class WeatherCallback extends AbstractCallback {

    private WeatherContainer weatherContainer = null;

    @Override
    public void operation() {
        String location = LocalWeatherPlugin.localWeatherPlugin.getDefaultConfig().getString("weather.defaultLocation");
        OperationSettings operationSettings = new OperationSettings();
        operationSettings.addSetting("weather.location", location);
        TaskOperation taskOperation = OperationRegister.getOperation("weather_current");
        addOperationData(taskOperation, operationSettings);

    }

    @Override
    public void callback(Object object) {
        JSONObject weatherObject = (JSONObject) object;
        weatherContainer = WeatherEngine.getWeatherByJSON(weatherObject);
        AppLogger.debug(Color.GREEN + "Weather pull complete");
    }

    @Override
    public CallbackTime getTime() {
        return new CallbackTime(1, 15, TimeUnit.MINUTES);
    }

    public WeatherContainer getWeatherContainer() {
        return weatherContainer;
    }
}
