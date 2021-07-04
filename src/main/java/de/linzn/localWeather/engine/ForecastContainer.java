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


public class ForecastContainer {

    private final JSONObject jsonObject;

    ForecastContainer(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public int getCnt() {
        return jsonObject.getInt("cnt");
    }

    public ForeCastDay getForecast(int day) {
        return new ForeCastDay(jsonObject.getJSONArray("list").getJSONObject(day));
    }
}

