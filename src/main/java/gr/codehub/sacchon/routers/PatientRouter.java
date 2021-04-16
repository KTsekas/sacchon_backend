package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.resources.patient.*;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

public class PatientRouter extends Router{

    public void setupEndPoints(){
        this.setDefaultMatchingMode(Template.MODE_EQUALS);
        this.attach("/" , InfoResource.class);
        this.attach("/glucose?offset={offset}&limit={limit}", GlucoseListResource.class).setMatchingQuery(true);
        this.attach("/glucose/{id}", GlucoseResource.class);
        this.attach("/glucose", GlucoseResource.class);
        this.attach("/carb?offset={offset}&limit={limit}", CarbListResource.class).setMatchingQuery(true);
        this.attach("/carb/average", AvgCarbResource.class);
        this.attach("/carb/{id}", CarbResource.class);
        this.attach("/carb", CarbResource.class);
    }
}
