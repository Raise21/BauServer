package de.daniel.bauserver.commands;

import de.daniel.bauserver.BauServer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {


    @Override
    public boolean onCommand( final CommandSender commandSender, final Command command, final String label, final String[] args ) {

        final Player player = ( Player ) commandSender;
        player.teleport( BauServer.getInstance( ).getConfiguration( ).getSpawnLocation( ).toLocation( ) );
        player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "Du wurdest zum §eSpawn §7teleportiert§8." );
        player.playSound( player.getLocation( ), Sound.LEVEL_UP, 3f, 3f );
        return true;
    }
}
