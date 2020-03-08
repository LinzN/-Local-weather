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


import de.azcore.azcoreRuntime.AZCoreRuntimeApp;
import de.azcore.azcoreRuntime.modules.pluginModule.AZPlugin;
import de.linzn.localWeather.data.WeatherCallback;
import de.linzn.localWeather.data.WeatherCommand;
import de.linzn.localWeather.engine.WeatherContainer;


public class LocalWeatherPlugin extends AZPlugin {

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
        AZCoreRuntimeApp.getInstance().getCommandModule().registerCommand("weather", new WeatherCommand());
        AZCoreRuntimeApp.getInstance().getCallBackService().registerCallbackListener(weatherCallback, this);
    }

    @Override
    public void onDisable() {
        AZCoreRuntimeApp.getInstance().getCommandModule().unregisterCommand("wetter");
    }

    public WeatherContainer getWeatherData() {
        return this.weatherCallback.getWeatherContainer();
    }
}
