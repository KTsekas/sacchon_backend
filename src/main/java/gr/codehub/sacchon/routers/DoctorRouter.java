package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.resources.doctor.InfoResource;
import gr.codehub.sacchon.resources.doctor.PatientPickerResource;
import gr.codehub.sacchon.resources.doctor.ConsultationResource;
import org.restlet.routing.Router;

public class DoctorRouter extends Router {
    public void setupEndPoints(){
        this.attachDefault(InfoResource.class);
        this.attach("/finder", PatientPickerResource.class);
        this.attach("/consultation", ConsultationResource.class);
//        this.attach("/glucose", GlucoseResource.class);
//        this.attach("/carb", CarbResource.class);
//        this.attach( "/average/{type}", AvgResource.class);
    }
}
