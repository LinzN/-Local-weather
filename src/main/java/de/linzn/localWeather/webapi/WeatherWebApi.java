package de.linzn.localWeather.webapi;

import com.sun.net.httpserver.HttpExchange;
import de.linzn.localWeather.LocalWeatherPlugin;
import de.linzn.localWeather.engine.ForeCastDay;
import de.linzn.localWeather.engine.WeatherContainer;
import de.linzn.webapi.core.HttpRequestClientPayload;
import de.linzn.webapi.modules.RequestInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class WeatherWebApi extends RequestInterface {
    @Override
    public Object callHttpEvent(HttpExchange httpExchange, HttpRequestClientPayload httpRequestClientPayload) throws IOException {
        JSONObject jsonObject = new JSONObject();

        int weatherID = -1;
        String description = "N.A";
        double current = -1;
        double minTemp = -1;
        double maxTemp = -1;
        String location = "None";
        double humidity = 0;
        double pressure = 0;
        JSONObject forecast = null;


        WeatherContainer weatherContainer = LocalWeatherPlugin.localWeatherPlugin.getWeatherData();
        if (weatherContainer != null) {
            weatherID = weatherContainer.getICON();
            description = weatherContainer.getWeatherDescription();
            current = weatherContainer.getTemp();
            minTemp = weatherContainer.getTemp_min();
            maxTemp = weatherContainer.getTemp_max();
            location = weatherContainer.getLocation();
            pressure = weatherContainer.getPressure();
            humidity = weatherContainer.getHumidity();

            if (weatherContainer.hasForecast()) {
                forecast = new JSONObject();
                forecast.put("cnt", weatherContainer.getForecast().getCnt());
                forecast.put("location", weatherContainer.getForecast().getLocation());
                JSONArray days = new JSONArray();
                for (int i = 0; i < weatherContainer.getForecast().getCnt(); i++) {
                    ForeCastDay foreCastDay = weatherContainer.getForecast().getForecast(i);
                    JSONObject day = new JSONObject();
                    day.put("maxTemp", foreCastDay.getMaxTemp());
                    day.put("minTemp", foreCastDay.getMinTemp());
                    day.put("speed", foreCastDay.getSpeed());
                    day.put("clouds", foreCastDay.getClouds());
                    day.put("description", foreCastDay.getDescription());
                    day.put("main", foreCastDay.getMain());
                    day.put("date", foreCastDay.getDate());
                    day.put("dayText", new SimpleDateFormat("EEEEE", Locale.GERMAN).format(foreCastDay.getDate()));
                    days.put(day);
                }
                forecast.put("forecasts", days);

            }
        }

        jsonObject.put("weatherID", weatherID);
        jsonObject.put("description", description);
        jsonObject.put("currentTemp", current);
        jsonObject.put("minTemp", minTemp);
        jsonObject.put("maxTemp", maxTemp);
        jsonObject.put("location", location);
        jsonObject.put("pressure", pressure);
        jsonObject.put("humidity", humidity);

        if (forecast != null) {
            jsonObject.put("forecast", forecast);
        }

        return jsonObject;
    }
}