package gr.codehub.sacchon.representations.patient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.util.LocalDateSerializer;
import gr.codehub.sacchon.util.LocalTimeSerializer;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class GlucoseRepresentation {

    private int id;
    private double glucose;
    @JsonSerialize( using = LocalDateSerializer.class)
    private LocalDate date;
    @JsonSerialize( using = LocalTimeSerializer.class)
    private LocalTime time;

    public GlucoseRepresentation(GlucoseRecord rec){
        if( rec == null)
            return;
        this.id = rec.getId();
        this.glucose =rec.getGlucoseLevel();
        this.date=rec.getDate();
        this.time = rec.getTime();
    }
}
