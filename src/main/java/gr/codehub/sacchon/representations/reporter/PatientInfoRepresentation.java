package gr.codehub.sacchon.representations.reporter;

import gr.codehub.sacchon.model.Patient;
import lombok.Data;

@Data
public class PatientInfoRepresentation {
    int id;
    String fullName;
    String email;


    public PatientInfoRepresentation(Patient p){
        this.id = p.getId();
        this.fullName = p.getFullName();
        this.email = p.getEmail();
    }
}
