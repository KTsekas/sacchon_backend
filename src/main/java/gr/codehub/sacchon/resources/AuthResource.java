package gr.codehub.sacchon.resources;

import gr.codehub.sacchon.model.User;
import gr.codehub.sacchon.security.RoleVerifier;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class AuthResource extends ServerResource {
    private User authenticatedUser;


    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        this.authenticatedUser = (User) getApplication().getContext().getAttributes().get(RoleVerifier.USER_ATTRIBUTE);
        if( this.authenticatedUser == null ){
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);
        }
    }

    public User getUser() { return this.authenticatedUser; }
}
