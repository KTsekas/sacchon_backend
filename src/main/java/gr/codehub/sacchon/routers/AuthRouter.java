package gr.codehub.sacchon.routers;

import gr.codehub.sacchon.resources.LoginResource;
import org.restlet.routing.Router;

public class AuthRouter extends Router {

    public AuthRouter(){

    }

    public void setupEndPoints() {
        this.attach("/login", LoginResource.class);
    }
}
