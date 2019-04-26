package de.daniel.bauserver.project;

import java.beans.ConstructorProperties;

public enum ProjectType {

    TRAINING( "Training" ),
    BUILDFFA( "BuildFFA" ),
    BEDWARS( "Bedwars" ),
    CORES( "Cores" ),
    SKYBLOCK( "SkyBlock" ),
    QHG( "QHG" ),
    WAVES( "Waves" ),
    SUMO( "Sumo" ),
    MLGRUSH( "MLGRush" );

    private final String type;

    public static ProjectType getType( final String name ) {
        final ProjectType[] types = values( );
        final int type = types.length;

        for ( int i = 0; i < type; ++i ) {
            final ProjectType each = types[ i ];
            if ( each.getType( ).equalsIgnoreCase( name ) ) {
                return each;
            }
        }
        return null;
    }

    @ConstructorProperties( {"type"} )
    ProjectType( String type ) {
        this.type = type;
    }

    public String getType( ) {
        return this.type;
    }

}
