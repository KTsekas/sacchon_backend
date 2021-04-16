package gr.codehub.sacchon.resources;


import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.PatientInfoRepresentation;
import org.restlet.resource.Get;

public class PatientInfoResource extends AuthResource {

    @Get("json")
    public PatientInfoRepresentation getSomething(){
        return new PatientInfoRepresentation((Patient)getUser());
    }
}
