package gr.codehub.sacchon.representations.reporter;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PendingConsultation {
    String firstName;
    String lastName;
    LocalDate expirationDate;
    LocalDate dateCarb;
    LocalDate dateGlucose;

    public PendingConsultation(Object[] obj){
        this.firstName = (String)obj[0];
        this.lastName = (String)obj[1];
//        this.expirationDate = (S)
    }
}
