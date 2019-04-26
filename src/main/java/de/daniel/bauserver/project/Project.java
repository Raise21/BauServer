package de.daniel.bauserver.project;

import de.daniel.bauserver.location.SerializableLocation;
import lombok.Data;

import java.beans.ConstructorProperties;
import java.util.*;

@Data
public class Project {

    private final UUID uuid;
    private final ProjectType type;
    private final SerializableLocation spawnLocation;
    private Set< UUID > members = new HashSet<>( );
    private boolean finished;

    @ConstructorProperties( {"uuid", "type", "spawnLocation"} )
    public Project( final UUID uuid, final ProjectType type, final SerializableLocation spawnLocation ) {
        this.uuid = uuid;
        this.type = type;
        this.spawnLocation = spawnLocation;
    }

}
