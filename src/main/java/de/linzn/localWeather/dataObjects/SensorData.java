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

package de.linzn.localWeather.dataObjects;


import org.json.JSONObject;

import java.util.Date;

public class SensorData {

    private static final int upToDateMinutes = 10;

    private Date date;
    private double temperature;
    private double pressure;
    private double humidity;
    private String location;


    public SensorData(JSONObject jsonObject) {
        this.date = new Date();
        this.temperature = round(jsonObject.getDouble("temperature"), 2);
        this.pressure = round(jsonObject.getDouble("pressure"), 2);
        this.humidity = round(jsonObject.getDouble("humidity"), 2);
        this.location = "Wetterstation";
    }

    public Date getDate() {
        return this.date;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public double getPressure() {
        return this.pressure;
    }

    public double getHumidity() {
        return this.humidity;
    }

    private double round(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.round(value * d) / d;
    }

    public boolean isUpToDate() {
        return new Date().getTime() <= this.date.getTime() + (upToDateMinutes * 60 * 1000);
    }

    public String getLocation() {
        return location;
    }
}
