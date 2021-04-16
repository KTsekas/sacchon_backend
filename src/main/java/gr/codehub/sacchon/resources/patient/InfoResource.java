package gr.codehub.sacchon.resources.patient;


import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.patient.PatientInfoRepresentation;
import org.restlet.resource.Get;

public class InfoResource extends AuthResource {

    @Get("json")
    public PatientInfoRepresentation getSomething(){
        return new PatientInfoRepresentation((Patient)getUser());
    }
}
