package gr.codehub.sacchon.routers;
import gr.codehub.sacchon.resources.PatientInfoResource;
import org.restlet.routing.Router;

public class PatientRouter extends Router{

    public void setupEndPoints(){
        this.attachDefault(PatientInfoResource.class);
    }
}
