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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class WeatherEngine {


    private String apiKey;

    public WeatherEngine(String apiKey) {
        this.apiKey = apiKey;
    }

    public static WeatherContainer getWeatherByJSON(JSONObject jsonObject) {
        return new WeatherContainer(jsonObject);
    }

    private static JSONObject readJsonFromUrl(String url) throws JSONException {
        try {
            URLConnection con = new URL(url).openConnection();

            InputStream is = con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        } catch (Exception ignored) {
        }
        return null;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public WeatherContainer getWeather(String location) {
        return new WeatherContainer(parseWeather(location));
    }

    public JSONObject parseWeather(String location) {
        String apiLink = "http://api.openweathermap.org/data/2.5/weather?q=" + location + ",de&appid=" + this.apiKey + "&lang=de&units=metric";
        return readJsonFromUrl(apiLink);
    }

}
