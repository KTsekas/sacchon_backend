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
    public static final int MISSING_VALUE=-1;
    int id=MISSING_VALUE;
    String text;

    @JsonSerialize( using = LocalDateSerializer.class)
    @JsonDeserialize( using = LocalDateDeserializer.class)
    LocalDate date;


    public boolean isInValid(){
        return id == MISSING_VALUE;
    }

    public Consultation create(){
        return update(new Consultation());
    }
    public Consultation update(Consultation c){
        c.setDate(date);
        c.setConsultationText(text);
        c.setExpirationDate(date.plusMonths(1));
        return c;
    }
}
