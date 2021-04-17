package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.resources.doctor.InfoResource;
import org.restlet.routing.Router;

public class DoctorRouter extends Router {
    public void setupEndPoints(){
        this.attach("/" , InfoResource.class);

//        this.attach("/glucose", GlucoseResource.class);
//        this.attach("/carb", CarbResource.class);
//        this.attach( "/average/{type}", AvgResource.class);
    }
}
