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

package de.linzn.localWeather;



import de.linzn.localWeather.data.WeatherCallback;
import de.linzn.localWeather.data.WeatherCommand;
import de.linzn.localWeather.engine.WeatherContainer;
import de.stem.stemSystem.STEMSystemApp;
import de.stem.stemSystem.modules.pluginModule.STEMPlugin;


public class LocalWeatherPlugin extends STEMPlugin {

    public static LocalWeatherPlugin localWeatherPlugin;
    private WeatherCallback weatherCallback;

    public LocalWeatherPlugin() {
        localWeatherPlugin = this;
        weatherCallback = new WeatherCallback();
    }

    @Override
    public void onEnable() {
        this.getDefaultConfig().get("weather.apiKey", "xxxxxxxxxxxxxxxxx");
        this.getDefaultConfig().get("weather.defaultLocation", "Blieskastel");
        this.getDefaultConfig().save();
        STEMSystemApp.getInstance().getCommandModule().registerCommand("weather", new WeatherCommand());
        STEMSystemApp.getInstance().getCallBackService().registerCallbackListener(weatherCallback, this);
    }

    @Override
    public void onDisable() {
        STEMSystemApp.getInstance().getCommandModule().unregisterCommand("wetter");
    }

    public WeatherContainer getWeatherData() {
        return this.weatherCallback.getWeatherContainer();
    }
}
