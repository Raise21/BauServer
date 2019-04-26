package de.daniel.bauserver.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

public class Scoreboard {

    private void registerTeams( final org.bukkit.scoreboard.Scoreboard scoreboard ) {
        scoreboard.registerNewTeam( "builder" ).setPrefix( "§a" );
    }

    private void setupScoreboardTeams( final org.bukkit.scoreboard.Scoreboard scoreboard, final Player player ) {
        final Team map = scoreboard.registerNewTeam( "project" );
        map.setPrefix( " §8• §e" + player.getWorld( ).getName( ) );
        map.addEntry( ChatColor.LIGHT_PURPLE.toString( ) );


    }

    private void setupScoreboardObjective( final Objective objective ) {
        objective.getScore( " " ).setScore( 3 );

        objective.getScore( "§7Projekt§8:" ).setScore( 2 );
        objective.getScore( ChatColor.LIGHT_PURPLE.toString( ) ).setScore( 1 );

        objective.getScore( ChatColor.AQUA.toString( ) + ChatColor.GRAY.toString( ) ).setScore( 0 );
    }

    public void sendSidebar( final Player player ) {

        final org.bukkit.scoreboard.Scoreboard scoreboard = Bukkit.getScoreboardManager( ).getNewScoreboard( );
        final Objective objective = scoreboard.registerNewObjective( "aaa", "bbb" );
        objective.setDisplaySlot( DisplaySlot.SIDEBAR );

        final Team display = scoreboard.registerNewTeam( "display" );
        display.setPrefix( "§eBAUSERVER" );

        objective.setDisplayName( display.getPrefix( ) + display.getSuffix( ) );

        setupScoreboardTeams( scoreboard, player );
        setupScoreboardObjective( objective );

        player.setScoreboard( scoreboard );
    }


    public void updateProjekt( final Player player, final org.bukkit.scoreboard.Scoreboard scoreboard ) {
        scoreboard.getTeam( "project" ).setPrefix( " §8• §e" + player.getWorld( ).getName( ) );
    }


    private void setupTablistTeams( final org.bukkit.scoreboard.Scoreboard scoreboard, final Player player ) {
        if ( scoreboard.getTeam( "builder" ) == null ) {
            registerTeams( scoreboard );
        }
    }

    private void setupTablist( final org.bukkit.scoreboard.Scoreboard scoreboard, final Player player ) {

        scoreboard.getTeam( "builder" ).addPlayer( player );

        Bukkit.getOnlinePlayers( ).forEach( ( all -> {
            final org.bukkit.scoreboard.Scoreboard allScoreboard = all.getScoreboard( );

            if ( allScoreboard.getTeam( "builder" ) == null ) {
                registerTeams( allScoreboard );
            }

            scoreboard.getTeam( "builder" ).addPlayer( all );
            allScoreboard.getTeam( "builder" ).addPlayer( player );
            all.setScoreboard( allScoreboard );
        } ) );
        player.setScoreboard( scoreboard );
        player.setPlayerListName( "§a" + player.getName( ) );

    }

    public void sendTablist( final Player player ) {
        final org.bukkit.scoreboard.Scoreboard scoreboard = player.getScoreboard( );
        setupTablistTeams( scoreboard, player );
        setupTablist( scoreboard, player );
    }


}
