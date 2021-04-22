package gr.codehub.sacchon.representations.reporter;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.model.Patient;
import gr.codehub.sacchon.resources.reporter.ConsultPending;
import gr.codehub.sacchon.util.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PendingConsultationRepresentation {
    String patientName;
    String patientEmail;
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate expirationDate;

    public PendingConsultationRepresentation(Object[] obj){
        this.patientName = obj[0] + " " + obj[1];
        this.patientEmail = (String)obj[2];
        this.expirationDate = (LocalDate)obj[3];
    }
}
