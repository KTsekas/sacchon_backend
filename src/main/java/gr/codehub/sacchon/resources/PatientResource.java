package gr.codehub.sacchon.resources;


import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.model.User;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class PatientResource extends ServerResource {

    @Get("json")
    public User getSomething(){
        Patient p = (Patient)getApplication().getContext().getAttributes().get("user");
        return p;
    }
}
