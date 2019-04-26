package de.daniel.bauserver.listener.world;

import de.daniel.bauserver.BauServer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerWorldChangeListener implements Listener {
    @EventHandler
    public void onPlayerWorldChange( PlayerChangedWorldEvent event ) {
        final Player player = event.getPlayer( );
        Bukkit.getScheduler( ).runTaskLater( BauServer.getInstance( ), ( ) -> player.setGameMode( GameMode.CREATIVE ), 10 );
        BauServer.getInstance( ).getScoreboard( ).updateProjekt( event.getPlayer( ), event.getPlayer( ).getScoreboard( ) );
    }
}
