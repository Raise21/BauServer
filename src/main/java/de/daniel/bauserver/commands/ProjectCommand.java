package de.daniel.bauserver.commands;

import de.daniel.bauserver.BauServer;
import de.daniel.bauserver.project.Project;
import de.daniel.bauserver.location.SerializableLocation;
import de.daniel.bauserver.project.ProjectType;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ProjectCommand implements CommandExecutor {
    @Override
    public boolean onCommand( final CommandSender commandSender, final Command command, final String label, final String[] args ) {

        Player player = ( Player ) commandSender;
        if ( args.length == 1 ) {
            if ( args[ 0 ].equalsIgnoreCase( "list" ) ) {
                player.performCommand( "projects" );
            } else if ( args[ 0 ].equalsIgnoreCase( "delete" ) ) {
                if ( !player.hasPermission( "bauserver.project.delete" ) ) {
                    player.sendMessage( BauServer.getInstance( ).getNo_permission( ) );
                    return true;
                }

                if ( BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) ) == null ) {
                    player.sendMessage( BauServer.getInstance( ).getPrefix( )
                            + "§cHier existiert kein Project§8." );
                } else {
                    BauServer.getInstance( ).getConfiguration( ).getProjects( ).remove( BauServer.getInstance( )
                            .getConfiguration( ).getProject( player.getWorld( ).getName( ) ) );
                    player.sendMessage( BauServer.getInstance( ).getPrefix( )
                            + "§aDu hast das Projekt erfolgreich gelöscht§8." );
                }
            } else if ( args[ 0 ].equalsIgnoreCase( "finish" ) ) {
                if ( !player.hasPermission( "bauserver.project.finish" ) ) {
                    player.sendMessage( BauServer.getInstance( ).getNo_permission( ) );
                    return true;
                }

                if ( BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) ) == null ) {
                    player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cHier existiert kein Project§8." );
                } else {
                    if ( BauServer.getInstance( ).getConfiguration( )
                            .getProject( player.getWorld( ).getName( ) ).isFinished( ) ) {
                        player.sendMessage( BauServer.getInstance( ).getPrefix( )
                                + "§cDieses Projekt ist bereits feritggestellt§8." );
                        return true;
                    }

                    if ( BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) )
                            .getUuid( ).toString( ).equalsIgnoreCase( player.getUniqueId( ).toString( ) ) ) {
                        BauServer.getInstance( ).getConfiguration( )
                                .getProject( player.getWorld( ).getName( ) ).setFinished( true );
                        player.sendMessage( BauServer.getInstance( ).getPrefix( )
                                + "§aDu hast das Projekt erfolgreich fertiggestellt§8." );
                    } else {
                        player.sendMessage( BauServer.getInstance( ).getNo_permission( ) );
                    }
                }
            } else if ( args[ 0 ].equalsIgnoreCase( "unfinish" ) ) {
                if ( !player.hasPermission( "bauserver.project.unfinish" ) ) {
                    player.sendMessage( BauServer.getInstance( ).getNo_permission( ) );
                    return true;
                }

                if ( !BauServer.getInstance( ).getConfiguration( )
                        .getProject( player.getWorld( ).getName( ) ).isFinished( ) ) {
                    player.sendMessage( BauServer.getInstance( ).getPrefix( )
                            + "§cDieses Projekt wurde nicht fertiggestellt§8." );
                    return true;
                }

                BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) ).setFinished( false );
                player.sendMessage( BauServer.getInstance( ).getPrefix( )
                        + "§aDu hast das Projekt wieder freigegeben!" );
            } else {
                this.sendHelpMap( player );
            }
        } else if ( args.length == 2 ) {
            if ( args[ 0 ].equalsIgnoreCase( "create" ) ) {
                if ( !player.hasPermission( "bauserver.project.create" ) ) {
                    player.sendMessage( BauServer.getInstance( ).getNo_permission( ) );
                    return true;
                }

                if ( BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) ) == null ) {
                    if ( ProjectType.getType( args[ 1 ] ) == null ) {
                        player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cBitte verwende einen güötigen Type§8:" );
                        final ProjectType[] types = ProjectType.values( );
                        final int type = types.length;

                        for ( int i = 0; i < type; ++i ) {
                            final ProjectType each = types[ i ];
                            player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§8- §c" + each.getType( ) );
                        }
                    } else {
                        final Project project = new Project( player.getUniqueId( ), ProjectType.getType( args[ 1 ] ),
                                SerializableLocation.toLocation( player.getLocation( ) ) );
                        project.getMembers( ).add( player.getUniqueId( ) );
                        BauServer.getInstance( ).getConfiguration( ).getProjects( ).add( project );
                        player.sendMessage( BauServer.getInstance( ).getPrefix( )
                                + "§aDu hast erfolgreich ein neues Projekt hinzugefügt§8." );
                    }
                } else {
                    player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cHier existiert bereits ein Projekt§8." );
                }
            } else if ( args[ 0 ].equalsIgnoreCase( "add" ) ) {
                if ( !player.hasPermission( "bauserver.project.add" ) ) {
                    player.sendMessage( BauServer.getInstance( ).getNo_permission( ) );
                    return true;
                }

                if ( BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) ) == null ) {
                    player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cHier existiert kein Project§8." );
                } else {
                    final Player targetPlayer = Bukkit.getPlayer( args[ 1 ] );
                    if ( targetPlayer == null ) {
                        player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cDer Spieler ist nicht online." );
                        return true;
                    }

                    if ( !BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) )
                            .getUuid( ).toString( ).equalsIgnoreCase( player.getUniqueId( ).toString( ) ) &&
                            !player.hasPermission( "bauserver.project.admin" ) ) {
                        player.sendMessage( BauServer.getInstance( ).getNo_permission( ) );
                    } else {
                        if ( BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) )
                                .getMembers( ).contains( targetPlayer.getUniqueId( ) ) ) {
                            player.sendMessage( BauServer.getInstance( ).getPrefix( )
                                    + "§cDer Spieler ist bereits ein Mitarbeiter§8." );
                            return true;
                        }

                        BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) )
                                .getMembers( ).add( targetPlayer.getUniqueId( ) );
                        player.sendMessage( BauServer.getInstance( ).getPrefix( )
                                + "§aDu hast erfolgreich den Mitarbeiter hinzugefügt§8." );
                    }
                }
            } else if ( args[ 0 ].equalsIgnoreCase( "remove" ) ) {
                if ( !player.hasPermission( "bauserver.project.remove" ) ) {
                    player.sendMessage( BauServer.getInstance( ).getNo_permission( ) );
                    return true;
                }

                if ( BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) ) == null ) {
                    player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§cHier existiert kein Project§8." );
                } else {
                    if ( Bukkit.getOfflinePlayer( args[ 1 ] ) == null ) {
                        player.sendMessage( BauServer.getInstance( ).getPrefix( )
                                + "§cDer Spieler wurde nicht gefunden§8." );
                        return true;
                    }

                    if ( !BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) )
                            .getUuid( ).toString( ).equalsIgnoreCase( player.getUniqueId( ).toString( ) )
                            && !player.hasPermission( "bauserver.project.admin" ) ) {
                        player.sendMessage( BauServer.getInstance( ).getNo_permission( ) );
                    } else {
                        UUID uuid = Bukkit.getOfflinePlayer( args[ 1 ] ).getUniqueId( );
                        if ( !BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) )
                                .getMembers( ).contains( uuid ) ) {
                            player.sendMessage( BauServer.getInstance( ).getPrefix( )
                                    + "§cDer Spieler ist kein Mitarbeiter§8." );
                            return true;
                        }

                        BauServer.getInstance( ).getConfiguration( ).getProject( player.getWorld( ).getName( ) )
                                .getMembers( ).remove( uuid );
                        player.sendMessage( BauServer.getInstance( ).getPrefix( )
                                + "§aDu hast erfolgreich den Mitarbeiter entfernt§8." );
                    }
                }
            } else {
                this.sendHelpMap( player );
            }
        } else {
            this.sendHelpMap( player );
        }

        return true;
    }

    private void sendHelpMap( Player player ) {
        player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§7Information §bBauServer§8:" );
        player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§b/project create <Type> §8- §7Erstelle ein Projekt" );

        final ProjectType[] types = ProjectType.values( );
        final int type = types.length;

        for ( int i = 0; i < type; ++i ) {
            final ProjectType each = types[ i ];
            player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§8- §b" + each.getType( ) );
        }

        player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§b/project add <Spieler> §8- §7Füge einen Mitarbeiter hinzu" );
        player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§b/project remove <Spieler> §8- §7Entferne einen Mitarbeiter" );
        player.sendMessage( " " );
        player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§b/project delete §8- §7Lösche ein Prokekt" );
        player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§b/project finish §8- §7Stelle ein Projekt fertig" );
        player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§b/project unfinish §8- §7Gebe ein Projekt frei" );
        player.sendMessage( BauServer.getInstance( ).getPrefix( ) + "§b/project list §8- §7Öffne die Projektübersicht" );
    }
}
