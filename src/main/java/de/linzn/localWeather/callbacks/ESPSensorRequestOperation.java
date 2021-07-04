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

package de.linzn.localWeather.callbacks;


import de.linzn.localWeather.LocalWeatherPlugin;
import de.linzn.localWeather.engine.WeatherEngine;
import de.stem.stemSystem.taskManagment.operations.AbstractOperation;
import de.stem.stemSystem.taskManagment.operations.OperationOutput;
import org.json.JSONObject;


public class ESPSensorRequestOperation extends AbstractOperation {

    @Override
    public OperationOutput runOperation() {
        OperationOutput operationOutput = new OperationOutput(this);
        JSONObject jsonObject = WeatherEngine.readJsonFromUrl("http://" + LocalWeatherPlugin.localWeatherPlugin.getDefaultConfig().getString("espMCU.sensor.address"));
        if (jsonObject != null) {
            operationOutput.setData(jsonObject);
            operationOutput.setExit(0);
        } else {
            operationOutput.setExit(1);
        }
        return operationOutput;
    }
}
