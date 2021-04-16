package gr.codehub.sacchon.resources;

import gr.codehub.sacchon.model.User;
import gr.codehub.sacchon.services.BaseService;
import org.restlet.Context;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class AuthResource extends ServerResource {
    private User authenticatedUser;
    private BaseService service;

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        // get user object from global context
        Context ctx  = getApplication().getContext();
        String id = getRequest().getClientInfo().getUser().getIdentifier();
        this.authenticatedUser = (User) ctx.getAttributes().get(id);
        ctx.getAttributes().remove(id);
        if( this.authenticatedUser == null ){
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED);
        }
    }
    @Override
    protected void doRelease() throws ResourceException {
        super.doRelease();
        // close entity managers
        if ( service != null )
            service.close();
    }

    public User getUser() { return this.authenticatedUser; }
    public void setService(BaseService service) {
        this.service=service;
    }

}
