package gr.codehub.sacchon.representations.reporter;

import gr.codehub.sacchon.model.Doctor;
import lombok.Data;

@Data
public class DoctorInfoRepresentation {
    int id;
    String fullName;
    String email;


    public DoctorInfoRepresentation(Doctor d) {
        this.id = d.getId();
        this.fullName = d.getFullName();
        this.email = d.getEmail();
    }

}
