package gr.codehub.sacchon.resources;

import gr.codehub.sacchon.forms.SignUpForm;
import gr.codehub.sacchon.model.User;
import gr.codehub.sacchon.model.UserRole;
import gr.codehub.sacchon.representations.LoginRepresentation;
import gr.codehub.sacchon.services.UserRepository;
import gr.codehub.sacchon.util.JpaUtil;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.util.regex.Pattern;

public class SignUpResource extends ServerResource {
    public static final Pattern EMAIL_CHECKER = Pattern.compile("(.+)@(.+)");
    public static final Pattern PASSWORD_CHECKER = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");

    UserRepository repo;

    public boolean isValidPassword(String password) {
        return PASSWORD_CHECKER.matcher(password).matches();
    }

    public boolean canUseUsername(String username) {
        // simplest email checker ever created
        return EMAIL_CHECKER.matcher(username).matches() && repo.getUser(username) == null;
    }

    @Post("json")
    public LoginRepresentation doSignUp(SignUpForm frm) {
        // check
        // Role, password and if user already exists ( email )
        if (!UserRole.isValidSignUpRole(frm.getRole())) {
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST, frm.getRole() + " is not a valid sign up role");
            return null;
        }
        if (!isValidPassword(frm.getPassword())) {
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "Password not in correct format");
        }
        repo = new UserRepository(JpaUtil.getEntityManager());
        if (!canUseUsername(frm.getEmail())) {
            setStatus(Status.CLIENT_ERROR_BAD_REQUEST, "Account already exists or invalid username");
            return null;
        }
        // finally we can make user
        User usr=null;
        if (frm.getRole().equals(UserRole.DOCTOR))
            usr = frm.createDoctor();
        else if ( frm.getRole().equals(UserRole.PATIENT))
            usr = frm.createDoctor();
        usr = repo.save(usr);
        if (usr == null) {
            setStatus(Status.SERVER_ERROR_INTERNAL, "Unable to create account");
            return null;
        }
        else
            return new LoginRepresentation(usr);
    }
}
