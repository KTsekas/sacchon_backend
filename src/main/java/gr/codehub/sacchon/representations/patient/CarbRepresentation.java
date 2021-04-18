package gr.codehub.sacchon.representations.patient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.util.LocalDateDeserializer;
import gr.codehub.sacchon.util.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CarbRepresentation {

    private int id;
    private double glucoseLevel;

    @JsonSerialize( using = LocalDateSerializer.class)
    private LocalDate date;

    public CarbRepresentation(CarbRecord record){
        if( record == null)
            return;
        this.id = record.getId();
        this.glucoseLevel=record.getCarbIntake();
        this.date=record.getDate();
    }
}

