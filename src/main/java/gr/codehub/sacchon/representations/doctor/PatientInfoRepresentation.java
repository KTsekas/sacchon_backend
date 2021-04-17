package gr.codehub.sacchon.representations.doctor;

import gr.codehub.sacchon.model.Patient;

public class PatientInfoRepresentation {
    public String firstName;
    public String lastName;
    public String email;

    public PatientInfoRepresentation(Patient p){
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.email=p.getEmail();
    }
}
