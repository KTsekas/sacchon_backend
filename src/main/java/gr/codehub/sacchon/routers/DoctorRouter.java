package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.resources.doctor.InfoResource;
import gr.codehub.sacchon.resources.doctor.PatientPickerResource;
import org.restlet.routing.Router;

public class DoctorRouter extends Router {
    public void setupEndPoints(){
        this.attachDefault(InfoResource.class);
        this.attach("/find", PatientPickerResource.class);
//        this.attach("/glucose", GlucoseResource.class);
//        this.attach("/carb", CarbResource.class);
//        this.attach( "/average/{type}", AvgResource.class);
    }
}
