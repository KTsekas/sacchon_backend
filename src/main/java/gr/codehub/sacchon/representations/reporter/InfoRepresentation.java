package gr.codehub.sacchon.representations.reporter;

import gr.codehub.sacchon.model.Reporter;
import lombok.Data;

@Data
public class InfoRepresentation {

    private String firstName;
    private String lastName;
    private String email;

    public InfoRepresentation(Reporter r){
        this.firstName = r.getFirstName();
        this.lastName = r.getLastName();
        this.email = r.getEmail();
    }



}
