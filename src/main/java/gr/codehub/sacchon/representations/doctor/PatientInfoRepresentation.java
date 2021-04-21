package gr.codehub.sacchon.representations.doctor;

import gr.codehub.sacchon.model.Patient;
import lombok.Data;

@Data
public class PatientInfoRepresentation {
    private int id;
    private String fullName;
    private String email;

    public PatientInfoRepresentation(Patient p){
        this.id = p.getId();
        this.fullName=p.getFullName();
        this.email=p.getEmail();
    }
}
