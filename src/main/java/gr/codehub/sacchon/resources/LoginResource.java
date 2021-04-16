package gr.codehub.sacchon.resources;

import gr.codehub.sacchon.representations.LoginFormRepresentation;
import gr.codehub.sacchon.representations.LoginRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class LoginResource extends ServerResource {

    private String username;
    private String password;

    @Post("json")
    public LoginRepresentation doLogin(LoginFormRepresentation repr){
        System.out.println(repr);
        return new LoginRepresentation(null);
    }
}
