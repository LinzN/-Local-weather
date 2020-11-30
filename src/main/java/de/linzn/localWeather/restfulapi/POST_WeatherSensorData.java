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


import de.linzn.localWeather.dataObjects.SensorData;
import de.linzn.restfulapi.api.jsonapi.post.IPostJSON;
import de.stem.stemSystem.STEMSystemApp;
import org.json.JSONObject;

import java.util.List;

public class POST_WeatherSensorData implements IPostJSON {

    @Override
    public Object postDataRequest(List<String> list) {
        JSONObject jsonObject = new JSONObject();
        String post_data = list.get(1);

        for (String data : post_data.split("&")) {
            String name = data.split("=")[0];
            Object value = data.split("=")[1];
            jsonObject.put(name, value);
        }

        STEMSystemApp.LOGGER.DEBUG("Get ESP sensor POST-data: " + jsonObject);
        SensorData sensorData = new SensorData(jsonObject);
        SensorData.setLastSensorData(sensorData);

        return jsonObject;
    }

    @Override
    public String name() {
        return "weather-sensor-data";
    }
}
