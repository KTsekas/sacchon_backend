package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.resources.patient.CountResource;
import gr.codehub.sacchon.resources.patient.*;
import org.restlet.routing.Router;

public class PatientRouter extends Router{

    public void setupEndPoints(){
        this.attach("/" , InfoResource.class);
        this.attach("/glucose", GlucoseResource.class);
        this.attach("/carb", CarbResource.class);
        this.attach( "/average/{type}",AvgResource.class);
        this.attach( "/{type}/count", CountResource.class);
    }
}
