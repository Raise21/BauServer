package de.daniel.bauserver.configuration;

import com.google.gson.GsonBuilder;
import de.daniel.bauserver.location.SerializableLocation;
import de.daniel.bauserver.project.Project;
import lombok.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class BauServerConfiguration {

    private final List< Project > projects = new ArrayList<>( );
    private SerializableLocation spawnLocation, holoLocation;

    public void save( final File file ) {
        try {
            final FileWriter writer = new FileWriter( file );
            writer.write( new GsonBuilder( ).setPrettyPrinting( ).create( ).toJson( this ) );
            writer.flush( );
            writer.close( );
        } catch ( final IOException exception ) {
            exception.printStackTrace( );
        }
    }

    public Project getProject( final String world ) {
        final Iterator iterator = this.projects.iterator( );
        Project project;

        do {
            if ( !iterator.hasNext( ) ) {
                return null;
            }
            project = ( Project ) iterator.next( );
        } while ( project.getSpawnLocation( ) == null || !project.getSpawnLocation( ).toLocation( )
                .getWorld( ).getName( ).equalsIgnoreCase( world ) );
        return project;
    }
}
