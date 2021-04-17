package gr.codehub.sacchon.representations.doctor;

import gr.codehub.sacchon.model.Doctor;
import lombok.Data;

@Data
public class InfoRepresentation {
    private String firstName;
    private String lastName;

    public InfoRepresentation(Doctor p){
        if( p == null )
            return;
        this.firstName=p.getFirstName();
        this.lastName=p.getLastName();
    }
}
