package gr.codehub.sacchon.representations.patient;

import gr.codehub.sacchon.model.GlucoseRecord;
import lombok.Data;

import java.time.ZoneId;

@Data
public class PatientGlucoseRepresentation {

    private int id;
    private double glucoseLevel;
    private long dateTime;

    public PatientGlucoseRepresentation(GlucoseRecord record){
        if( record == null)
            return;
        this.id = record.getId();
        this.glucoseLevel=record.getGlucoseLevel();
        this.dateTime=record.getDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
