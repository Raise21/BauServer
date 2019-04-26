package de.daniel.bauserver.listener.environment;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class EntityExplodeListener implements Listener {

    @EventHandler
    public void onEntityExplode( final EntityExplodeEvent event) {
        event.setCancelled(true);
    }

}
