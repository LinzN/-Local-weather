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



import de.linzn.localWeather.LocalWeatherPlugin;
import de.linzn.localWeather.engine.WeatherEngine;
import de.stem.stemSystem.taskManagment.operations.AbstractOperation;
import de.stem.stemSystem.taskManagment.operations.OperationOutput;

public class WeatherOperation extends AbstractOperation {
    private String location;

    @Override
    public OperationOutput runOperation() {
        OperationOutput operationOutput = new OperationOutput(this);
        String key = LocalWeatherPlugin.localWeatherPlugin.getDefaultConfig().getString("weather.apiKey");
        operationOutput.setData(new WeatherEngine(key).parseWeather(location));
        operationOutput.setExit(0);
        return operationOutput;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
