package de.linzn.weather.webapi;

import de.linzn.webapi.WebApiPlugin;
import de.linzn.webapi.modules.WebModule;
import de.stem.stemSystem.modules.pluginModule.STEMPlugin;

public class WebApiHandler {

    private final STEMPlugin stemPlugin;
    private final WebModule stemWebModule;

    public WebApiHandler(STEMPlugin stemPlugin) {
        this.stemPlugin = stemPlugin;
        stemWebModule = new WebModule("weather");
        stemWebModule.registerSubCallHandler(new WeatherWebApi(), "current");
        WebApiPlugin.webApiPlugin.getWebServer().enableCallModule(stemWebModule);
    }
}
