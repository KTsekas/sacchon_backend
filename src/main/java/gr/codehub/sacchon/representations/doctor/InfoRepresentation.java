package gr.codehub.sacchon.representations.doctor;

import gr.codehub.sacchon.model.Doctor;
import lombok.Data;

@Data
public class InfoRepresentation {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public InfoRepresentation(Doctor p){
        if( p == null )
            return;
        this.id = p.getId();
        this.firstName=p.getFirstName();
        this.lastName=p.getLastName();
        this.email = p.getEmail();
    }
}
