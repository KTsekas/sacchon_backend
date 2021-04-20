package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.patient.FieldCountRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import org.restlet.resource.Get;

public class FieldCountResource extends AuthResource {


    @Get("json")
    public FieldCountRepresentation getCounts(){
        Patient p = (Patient)getUser();
        return new FieldCountRepresentation(p.getCarbs().size(),p.getGlucoseLevels().size());
    }
}
