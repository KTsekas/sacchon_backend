package gr.codehub.sacchon.representations;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.util.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsultationRepresentation {

    int id;
    String consultationText;
    @JsonSerialize( using = LocalDateSerializer.class)
    LocalDate date;

    public ConsultationRepresentation(Consultation c){
        this.id = c.getId();
        this.consultationText = c.getConsultationText();
        this.date = c.getDate();
    }
}
