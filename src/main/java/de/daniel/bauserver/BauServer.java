package de.daniel.bauserver;

import com.google.gson.Gson;
import de.daniel.bauserver.builder.ItemBuilder;
import de.daniel.bauserver.commands.*;
import de.daniel.bauserver.configuration.BauServerConfiguration;
import de.daniel.bauserver.scoreboard.Scoreboard;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BauServer extends JavaPlugin {

    @Getter
    private static BauServer instance;
    @Getter
    private final String prefix = "§8» §aBauServer §8● §7";
    @Getter
    private final String no_permission = prefix + "§7Dazu hast du keine §cBerechtigung.";
    @Getter
    private File file;
    @Getter
    private BauServerConfiguration configuration;
    @Getter
    private ItemBuilder itemBuilder;
    @Getter
    private final ExecutorService service = Executors.newCachedThreadPool( );
    @Getter
    private Scoreboard scoreboard;

    @Getter
    private final List< String > color = new ArrayList<>( );


    @Override
    public void onLoad( ) {
        super.onLoad( );
        instance = this;
        itemBuilder = new ItemBuilder( );
        scoreboard = new Scoreboard( );
    }

    @Override
    public void onEnable( ) {
        loadCommands( );
        loadEvents( );

        if ( !getDataFolder( ).exists( ) ) {
            getDataFolder( ).mkdir( );
        }

        this.file = new File( this.getDataFolder( ), "config.json" );
        try {
            if ( this.file.exists( ) ) {
                configuration = new Gson( ).fromJson( new FileReader( this.file ), BauServerConfiguration.class );
            } else {
                this.file.createNewFile( );
                this.configuration = new BauServerConfiguration( );
                this.configuration.save( file );
            }
        } catch ( final Exception exception ) {
            exception.printStackTrace( );
        }

        Bukkit.getWorlds( ).forEach( ( world -> {
            world.setTime( 6000 );
            world.setStorm( false );
            world.setThundering( false );
            world.setGameRuleValue( "doMobSpawning", "false" );
        } ) );

        color.add( "Blau" );
        color.add( "Gelb" );
        color.add( "Grün" );
        color.add( "Rot" );
        color.add( "Türkis" );
        color.add( "Schwarz" );
        color.add( "Orange" );
        color.add( "Grau" );
        color.add( "Lila" );
    }

    @Override
    public void onDisable( ) {
        configuration.save( file );
        service.shutdown( );
    }

    private void loadCommands( ) {
        getCommand( "projects" ).setExecutor( new ProjectsCommand( ) );
        getCommand( "project" ).setExecutor( new ProjectCommand( ) );
        getCommand( "setspawn" ).setExecutor( new SetSpawnCommand( ) );
        getCommand( "spawn" ).setExecutor( new SpawnCommand( ) );
        getCommand( "color" ).setExecutor( new ColorCommand( ) );

    }

    private void loadEvents( ) {
        new Reflections( "de.daniel.bauserver.listener" ).getSubTypesOf( Listener.class ).forEach( events -> {
            try {
                Bukkit.getPluginManager( ).registerEvents( events.newInstance( ), this );
            } catch ( final InstantiationException | IllegalAccessException event ) {
                event.printStackTrace( );
            }
        } );
    }
}
