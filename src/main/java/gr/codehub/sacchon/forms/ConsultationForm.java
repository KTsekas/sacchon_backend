package gr.codehub.sacchon.forms;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gr.codehub.sacchon.model.Consultation;
import gr.codehub.sacchon.util.LocalDateDeserializer;
import gr.codehub.sacchon.util.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsultationForm {
    int id;
    String text;
    @JsonSerialize( using = LocalDateSerializer.class)
    @JsonDeserialize( using = LocalDateDeserializer.class)
    LocalDate date;

    public Consultation create(){
        return update(new Consultation());
    }
    public Consultation update(Consultation c){
        c.setDate(date);
        c.setConsultationText(text);
    }
}
