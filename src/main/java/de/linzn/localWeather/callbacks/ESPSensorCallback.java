/*
 * Copyright (C) 2019. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.localWeather.callbacks;

import de.linzn.localWeather.dataObjects.SensorData;
import de.stem.stemSystem.STEMSystemApp;
import de.stem.stemSystem.taskManagment.AbstractCallback;
import de.stem.stemSystem.taskManagment.CallbackTime;
import de.stem.stemSystem.taskManagment.operations.OperationOutput;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;


public class ESPSensorCallback extends AbstractCallback {

    @Override
    public void operation() {
        ESPSensorRequestOperation espSensorRequestOperation = new ESPSensorRequestOperation();
        addOperationData(espSensorRequestOperation);
        STEMSystemApp.LOGGER.DEBUG("Send ESP sensor request");
    }

    @Override
    public void callback(OperationOutput operationOutput) {
        JSONObject jsonObject = (JSONObject) operationOutput.getData();
        if(operationOutput.getExit() == 0){
            STEMSystemApp.LOGGER.DEBUG("Get ESP sensor data: " + jsonObject + " exit " + operationOutput.getExit());
            SensorData sensorData = new SensorData(jsonObject);
            SensorData.setLastSensorData(sensorData);
        } else {
            STEMSystemApp.LOGGER.ERROR("ESP sensor data error! Request end with exit " + operationOutput.getExit());
        }
    }

    @Override
    public CallbackTime getTime() {
        return new CallbackTime(30, 60, TimeUnit.SECONDS);
    }


}
