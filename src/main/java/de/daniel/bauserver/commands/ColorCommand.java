package de.daniel.bauserver.commands;

import de.daniel.bauserver.BauServer;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ColorCommand implements CommandExecutor {

    @Override
    public boolean onCommand( final CommandSender commandSender, final Command command, final String label, final String[] args ) {

        final Player player = ( Player ) commandSender;

        if ( !player.hasPermission( "bauserver.command.color" ) ) {
            player.sendMessage( BauServer.getInstance( ).getPrefix( ) );
            return true;
        }

        if ( player.getInventory( ).getItemInHand( ).getType( ) == Material.LEATHER_BOOTS || player.getInventory( ).getItemInHand( )
                .getType( ) == Material.LEATHER_CHESTPLATE || player.getInventory( ).getItemInHand( )
                .getType( ) == Material.LEATHER_HELMET || player.getInventory( ).getItemInHand( )
                .getType( ) == Material.LEATHER_LEGGINGS ) {

            if ( args.length == 1 ) {
                final LeatherArmorMeta meta;
                final ItemStack itemStack;
                if ( args[ 0 ].equalsIgnoreCase( "blau" ) ) {
                    itemStack = new ItemStack( player.getInventory( ).getItemInHand( ) );
                    meta = ( LeatherArmorMeta ) itemStack.getItemMeta( );
                    meta.setColor( Color.BLUE );
                    itemStack.setItemMeta( meta );
                    player.getItemInHand( ).setItemMeta( meta );
                } else if ( args[ 0 ].equalsIgnoreCase( "rot" ) ) {
                    itemStack = new ItemStack( player.getInventory( ).getItemInHand( ) );
                    meta = ( LeatherArmorMeta ) itemStack.getItemMeta( );
                    meta.setColor( Color.RED );
                    itemStack.setItemMeta( meta );
                    player.getItemInHand( ).setItemMeta( meta );
                } else if ( args[ 0 ].equalsIgnoreCase( "gelb" ) ) {
                    itemStack = new ItemStack( player.getInventory( ).getItemInHand( ) );
                    meta = ( LeatherArmorMeta ) itemStack.getItemMeta( );
                    meta.setColor( Color.YELLOW );
                    itemStack.setItemMeta( meta );
                    player.getItemInHand( ).setItemMeta( meta );
                } else if ( args[ 0 ].equalsIgnoreCase( "grün" ) ) {
                    itemStack = new ItemStack( player.getInventory( ).getItemInHand( ) );
                    meta = ( LeatherArmorMeta ) itemStack.getItemMeta( );
                    meta.setColor( Color.GREEN );
                    itemStack.setItemMeta( meta );
                    player.getItemInHand( ).setItemMeta( meta );
                } else if ( args[ 0 ].equalsIgnoreCase( "orange" ) ) {
                    itemStack = new ItemStack( player.getInventory( ).getItemInHand( ) );
                    meta = ( LeatherArmorMeta ) itemStack.getItemMeta( );
                    meta.setColor( Color.ORANGE );
                    itemStack.setItemMeta( meta );
                    player.getItemInHand( ).setItemMeta( meta );
                } else if ( args[ 0 ].equalsIgnoreCase( "türkis" ) ) {
                    itemStack = new ItemStack( player.getInventory( ).getItemInHand( ) );
                    meta = ( LeatherArmorMeta ) itemStack.getItemMeta( );
                    meta.setColor( Color.AQUA );
                    itemStack.setItemMeta( meta );
                    player.getItemInHand( ).setItemMeta( meta );
                } else if ( args[ 0 ].equalsIgnoreCase( "schwarz" ) ) {
                    itemStack = new ItemStack( player.getInventory( ).getItemInHand( ) );
                    meta = ( LeatherArmorMeta ) itemStack.getItemMeta( );
                    meta.setColor( Color.BLACK );
                    itemStack.setItemMeta( meta );
                    player.getItemInHand( ).setItemMeta( meta );
                } else if ( args[ 0 ].equalsIgnoreCase( "grau" ) ) {
                    itemStack = new ItemStack( player.getInventory( ).getItemInHand( ) );
                    meta = ( LeatherArmorMeta ) itemStack.getItemMeta( );
                    meta.setColor( Color.GRAY );
                    itemStack.setItemMeta( meta );
                    player.getItemInHand( ).setItemMeta( meta );
                } else if ( args[ 0 ].equalsIgnoreCase( "lila" ) ) {
                    itemStack = new ItemStack( player.getInventory( ).getItemInHand( ) );
                    meta = ( LeatherArmorMeta ) itemStack.getItemMeta( );
                    meta.setColor( Color.PURPLE );
                    itemStack.setItemMeta( meta );
                    player.getItemInHand( ).setItemMeta( meta );
                } else {
                    player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "Mögliche Farben§8:" );
                    for ( final String string : BauServer.getInstance( ).getColor( ) ) {
                        player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§8- §e" + string );
                    }
                }
            } else {
                player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cBenutze§8: /§bcolor §8<§eType§8>" );
            }

        } else {
            player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cDu kannst nur Lederrüstung färben§8." );
        }


        return true;
    }
}
