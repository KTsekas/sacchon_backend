package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.resources.doctor.*;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

public class DoctorRouter extends Router {
    public void setupEndPoints(){
        this.attachDefault(InfoResource.class);
        this.setDefaultMatchingMode(Template.MODE_STARTS_WITH);
        this.attach("/finder", PatientPickerResource.class);
        this.attach("/consultation/pending", PendingResource.class);
        this.attach("/consultation", ConsultationResource.class);
        this.attach("/patient/carb", PatientCarbResource.class);
        this.attach("/patient/glucose", PatientCarbResource.class);
        this.attach("/patient",PatientListResource.class);
    }
}
