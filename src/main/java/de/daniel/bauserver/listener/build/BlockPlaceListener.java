package de.daniel.bauserver.listener.build;

import de.daniel.bauserver.BauServer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockBreak( final BlockPlaceEvent event ) {
        final Player player = event.getPlayer( );
        if ( BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) ) == null ) {
            event.setCancelled( true );
            player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cDie Map kann derzeit nicht editiert werden§8." );
        } else if ( !BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) )
                .getMembers( ).contains( player.getUniqueId( ) ) ) {
            event.setCancelled( true );
            player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cDu bist kein Mitglied dieses Projektes§8." );
        } else if ( BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) ).isFinished( ) ) {
            event.setCancelled( true );
            player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cDieses Projekt wurde bereits fertiggestellt§8." );
        }

    }
}
