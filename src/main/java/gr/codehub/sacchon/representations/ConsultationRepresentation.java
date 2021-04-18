package gr.codehub.sacchon.representations;

import gr.codehub.sacchon.model.Consultation;

import java.time.LocalDate;

public class ConsultationRepresentation {

    int id;
    String consultationText;
    LocalDate date;

    public ConsultationRepresentation(Consultation c){
        this.id = c.getId();
        this.consultationText = c.getConsultationText();
        this.date = c.getDate();
    }
}
