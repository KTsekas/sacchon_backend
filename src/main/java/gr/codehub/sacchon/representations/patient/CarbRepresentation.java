package gr.codehub.sacchon.representations.patient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gr.codehub.sacchon.model.CarbRecord;
import gr.codehub.sacchon.util.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CarbRepresentation {

    private int id;
    private double carb;

    @JsonSerialize( using = LocalDateSerializer.class)
    private LocalDate date;

    public CarbRepresentation(CarbRecord record){
        if( record == null)
            return;
        this.id = record.getId();
        this.carb=record.getCarbIntake();
        this.date=record.getDate();
    }
}

