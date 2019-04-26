package de.daniel.bauserver.listener.connect;

import de.daniel.bauserver.BauServer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin( PlayerJoinEvent event ) {
        event.setJoinMessage( null );
        Player player = event.getPlayer( );
        player.getInventory( ).clear( );
        player.setHealth( 20 );
        player.setFoodLevel( 40 );
        player.getInventory( ).setArmorContents( null );
        player.teleport( BauServer.getInstance( ).getConfiguration( ).getSpawnLocation( ).toLocation( ) );
        player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§aWillkommen auf dem BauServer!" );
        BauServer.getInstance( ).getScoreboard( ).sendSidebar( player );
        BauServer.getInstance( ).getScoreboard( ).sendTablist( player );

        Bukkit.getScheduler( ).runTaskLater( BauServer.getInstance( ), ( ) -> player.setGameMode( GameMode.CREATIVE ), 10 );
    }
}
