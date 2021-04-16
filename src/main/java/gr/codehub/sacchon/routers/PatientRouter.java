package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.resources.*;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

public class PatientRouter extends Router{

    public void setupEndPoints(){
        this.setDefaultMatchingMode(Template.MODE_EQUALS);
        this.attach("/" ,PatientInfoResource.class);
        this.attach("/glucose?offset={offset}&limit={limit}", PatientGlucoseListResource.class).setMatchingQuery(true);
        this.attach("/glucose/{id}", PatientGlucoseResource.class);
        this.attach("/glucose", PatientGlucoseResource.class);
        this.attach("/carb?offset={offset}&limit={limit}", PatientCarbListResource.class).setMatchingQuery(true);
        this.attach("/carb/{id}", PatientCarbResource.class);
        this.attach("/carb", PatientCarbResource.class);
    }
}
