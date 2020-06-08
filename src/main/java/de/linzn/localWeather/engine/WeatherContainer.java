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

package de.linzn.localWeather.engine;

import de.linzn.localWeather.DataListener;
import org.json.JSONObject;

import java.text.NumberFormat;

public class WeatherContainer {

    private String location;

    private String weatherDescription;
    private String weatherMain;

    private double temp;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double humidity;

    private double wind_speed;

    private double clouds;

    private int weatherIcon;
    private boolean sensorData;


    public WeatherContainer(JSONObject jsonObject) {
        distribute(jsonObject);
    }

    private void distribute(JSONObject jsonObject) {
        location = jsonObject.getString("name");

        JSONObject weatherObject = jsonObject.getJSONArray("weather").getJSONObject(0);
        weatherDescription = weatherObject.getString("description");
        weatherMain = weatherObject.getString("main");

        JSONObject mainObject = jsonObject.getJSONObject("main");
        temp_min = mainObject.getDouble("temp_min");
        temp_max = mainObject.getDouble("temp_max");

        JSONObject jSONsensorData = DataListener.getSensorData();

        if(jSONsensorData != null){

            temp = round(jSONsensorData.getDouble("temperature"), 2);
            pressure = round(jSONsensorData.getDouble("pressure"), 2);
            humidity = round(jSONsensorData.getDouble("humidity"), 2);
            sensorData = true;
        } else {
            temp = mainObject.getDouble("temp");
            pressure = mainObject.getDouble("pressure");
            humidity = mainObject.getDouble("humidity");
            sensorData = false;
        }

        JSONObject windObject = jsonObject.getJSONObject("wind");
        wind_speed = windObject.getDouble("speed");

        JSONObject cloudsObject = jsonObject.getJSONObject("clouds");
        clouds = cloudsObject.getDouble("all");

        /* Weather icon */
        if (weatherMain.equalsIgnoreCase("clear")) {
            weatherIcon = 0;
        } else if (weatherMain.equalsIgnoreCase("clouds")) {
            weatherIcon = 1;
        } else if (weatherMain.equalsIgnoreCase("drizzle")) {
            weatherIcon = 2;
        } else if (weatherMain.equalsIgnoreCase("rain")) {
            weatherIcon = 3;
        } else if (weatherMain.equalsIgnoreCase("thunderstorm")) {
            weatherIcon = 4;
        } else if (weatherMain.equalsIgnoreCase("snow")) {
            weatherIcon = 5;
        } else if (weatherMain.equalsIgnoreCase("mist")) {
            weatherIcon = 6;
        } else if (weatherMain.equalsIgnoreCase("fog")) {
            weatherIcon = 6;
        }
    }

    public String getLocation() {
        return location;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public double getTemp() {
        return temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public double getClouds() {
        return clouds;
    }

    public int getICON() {
        return weatherIcon;
    }

    public String printData() {
        return "";
    }

    public boolean isSensorData() {
        return sensorData;
    }

    private double round(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.round(value * d) / d;
    }
}
