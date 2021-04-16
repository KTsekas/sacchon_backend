package gr.codehub.sacchon.resources.patient;

import gr.codehub.sacchon.model.User;
import org.restlet.Context;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class AuthResource extends ServerResource {
    private User authenticatedUser;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        Context ctx  = getApplication().getContext();
        String id = getRequest().getClientInfo().getUser().getIdentifier();
        this.authenticatedUser = (User) ctx.getAttributes().get(id);
        ctx.getAttributes().remove(id);
        if( this.authenticatedUser == null ){
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);
        }
    }

    public User getUser() { return this.authenticatedUser; }
}
