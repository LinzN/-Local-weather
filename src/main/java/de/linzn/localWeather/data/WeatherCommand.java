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

package de.linzn.localWeather.data;


import de.linzn.localWeather.LocalWeatherPlugin;
import de.linzn.localWeather.dataObjects.SensorData;
import de.linzn.localWeather.engine.WeatherContainer;
import de.linzn.localWeather.engine.WeatherEngine;
import de.stem.stemSystem.STEMSystemApp;
import de.stem.stemSystem.modules.commandModule.ICommand;

public class WeatherCommand implements ICommand {
    @Override
    public boolean executeTerminal(String[] strings) {
        if (strings.length >= 1) {
            String location = strings[0];
            String key = LocalWeatherPlugin.localWeatherPlugin.getDefaultConfig().getString("weather.apiKey");
            WeatherContainer weatherContainer = new WeatherEngine(key).getCurrentWeather(location);
            STEMSystemApp.LOGGER.LIVE(weatherContainer.printData());
        } else {
            if (SensorData.getLastSensorData() != null) {
                long s = SensorData.getLastSensorData().getSecondsSinceSync();
                STEMSystemApp.LOGGER.LIVE("Last sync from esp sensor: ");
                STEMSystemApp.LOGGER.LIVE(s + " seconds ago!");
            } else {
                STEMSystemApp.LOGGER.LIVE("No data since last stem restart!");
            }
        }

        return true;
    }
}
