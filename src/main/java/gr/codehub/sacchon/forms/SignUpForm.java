package gr.codehub.sacchon.forms;

import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.model.User;
import lombok.Data;

@Data
public class SignUpForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;


    public <T extends User> T createUser(T usr){
        usr.setFirstName(firstName);
        usr.setLastName(lastName);
        usr.setEmail(email);
        usr.setPassword(password);
        usr.setRole(role);
        return usr;
    }
    public Doctor createDoctor(){
        return createUser(new Doctor());
    }
    public Patient createPatient(){
       return createUser(new Patient());
    }
}
