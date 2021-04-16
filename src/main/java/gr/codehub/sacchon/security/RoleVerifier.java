package gr.codehub.sacchon.security;

import gr.codehub.sacchon.util.JpaUtil;
import gr.codehub.sacchon.model.User;
import gr.codehub.sacchon.services.UserRepository;
import org.restlet.Application;
import org.restlet.Request;
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

        Request.getCurrent().getClientInfo().setUser( new org.restlet.security.User(s));
        // save user object in global context so we can access it in resourcesg

        this.application.getContext().getAttributes().put(s,usr);
        return Verifier.RESULT_VALID;
    }
}
