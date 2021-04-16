package gr.codehub.sacchon.resources.patient;


import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.representations.patient.InfoRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import org.restlet.resource.Get;

public class InfoResource extends AuthResource {

    @Get("json")
    public InfoRepresentation getSomething(){
        return new InfoRepresentation((Patient)getUser());
    }
}
