package de.daniel.bauserver.listener.environment;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {

    @EventHandler
    public void onWeaherChange( final WeatherChangeEvent event) {
        event.setCancelled(true);
    }

}
