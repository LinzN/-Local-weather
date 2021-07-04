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

package de.linzn.localWeather.engine;

import org.json.JSONObject;

import java.util.Date;

public class ForeCastDay {
    private final JSONObject data;

    public ForeCastDay(JSONObject jsonObject) {
        data = jsonObject;
    }

    public double getMinTemp() {
        return data.getJSONObject("temp").getDouble("min");
    }

    public double getMaxTemp() {
        return data.getJSONObject("temp").getDouble("max");
    }

    public double getAverageTemp() {
        return data.getJSONObject("temp").getDouble("day");
    }

    public Date getDate() {
        return new Date(data.getLong("dt") * 1000);
    }

    public String getDescription() {
        return data.getJSONArray("weather").getJSONObject(0).getString("description");
    }

    public double getClouds() {
        return data.getDouble("clouds");
    }
}
