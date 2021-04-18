package gr.codehub.sacchon.representations.patient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gr.codehub.sacchon.model.GlucoseRecord;
import gr.codehub.sacchon.util.LocalDateDeserializer;
import gr.codehub.sacchon.util.LocalDateSerializer;
import gr.codehub.sacchon.util.LocalTimeDeserializer;
import gr.codehub.sacchon.util.LocalTimeSerializer;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class GlucoseRepresentation {

    private int id;
    private double glucoseLevel;
    @JsonSerialize( using = LocalDateSerializer.class)
    private LocalDate date;
    @JsonSerialize( using = LocalTimeSerializer.class)
    private LocalTime time;

    public GlucoseRepresentation(GlucoseRecord rec){
        if( rec == null)
            return;
        this.id = rec.getId();
        this.glucoseLevel=rec.getGlucoseLevel();
        this.date=rec.getDate();
        this.time = rec.getTime();
    }
}
