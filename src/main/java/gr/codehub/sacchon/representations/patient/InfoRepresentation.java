package gr.codehub.sacchon.representations.patient;

import gr.codehub.sacchon.model.Patient;
import lombok.Data;

@Data
public class InfoRepresentation {
    private String firstName;
    private String lastName;
    private String doctor;
    private String consultationStatus;
    private String email;


    public InfoRepresentation(Patient p){
        if( p == null )
            return;
        this.firstName=p.getFirstName();
        this.lastName=p.getLastName();
        if ( p.getDoctor() != null )
            this.doctor = p.getDoctor().getFullName();
        if ( p.getConsultationStatus() != null )
            this.consultationStatus=p.getConsultationStatus();
        this.email=p.getEmail();
    }
}
