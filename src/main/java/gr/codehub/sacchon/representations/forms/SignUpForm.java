package gr.codehub.sacchon.representations.forms;

import gr.codehub.sacchon.model.User;
import lombok.Data;

@Data
public class SignUpForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;


    public User createUser(){
        User usr = new User();
        usr.setFirstName(firstName);
        usr.setLastName(lastName);
        usr.setEmail(email);
        usr.setPassword(password);
        usr.setRole(role);
        return usr;
    }
}