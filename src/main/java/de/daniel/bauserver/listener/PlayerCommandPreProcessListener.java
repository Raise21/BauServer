package de.daniel.bauserver.listener;

import de.daniel.bauserver.BauServer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreProcessListener implements Listener {

    @EventHandler
    public void onPlayerCommandPreProcess( PlayerCommandPreprocessEvent event ) {
        if ( event.getMessage( ).startsWith( "//" ) || event.getMessage( ).toLowerCase( ).startsWith( "/setblock" ) ) {
            if ( BauServer.getInstance( ).getConfiguration( ).getProject( event.getPlayer( ).getWorld( ).getName( ) ) == null ) {
                event.setCancelled( true );
                event.getPlayer( ).sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cHier ist kein Projekt§8." );
                return;
            }

            if ( !BauServer.getInstance( ).getConfiguration( ).getProject( event.getPlayer( ).getWorld( ).getName( ) ).getMembers( )
                    .contains( event.getPlayer( ).getUniqueId( ) ) ) {
                event.setCancelled( true );
                event.getPlayer( ).sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cDu bist kein Mitglied dieses Projektes§8." );
                return;
            }

            if ( BauServer.getInstance( ).getConfiguration( ).getProject( event.getPlayer( ).getWorld( ).getName( ) ).isFinished( ) ) {
                event.setCancelled( true );
                event.getPlayer( ).sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cDiese Map wurde bereits fertiggestellt§8." );
            }
        }

    }

}
