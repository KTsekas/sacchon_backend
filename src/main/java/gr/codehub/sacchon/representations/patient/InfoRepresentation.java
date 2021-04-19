package gr.codehub.sacchon.representations.patient;

import gr.codehub.sacchon.model.Patient;
import lombok.Data;

@Data
public class InfoRepresentation {
    private String firstName;
    private String lastName;
    private String doctorName;
    private String consultationStatus;


    public InfoRepresentation(Patient p){
        if( p == null )
            return;
        this.firstName=p.getFirstName();
        this.lastName=p.getLastName();
        if ( p.getDoctor() != null )
            this.doctorName = p.getDoctor().getFullName();
        if ( p.getConsultationStatus() != null )
            this.consultationStatus=p.getConsultationStatus();
    }
}
