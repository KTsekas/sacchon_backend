package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.resources.patient.AvgResource;
import gr.codehub.sacchon.resources.patient.CarbResource;
import gr.codehub.sacchon.resources.patient.GlucoseResource;
import gr.codehub.sacchon.resources.patient.InfoResource;
import org.restlet.routing.Router;

public class PatientRouter extends Router{

    public void setupEndPoints(){
        this.attach("/" , InfoResource.class);
        this.attach("/glucose", GlucoseResource.class);
        this.attach("/carb", CarbResource.class);
        this.attach( "/average/{type}",AvgResource.class);
    }
}
