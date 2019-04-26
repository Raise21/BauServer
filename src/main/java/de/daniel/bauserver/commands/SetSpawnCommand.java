package de.daniel.bauserver.commands;

import de.daniel.bauserver.BauServer;
import de.daniel.bauserver.location.SerializableLocation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand( final CommandSender commandSender, final Command command, final String label, final String[] args ) {
        final Player player = ( Player ) commandSender;

        if ( !player.hasPermission( "bauserver.command.setspawn" ) ) {
            player.sendMessage( BauServer.getInstance( ).getNo_permission( ) );
            return true;
        }

        BauServer.getInstance( ).getConfiguration( ).setSpawnLocation( SerializableLocation.toLocation( player.getLocation( ) ) );
        BauServer.getInstance( ).getConfiguration( ).save( BauServer.getInstance( ).getFile( ) );
        player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "Du hast erfolgreich den §eSpawn §7gesetzt§8." );

        return true;
    }
}
