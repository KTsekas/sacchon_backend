package gr.codehub.sacchon.security;

import gr.codehub.sacchon.jpautil.JpaUtil;
import gr.codehub.sacchon.model.User;
import gr.codehub.sacchon.repository.UserRepository;
import org.restlet.Application;
import org.restlet.security.SecretVerifier;
import org.restlet.security.Verifier;

public class RoleVerifier extends SecretVerifier {

    private String role;
    private Application application;

    public RoleVerifier(Application app, String role) {
        this.role = role;
        this.application = app;
    }

    @Override
    public int verify(String s, char[] chars) {
        // get user from database if exists
        UserRepository repo = new UserRepository(JpaUtil.getEntityManager());
        User usr = repo.getUser(s);
        // first check if user with username s exists, in  correct role, then password
        if (usr == null)
            return Verifier.RESULT_INVALID;
        if (!usr.getRole().equals(this.role))
            return Verifier.RESULT_INVALID;
        if (!compare(chars,usr.getPassword().toCharArray()))
            return Verifier.RESULT_INVALID;
        // save user object in global context so we can access it in resources
        this.application.getContext().getAttributes().put("user",usr);
        return Verifier.RESULT_VALID;
    }
}
