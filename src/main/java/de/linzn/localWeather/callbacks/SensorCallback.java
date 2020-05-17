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


import de.stem.stemSystem.AppLogger;
import de.stem.stemSystem.taskManagment.AbstractCallback;
import de.stem.stemSystem.taskManagment.CallbackTime;
import de.stem.stemSystem.taskManagment.operations.OperationOutput;
import de.stem.stemSystem.utils.Color;

import java.util.concurrent.TimeUnit;

public class SensorCallback extends AbstractCallback {
    @Override
    public void operation() {
        SensorRequestOperation sensorRequestOperation = new SensorRequestOperation();
        addOperationData(sensorRequestOperation);
    }

    @Override
    public void callback(OperationOutput operationOutput) {
        AppLogger.debug(Color.GREEN + "Sensor data request send");
    }

    @Override
    public CallbackTime getTime() {
        return new CallbackTime(30, 60, TimeUnit.SECONDS);
    }

}
