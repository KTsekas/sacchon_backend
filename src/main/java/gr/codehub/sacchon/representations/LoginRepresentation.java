package gr.codehub.sacchon.representations;

import gr.codehub.sacchon.model.User;
import lombok.Data;

import java.util.Base64;

@Data
public class LoginRepresentation {
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String authToken;

    public LoginRepresentation(User user){
        if ( user == null)
            return;
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.authToken = Base64.getEncoder().encodeToString((user.getFirstName() +": " + user.getPassword()).getBytes());
    }

}
