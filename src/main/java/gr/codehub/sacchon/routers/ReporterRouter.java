package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.resources.reporter.*;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

public class ReporterRouter extends Router {
    public void setupEndPoints(){
        this.setDefaultMatchingMode(Template.MODE_EQUALS);
        this.attach("/patient/activity",ActivityPatient.class);
        this.attach("/patient/search", SearchPatient.class);
        this.attach("/patient/carb", CarbResource.class);
        this.attach("/patient/glucose", GlucoseResource.class);
        this.attach("/doctor/search", SearchDoctor.class);
        this.attach("/doctor/consults",DoctorConsultationsResource.class);
        this.attach("/doctor/activity",ActivityDoctor.class);
        this.attach("/patient/pending",ConsultPending.class);

        this.attach("/",InfoResource.class);

//        this.attach("/patients",PatientsResource.class);
//        this.attach("/doctors",DoctorsResource.class);
    }
}
