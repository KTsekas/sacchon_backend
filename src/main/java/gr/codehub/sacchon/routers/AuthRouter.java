package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.resources.PatientResource;
import org.restlet.routing.Router;

public class AuthRouter extends Router {

    public AuthRouter(){
       super();
       this.attach("/test", PatientResource.class);
    }
}
