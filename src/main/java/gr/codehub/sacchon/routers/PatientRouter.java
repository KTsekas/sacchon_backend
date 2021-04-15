package gr.codehub.sacchon.routers;
import gr.codehub.sacchon.resources.PatientResource;
import org.restlet.routing.Router;

public class PatientRouter extends Router{
    public PatientRouter(){
        this.attach("/", PatientResource.class);
    }
}
