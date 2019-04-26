package de.daniel.bauserver.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onAsync( final AsyncPlayerChatEvent event ) {
        event.setCancelled( true );

        Bukkit.getOnlinePlayers( ).forEach( ( all -> {
            all.sendMessage( "§a" + event.getPlayer( ).getName( ) + " §8» §7" + event.getMessage( ) );
        } ) );

    }

}
