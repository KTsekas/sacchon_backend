package gr.codehub.sacchon.resources.doctor;

import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.representations.doctor.InfoRepresentation;
import gr.codehub.sacchon.resources.AuthResource;
import org.restlet.resource.Get;

public class InfoResource extends AuthResource {

    @Get("json")
    public InfoRepresentation getSomething() {
        return new InfoRepresentation((Doctor) getUser());
    }
}
