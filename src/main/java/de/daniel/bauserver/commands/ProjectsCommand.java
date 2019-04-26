package de.daniel.bauserver.commands;

import de.daniel.bauserver.BauServer;
import de.daniel.bauserver.builder.ItemBuilder;
import de.daniel.bauserver.project.Project;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;

public class ProjectsCommand implements CommandExecutor {

    @Override
    public boolean onCommand( final CommandSender commandSender, final Command command, final String label, final String[] args ) {

        final Player player = ( Player ) commandSender;
        if ( BauServer.getInstance( ).getConfiguration( ).getProjects( ).size( ) == 0 ) {
            player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cDerzeit existieren keine Projekte§8." );
            return true;
        } else {
            final Inventory inventory = Bukkit.createInventory( null, 54, "§bÜbersicht aller Projekte" );
            final Iterator projectIterator = BauServer.getInstance( ).getConfiguration( ).getProjects( ).iterator( );

            while ( projectIterator.hasNext( ) ) {
                final Project projects = ( Project ) projectIterator.next( );
                final ItemStack projectItem = BauServer.getInstance( ).getItemBuilder( )
                        .createItemStack( Material.EMPTY_MAP, 0, "§a"
                                + projects.getSpawnLocation( ).toLocation( ).getWorld( ).getName( )
                        , new String[]{"§7Leiter§8: §e"+ Bukkit.getOfflinePlayer(
                                projects.getUuid( ) ).getName( ), "§7Type§8: §e"
                                + projects.getType( ).getType( ), "§7Fertiggestellt§8: §e"
                                + ( projects.isFinished( ) ? "ja" : "nein" ), "§7Mitarbeiter§8: §e"
                                + projects.getMembers( ).size( )} );
                inventory.addItem( projectItem );
            }

            for ( int i = 0; i < inventory.getSize( ); ++i ) {
                if ( inventory.getItem( i ) == null || inventory.getItem( i ).getType( ) == Material.AIR ) {
                    inventory.setItem( i, new ItemBuilder( )
                            .createItemStack( Material.STAINED_GLASS_PANE, 7, " ", 1 ) );
                }
            }

            player.openInventory( inventory );
            player.playSound( player.getLocation( ), Sound.DOOR_CLOSE, 3.0F, 1.0F );
            return true;
        }
    }
}
