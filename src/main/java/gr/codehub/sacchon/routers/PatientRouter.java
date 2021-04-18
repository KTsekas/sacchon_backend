package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.resources.patient.*;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

public class PatientRouter extends Router{

    public void setupEndPoints(){
        this.attachDefault(InfoResource.class);
        this.setDefaultMatchingMode(Template.MODE_STARTS_WITH);
        this.attach("/glucose", GlucoseResource.class);
        this.attach("/carb", CarbResource.class);
        this.attach( "/average/{type}",AvgResource.class);
        this.attach("/consultation", ConsultationResource.class);
    }
}
