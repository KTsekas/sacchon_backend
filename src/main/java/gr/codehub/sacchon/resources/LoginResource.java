package gr.codehub.sacchon.resources;

import gr.codehub.sacchon.forms.LoginForm;
import gr.codehub.sacchon.model.User;
import gr.codehub.sacchon.representations.LoginRepresentation;
import gr.codehub.sacchon.services.UserRepository;
import gr.codehub.sacchon.util.JpaUtil;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class LoginResource extends ServerResource {

    private String username;
    private String password;

    @Post("json")
    public LoginRepresentation doLogin(LoginForm frm){
        UserRepository repo = new UserRepository(JpaUtil.getEntityManager());
        User usr = repo.getUser(frm.getUsername());
        if( usr == null ){
           setStatus(Status.CLIENT_ERROR_NOT_FOUND,"No user associated with that email");
           return null;
        }
        if ( !usr.getPassword().equals(frm.getPassword())){
            setStatus(Status.CLIENT_ERROR_NOT_FOUND,"Invalid username or password");
            return null;
        }
        return new LoginRepresentation(usr);
    }
}
