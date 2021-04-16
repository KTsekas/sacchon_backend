package gr.codehub.sacchon.resources;

import gr.codehub.sacchon.jpautil.JpaUtil;
import gr.codehub.sacchon.model.User;
import gr.codehub.sacchon.repository.UserRepository;
import gr.codehub.sacchon.representations.LoginFormRepresentation;
import gr.codehub.sacchon.representations.LoginRepresentation;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class LoginResource extends ServerResource {

    private String username;
    private String password;

    @Post("json")
    public LoginRepresentation doLogin(LoginFormRepresentation repr){
        UserRepository repo = new UserRepository(JpaUtil.getEntityManager());
        User usr = repo.getUser(repr.getUsername());
        if( usr == null )
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND,"No user associated with that email");
        if ( !usr.getPassword().equals(repr.getPassword()))
            throw new ResourceException(Status.CLIENT_ERROR_NOT_FOUND,"Invalid username or password");
        return new LoginRepresentation(usr);
    }
}
