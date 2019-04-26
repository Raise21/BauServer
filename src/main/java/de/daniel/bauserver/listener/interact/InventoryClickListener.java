package de.daniel.bauserver.listener.interact;

import de.daniel.bauserver.BauServer;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick( final InventoryClickEvent event ) {
        Player player = ( Player ) event.getWhoClicked( );
        ItemStack stack = event.getCurrentItem( );
        if ( event.getInventory( ) != null && stack != null && stack.hasItemMeta( )
                && stack.getItemMeta( ).getDisplayName( ) != null ) {
            if ( event.getInventory( ).getName( ).equalsIgnoreCase( "§bÜbersicht aller Projekte" ) ) {
                event.setCancelled( true );
                if ( stack.getType( ) == Material.EMPTY_MAP ) {
                    player.teleport( BauServer.getInstance( ).getConfiguration( )
                            .getProject( stack.getItemMeta( ).getDisplayName( ).replace( "§a", "" ) )
                            .getSpawnLocation( ).toLocation( ) );
                    player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "Du wurdest zum Projekt §e" +
                            stack.getItemMeta( ).getDisplayName( ).replace( "§a", "" ) +
                            " §7teleportiert" );
                    player.playSound( player.getLocation( ), Sound.ENDERMAN_TELEPORT, 3f, 3f );
                    player.closeInventory( );
                }
            }

        }
    }
}
