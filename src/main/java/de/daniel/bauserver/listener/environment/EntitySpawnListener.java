package de.daniel.bauserver.listener.environment;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {

    @EventHandler
    public void onEnitySpawn(final EntitySpawnEvent event) {
        event.setCancelled(true);
    }


}
