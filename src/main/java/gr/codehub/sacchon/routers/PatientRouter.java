package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.resources.PatientGlucoseListResource;
import gr.codehub.sacchon.resources.PatientGlucoseResource;
import gr.codehub.sacchon.resources.PatientInfoResource;
import org.restlet.routing.Router;

public class PatientRouter extends Router{

    public void setupEndPoints(){
        this.attach("/" ,PatientInfoResource.class);
        this.attach("/glucose?offset={offset}&limit={limit}", PatientGlucoseListResource.class).setMatchingQuery(true);
        this.attach("/glucose/{id}", PatientGlucoseResource.class);
    }
}
