package gr.codehub.sacchon.resources;


import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.model.User;
import org.restlet.resource.Get;

public class PatientResource extends AuthResource {

    @Get("json")
    public User getSomething(){
        Patient p = (Patient)getApplication().getContext().getAttributes().get("user");
        return p;
    }
}
