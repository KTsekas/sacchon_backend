package gr.codehub.sacchon.resources;

import gr.codehub.sacchon.jpautil.JpaUtil;
import gr.codehub.sacchon.model.User;
import gr.codehub.sacchon.model.UserRole;
import gr.codehub.sacchon.repository.UserRepository;
import gr.codehub.sacchon.representations.LoginRepresentation;
import gr.codehub.sacchon.representations.forms.SignUpForm;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class SignUpResource extends ServerResource {

    UserRepository repo;

    public boolean isValidPassword(String password){
        return true;
    }
    public boolean canUseUsername(String username){
        return repo.getUser(username) == null;
    }

    @Post("json")
    public LoginRepresentation doSignUp(SignUpForm frm){
        // check
        // Role, password and if user already exists ( email )
        if ( !UserRole.isValidSignUpRole(frm.getRole()))
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,
                    frm.getRole() + " is not a valid sign up role");
        if ( !isValidPassword(frm.getPassword()))
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,
                    "password not in correct format");
        repo = new UserRepository(JpaUtil.getEntityManager());
        if (!canUseUsername(frm.getEmail()))
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST,
                    "account already exists");
        // finally we can make user
        User usr = frm.createUser();
        usr = repo.save(usr);
        if ( usr == null )
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL,
                    "unable to create account");
        return new LoginRepresentation(usr);
    }
}
