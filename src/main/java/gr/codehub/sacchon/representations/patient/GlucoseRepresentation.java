package gr.codehub.sacchon.representations.patient;

import gr.codehub.sacchon.model.GlucoseRecord;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class GlucoseRepresentation {

    private int id;
    private double glucoseLevel;
    private LocalDate date;
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
