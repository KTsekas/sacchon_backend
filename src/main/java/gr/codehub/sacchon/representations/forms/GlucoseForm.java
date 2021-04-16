package gr.codehub.sacchon.representations.forms;

import gr.codehub.sacchon.model.GlucoseRecord;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class GlucoseForm {
    public static final int MISSING_ID_VALUE=-1;
    private int id=MISSING_ID_VALUE;
    private double glucoseLevel;
    private long dateTime;


    public LocalDateTime getLocalDateTime(){
    }

    public GlucoseRecord update(GlucoseRecord rec){
        if( id != MISSING_ID_VALUE)
            rec.id = this.id;
        rec.setGlucoseLevel(glucoseLevel);
        rec.setDateTime(getLocalDateTime());
    }
}
