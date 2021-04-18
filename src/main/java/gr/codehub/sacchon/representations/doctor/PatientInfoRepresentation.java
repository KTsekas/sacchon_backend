package gr.codehub.sacchon.representations.doctor;

import gr.codehub.sacchon.model.Patient;
import lombok.Data;

@Data
public class PatientInfoRepresentation {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public PatientInfoRepresentation(Patient p){
        this.id = p.getId();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.email=p.getEmail();

    }
}
