package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.resources.patient.AvgResource;
import gr.codehub.sacchon.resources.patient.CarbResource;
import gr.codehub.sacchon.resources.patient.GlucoseResource;
import gr.codehub.sacchon.resources.patient.InfoResource;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

public class PatientRouter extends Router{

    public void setupEndPoints(){
        this.attachDefault(InfoResource.class);
        this.setDefaultMatchingMode(Template.MODE_STARTS_WITH);
        this.attach("/glucose", GlucoseResource.class);
        this.attach("/carb", CarbResource.class);
        this.attach( "/average/{type}",AvgResource.class);
    }
}
